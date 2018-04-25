package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.FileStore;
import pe.oh29oh29.myweb.model.FileStoreExample;

public interface FileStoreDao {

	public int insertFileStore(FileStore fileStore);
	
	public int updateFileStore(FileStore fileStore);
	
	public int deleteFileStore(String idx);
	
	public int deleteAllFileStores();
	
	public List<FileStore> selectFileStore(FileStoreExample example);
}
