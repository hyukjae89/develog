package pe.oh29oh29.myweb.service;

import org.springframework.web.multipart.MultipartFile;

public interface AttachedFileService {
	
	public enum FileType {
		IMAGE_IN_CONTENTS(0), ATTACHMENT(1);
		
		private final int value;
		
		FileType(int value) {
			this.value = value;
		}
		
		public int intValue() {
			return value;
		}
	}

	public String transfer(MultipartFile file, FileType type) throws Exception;
	
	public void verifyAttachedFiles(String postIdx, String postContent);
	
}
