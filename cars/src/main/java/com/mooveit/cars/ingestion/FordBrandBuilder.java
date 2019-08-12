package com.mooveit.cars.ingestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.EngineType;
import com.mooveit.cars.domain.Modification;
import com.mooveit.cars.domain.Specification;
import com.mooveit.cars.domain.Wheel;

/**
 * Ford brand builder builds a specific {@link Brand} per each file found on the
 * base folder.
 * 
 * Sources are used to filter these files based on certain criteria, as for
 * examples old, temporary or already processed files.
 * 
 * The .xml format follow the next structure :
 * 
 * <pre>
 *{@code
 * <CATALOGUE>
 *    <MODEL name="Aspire" from="1994" to="1997" type="subcompact">
 *        <ENGINE power="1400" type="GAS"/>
 *        <WHEELS size="R15" type="STEEL"/>
 *        <SUBMODELS>
 *            <MODEL name="Aspire 2" line="hatchback">
 *                <ENGINE power="1600" type="GAS"/>
 *                <WHEELS size="R15" type="STEEL"/>
 *            </MODEL>
 *            <MODEL name="Aspire 4" line="hatchback">
 *                <ENGINE power="1600" type="GAS"/>
 *                <WHEELS size="R15" type="STEEL"/>
 *            </MODEL>
 *        </SUBMODELS>
 *    </MODEL>
 *    ...
 *</CATALOGUE>
 *}
 * </pre>
 */
public class FordBrandBuilder implements BrandBuilder {

	private static final Logger log = LoggerFactory.getLogger(FordBrandBuilder.class);

	/**
	 * Folder to scan
	 */
	private String scanDirectory = ".";

	/**
	 * Scan folder on classloader or file system
	 */
	private boolean scanClassloader = true;

	public FordBrandBuilder() {
		super();
	}

	public FordBrandBuilder(String scanDirectory, boolean scanClassloader) {
		super();
		this.scanDirectory = scanDirectory;
		this.scanClassloader = scanClassloader;
	}

	/**
	 * Brand name
	 */
	@Override
	public String getBrandName() {
		return "Ford";
	}

	/**
	 * Build a {@link Brand} per each `.xml` file found in the specific
	 * `fordFilesDir` folder.
	 */
	@Override
	public Map<String, Brand> createBrands(Set<String> sourceToOmit) {

		Map<String, Brand> brands = new HashMap<String, Brand>();
		try {

			List<File> files = this.getSourceXMLFiles(sourceToOmit);
			for (File file : files) {
				InputStream is = new FileInputStream(file);
				brands.put(file.toString(), this.createBrandFromXml(is));
			}

		} catch (Exception e) {
			String errorMessage = String.format(
					"There was a problem gathering files from directory %s with pattern ford-*.xml",
					this.scanDirectory);
			log.error(errorMessage);
			throw new IngestionException(errorMessage, e);
		}

		return brands;

	}

	protected List<File> getSourceXMLFiles(Set<String> sourceToOmit) {

		String filePattern = String.format("^ford-(.+).xml$", File.separator);
		try {
			// Scan directory
			Path basePath = null;
			if (this.scanClassloader) {
				basePath = Paths.get(getClass().getClassLoader().getResource(this.scanDirectory).toURI());
			} else {
				basePath = Paths.get(this.scanDirectory);
			}

			// Filter all ford-*.xml files with given format not in
			// `sourceToOmit`
			Pattern fordXmlFilePattern = Pattern.compile(filePattern);
			return Files.list(basePath).filter(p -> p.toFile().isFile())
					.filter(p -> fordXmlFilePattern.matcher(p.getFileName().toString()).matches())
					.filter(p -> !sourceToOmit.contains(p.toAbsolutePath().toString())).map(p -> p.toFile())
					.collect(Collectors.toList());
		} catch (Exception e) {
			String errorMessage = String.format(
					"There was a probles scanning files from directory %s with pattern `%s`", this.scanDirectory,
					filePattern);
			log.error(errorMessage);
			throw new IngestionException(errorMessage, e);
		}
	}
	
	/**
	 * Build a {@link Brand} based on a XML which path is given by parameter.
	 * 
	 * @param path
	 *            Path of the XML file to be parsed
	 * @return {@link Brand} built based on XML file given as argument
	 */
	protected Brand createBrandFromXml(InputStream is) {

		try {

			log.debug(String.format("Processing Ford catalog"));

			SAXReader reader = new SAXReader();
			Document document = reader.read(is);

			// Create Ford Brand
			Brand brand = new Brand("Ford");

			log.debug("Start processing CATALOG element ...");
			Element catalog = document.getRootElement();
			if (catalog.getName().equals("CATALOG")) {
				throw new Error("Root element name must be `CATALOG`");
			}

			log.debug("Start processing CATALOG/MODEL list ...");
			Iterator<Element> iterator = catalog.elementIterator("MODEL");
			while (iterator.hasNext()) {
				Specification spec = this.buildSpecification(brand, (Element) iterator.next());
				brand.addSpecification(spec);
			}
			log.debug(String.format("Specifications : %d created", brand.getSpecifications().count()));

			return brand;

		} catch (DocumentException e) {
			String errorMessage = String.format("Failed reading source InputStream");
			log.error(errorMessage);
			throw new IngestionException(errorMessage, e);
		}

	}

	/**
	 * Based on a `MODEL` element, create a {@link Specification} and all their
	 * {@link Modification}.
	 */
	private Specification buildSpecification(Brand brand, Element element) {

		String name = element.attributeValue("name");
		String type = element.attributeValue("type");
		Integer from = attributeIntValue(element, "from");
		Integer to = attributeIntValue(element, "to");
		Engine engine = this.buildEngine(element.element("ENGINE"));
		Wheel wheel = this.buildWheel(element.element("WHEELS"));

		Specification spec = new Specification(brand, name, from, to, type, engine, wheel);
		log.debug(String.format("Specification '%s' was created", spec.getName()));

		Element submodels = element.element("SUBMODELS");
		if (submodels != null) {
			Iterator<Element> iterator = submodels.elementIterator("MODEL");
			while (iterator.hasNext()) {
				Modification modif = this.buildModification((Element) iterator.next());
				spec.addModification(modif);
			}
		}
		log.debug(String.format("Modifications : %d created", spec.getModifications().size()));

		return spec;
	}

	private Modification buildModification(Element element) {
		if (element == null) {
			return null;
		}

		String name = element.attributeValue("name");
		String line = element.attributeValue("line");
		Integer from = attributeIntValue(element, "from");
		Integer to = attributeIntValue(element, "to");
		Engine engine = this.buildEngine(element.element("ENGINE"));
		Wheel wheel = this.buildWheel(element.element("WHEELS"));

		return new Modification(name, from, to, line, engine, wheel);
	}

	private Wheel buildWheel(Element element) {
		if (element == null) {
			return null;
		}
		String size = element.attributeValue("size");
		String type = element.attributeValue("type");
		return new Wheel(size, type);
	}

	private Engine buildEngine(Element element) {
		if (element == null) {
			return null;
		}
		Integer size = attributeIntValue(element, "power");
		EngineType type = element.attributeValue("type") == null ? null
				: EngineType.valueOf(element.attributeValue("type"));
		return new Engine(size, type);
	}

	private Integer attributeIntValue(Element element, String attrName) {
		return element.attributeValue(attrName) != null ? Integer.valueOf(element.attributeValue(attrName)) : null;
	}

}
