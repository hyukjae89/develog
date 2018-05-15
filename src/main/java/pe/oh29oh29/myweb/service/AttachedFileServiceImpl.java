package pe.oh29oh29.myweb.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.oh29oh29.myweb.common.Constants;
import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.AttachedFileDao;
import pe.oh29oh29.myweb.dao.FileStoreDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.model.AttachedFile;
import pe.oh29oh29.myweb.model.AttachedFileExample;
import pe.oh29oh29.myweb.model.FileStore;
import pe.oh29oh29.myweb.model.FileStoreExample;
import pe.oh29oh29.myweb.model.PostExample;

@Service
public class AttachedFileServiceImpl implements AttachedFileService {

	@Autowired PostDao postDao;
	@Autowired FileStoreDao fileStoreDao;
	@Autowired AttachedFileDao attachedFileDao;
	
	@Override
	public String transfer(MultipartFile file, FileType type) throws Exception {
		String realFileFullName = file.getOriginalFilename();
		String realFileExt = realFileFullName.substring(realFileFullName.lastIndexOf(".") + 1, realFileFullName.length());
		String idx = Utils.generateIdx();
		String fakeFileFullName = idx + "." + realFileExt;
		
		FileStoreExample fileStoreExample = new FileStoreExample();
		fileStoreExample.createCriteria().andTypeEqualTo(type.intValue()).andAvailableEqualTo(Constants.TRUE);
		fileStoreExample.setOrderByClause("ORD ASC");
		List<FileStore> fileStores = fileStoreDao.selectFileStore(fileStoreExample);
		
		if (fileStores.isEmpty())
			throw new Exception();
		
		FileStore fileStore = fileStores.get(0);
		String realPathToBeSaved = fileStore.getRealPath();
		File dirToBeSaved = new File(realPathToBeSaved);
		
		if (!dirToBeSaved.exists())
			dirToBeSaved.mkdirs();
		
		dirToBeSaved = new File(dirToBeSaved.getPath() + File.separator + fakeFileFullName);
		
		file.transferTo(dirToBeSaved);
		
		AttachedFile attachedFile = new AttachedFile();
		attachedFile.setIdx(idx);
		attachedFile.setFakeName(fakeFileFullName);
		attachedFile.setRealName(realFileFullName);
		attachedFile.setRealPath(realPathToBeSaved);
		attachedFile.setSize((int)file.getSize());
		attachedFile.setType(FileType.IMAGE_IN_CONTENTS.intValue());
		attachedFileDao.insertAttachedFile(attachedFile);
		
		return fileStore.getContextPath() + "/" + fakeFileFullName;
	}

	@Override
	public void verifyAttachedFiles(String postIdx, String postContent) {
		AttachedFileExample attachedFileExample = new AttachedFileExample();
		attachedFileExample.createCriteria().andPostIdxIsNull();
		List<AttachedFile> attachedFiles = attachedFileDao.selectAttachedFile(attachedFileExample);
		
		List<String> attachedFileToDelete = new ArrayList<String>();
		for (AttachedFile attachedFile : attachedFiles) {
			if (!postContent.contains(attachedFile.getFakeName())) {
				attachedFileToDelete.add(attachedFile.getIdx());
				File file = new File(attachedFile.getRealPath() + File.separator + attachedFile.getFakeName());
				file.delete();
			} else {
				AttachedFile attachedFileToUpdate = new AttachedFile();
				attachedFileToUpdate.setIdx(attachedFile.getIdx());
				attachedFileToUpdate.setPostIdx(postIdx);
				attachedFileDao.updateAttachedFile(attachedFileToUpdate);
			}
		}
		
		if (!attachedFileToDelete.isEmpty()) {
			attachedFileExample.clear();
			attachedFileExample.createCriteria().andIdxIn(attachedFileToDelete);
			attachedFileDao.deleteAttachedFile(attachedFileExample);
		}
	}

}
