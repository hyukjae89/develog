package pe.oh29oh29.myweb.common;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Files {

	private String				name;
	private String				description;
	private List<MultipartFile>	files;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
}
