package pe.oh29oh29.myweb.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.oh29oh29.myweb.common.Constants;
import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.FileStoreDao;
import pe.oh29oh29.myweb.model.FileStore;
import pe.oh29oh29.myweb.model.FileStoreExample;

@Service
public class AttachedFileServiceImpl implements AttachedFileService {

	@Autowired FileStoreDao fileStoreDao;
	
	@Override
	public String transfer(MultipartFile file, FileType type) throws Exception {
		String realFileFullName = file.getOriginalFilename();
		String realFileExt = realFileFullName.substring(realFileFullName.lastIndexOf(".") + 1, realFileFullName.length());
		String fakeFileFullName = Utils.generateIdx() + "." + realFileExt;
		
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
		
		return fileStore.getContextPath() + "/" + fakeFileFullName;
	}

}
