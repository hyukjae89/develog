package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.AttachedFileMapper;
import pe.oh29oh29.myweb.model.AttachedFile;
import pe.oh29oh29.myweb.model.AttachedFileExample;

@Repository
public class AttachedFileDaoImpl implements AttachedFileDao {

	@Autowired AttachedFileMapper AttachedFileMapper;
	
	@Override
	public int insertAttachedFile(AttachedFile attachedFile) {
		return AttachedFileMapper.insertSelective(attachedFile);
	}

	@Override
	public int updateAttachedFile(AttachedFile attachedFile) {
		return AttachedFileMapper.updateByPrimaryKeySelective(attachedFile);
	}

	@Override
	public int deleteAttachedFile(AttachedFileExample example) {
		return AttachedFileMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllAttachedFiles() {
		return AttachedFileMapper.deleteByExample(null);
	}

	@Override
	public List<AttachedFile> selectAttachedFile(AttachedFileExample example) {
		return AttachedFileMapper.selectByExample(example);
	}

}
