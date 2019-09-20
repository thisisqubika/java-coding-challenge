package com.mooveit.cars.ingestion.ford.tasks;

import com.mooveit.cars.ingestion.ford.xml.FordCatalogueXmlParser;
import com.mooveit.cars.ingestion.ford.xml.FordNotParsedException;
import com.mooveit.cars.ingestion.ford.xml.mappers.CarMapper;
import com.mooveit.cars.ingestion.ford.xml.model.FordCatalogue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FordIngesterTask {

  public static final String INGESTED_FILE_EXTENSION = ".ingested";

  private static final String FAILED_TO_INGEST_FILE_EXTENSION = ".failed";
  private static final String FORD_INGESTER_DEFAULT_ROOT_PATH = "ford/";

  private final FordCatalogueXmlParser fordCatalogueXmlParser;
  private final CarMapper carMapper;
  private final MappedFordPersister mappedFordPersister;

  @Setter(onMethod_ = @Value("${cars.ford.ingester.root-path:ford/}"))
  @Getter
  private String fordIngesterBasePathString = FORD_INGESTER_DEFAULT_ROOT_PATH;
  @Getter
  private Path fordIngesterBasePath;

  @PostConstruct
  public void init() {
    this.fordIngesterBasePath = Paths.get(fordIngesterBasePathString);
    if (!Files.isDirectory(fordIngesterBasePath)) {
      throw new IllegalStateException(
              "Error during FordIngesterTask initialization. The base path configured doesn't exist or is not a folder! "
                      + "The base path found is: "
                      + fordIngesterBasePathString);
    }
  }

  @Scheduled(cron = "${cars.ford.ingester.runCron}")
  public void ingestFile() {
    try (DirectoryStream<Path> ds =
                 Files.newDirectoryStream(
                         fordIngesterBasePath, (path -> path.toString().endsWith(".xml")))) {
      ds.forEach(this::parseAndPersistFordCatalogue);
    } catch (IOException e) {
      log.error("Error occurred during ingestion, scanning folder: " + fordIngesterBasePath);
    }
  }

  private void parseAndPersistFordCatalogue(Path path) {
    try {
      FordCatalogue fordCatalogue = parseFordCatalogueFile(path);
      persistFordModels(fordCatalogue);
      markFileAsIngested(path);
    } catch (Exception e) {
      log.error("Error occurred parsing Ford catalogue file: " + path, e);
      markFileAsFailedToIngest(path);
    }
  }

  private FordCatalogue parseFordCatalogueFile(Path path)
          throws FordNotParsedException, IOException {
    try (BufferedInputStream bufferedInputStream =
                 new BufferedInputStream(Files.newInputStream(path))) {
      return fordCatalogueXmlParser.parse(bufferedInputStream);
    }
  }

  private void markFileAsFailedToIngest(Path path) {
    markFileAs(path, FAILED_TO_INGEST_FILE_EXTENSION);
  }

  private void markFileAsIngested(Path path) {
    markFileAs(path, INGESTED_FILE_EXTENSION);
  }

  private void markFileAs(Path path, String markingFileExtension) {
    try {
      Files.move(
              path, path.getParent().resolve(path.getFileName().toString() + markingFileExtension));
    } catch (IOException e) {
      log.error("Error during renaming of ingested Ford catalogue file: " + path);
    }
  }

  private void persistFordModels(FordCatalogue fordCatalogue) {
    fordCatalogue.getFordModels().stream()
            .map(carMapper::fordModelToCar)
            .forEach(mappedFordPersister::persist);
  }
}
