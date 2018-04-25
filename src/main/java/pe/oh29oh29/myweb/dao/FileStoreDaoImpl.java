package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.FileStoreMapper;
import pe.oh29oh29.myweb.model.FileStore;
import pe.oh29oh29.myweb.model.FileStoreExample;

@Repository
public class FileStoreDaoImpl implements FileStoreDao {

	@Autowired FileStoreMapper fileStoreMapper;
	
	@Override
	public int insertFileStore(FileStore fileStore) {
		return fileStoreMapper.insertSelective(fileStore);
	}

	@Override
	public int updateFileStore(FileStore fileStore) {
		return fileStoreMapper.updateByPrimaryKeySelective(fileStore);
	}

	@Override
	public int deleteFileStore(String idx) {
		return fileStoreMapper.deleteByPrimaryKey(idx);
	}

	@Override
	public int deleteAllFileStores() {
		return fileStoreMapper.deleteByExample(null);
	}

	@Override
	public List<FileStore> selectFileStore(FileStoreExample example) {
		return fileStoreMapper.selectByExample(example);
	}
}
