package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.Members;
import pe.oh29oh29.myweb.model.MembersExample;

public interface MembersDao {
	
	public int insertMember(Members member);
	
	public Members selectMember(MembersExample example);
	
	public List<Members> selectMembers(MembersExample example);
	
	public int updateMember(Members member);
	
	public int deleteMember(MembersExample example);
	
	public int deleteAllMembers();

}
