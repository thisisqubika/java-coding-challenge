
package com.mooveit.cars.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mooveit.cars.domain.Image;
import com.mooveit.cars.services.ImageService;

@RestController
public class FileController {

	private static final Logger log = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private ImageService imageService;

	@PostMapping("/specifications/{id}/image")
	public Image uploadFile(@PathVariable(name = "id") Long specId, @RequestParam("file") MultipartFile file) {

		log.info(String.format("Setting image for Specification id %d", specId));

		Image image = imageService.storeFile(specId, file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(String.format("/specifications/%d/image", specId)).toUriString();
		image.setUrl(fileDownloadUri);

		return image;
	}

	@GetMapping("/specifications/{id}/image")
	public ResponseEntity<org.springframework.core.io.Resource> downloadFile(@PathVariable(name = "id") Long specId) {

		log.info(String.format("Retrieving image for Specification id %d", specId));

		Image image = imageService.getFile(specId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
				.body(new ByteArrayResource(image.getData()));
	}

}