package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "files")
public class Image extends BaseEntity {

	@NotNull
	private String fileName;

	@NotNull
	private String fileType;

	@Lob
	private byte[] data;

	private Long size;

	@Transient
	private String url;

	public Image() {
		super();
	}

	public Image(@NotNull String fileName, @NotNull String fileType, byte[] data, Long size) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.size = size;
	}

	@JsonIgnore
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}