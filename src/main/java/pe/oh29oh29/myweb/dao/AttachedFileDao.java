package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.AttachedFile;
import pe.oh29oh29.myweb.model.AttachedFileExample;

public interface AttachedFileDao {

	public int insertAttachedFile(AttachedFile attachedFile);
	
	public int updateAttachedFile(AttachedFile attachedFile);
	
	public int deleteAttachedFile(AttachedFileExample example);
	
	public int deleteAllAttachedFiles();
	
	public List<AttachedFile> selectAttachedFile(AttachedFileExample example);
}
