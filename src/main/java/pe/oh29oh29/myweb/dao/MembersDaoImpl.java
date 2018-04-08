package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.MembersMapper;
import pe.oh29oh29.myweb.model.Members;
import pe.oh29oh29.myweb.model.MembersExample;

@Repository
public class MembersDaoImpl implements MembersDao{

	@Autowired MembersMapper membersMapper;
	
	@Override
	public int insertMember(Members member) {
		return membersMapper.insertSelective(member);
	}

	@Override
	public Members selectMember(MembersExample example) {
		return null;
	}

	@Override
	public List<Members> selectMembers(MembersExample example) {
		return membersMapper.selectByExample(example);
	}

	@Override
	public int updateMember(Members member) {
		return membersMapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public int deleteMember(MembersExample example) {
		return membersMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllMembers() {
		return membersMapper.deleteByExample(null);
	}

}
