package com.mooveit.cars.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mooveit.cars.domain.AbstractSpec;
import com.mooveit.cars.domain.Image;
import com.mooveit.cars.repositories.AbstractSpecRepository;

@Service
public class ImageService {

	@Autowired
	private AbstractSpecRepository abstractSpecRepository;

	public Image storeFile(Long specId, MultipartFile file) {

		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			AbstractSpec spec = abstractSpecRepository.findById(specId).orElseThrow(() -> new ImageStorageException(
					String.format("Parent specification with id %d is doesn't exists", specId)));

			Image newImage = new Image(fileName, file.getContentType(), file.getBytes(), file.getSize());
			spec.setImage(newImage);

			spec = abstractSpecRepository.save(spec);
			return spec.getImage();

		} catch (IOException ex) {
			throw new ImageStorageException("Could not store image " + fileName + ". Please try again!", ex);
		}
	}

	public Image getFile(Long specId) {
		return abstractSpecRepository.findById(specId).map(spec -> spec.getImage())
				.orElseThrow(() -> new ImageStorageException("Image not found for Specification id " + specId));
	}
}
