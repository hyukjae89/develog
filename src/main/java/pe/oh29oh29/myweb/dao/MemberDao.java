package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.MemberExample;

public interface MemberDao {
	
	public int insertMember(Member member);
	
	public List<Member> selectMember(MemberExample example);
	
	public int updateMember(Member member);
	
	public int deleteMember(MemberExample example);
	
	public int deleteAllMembers();

}
