package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.MemberMapper;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.MemberExample;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired MemberMapper memberMapper;
	
	@Override
	public int insertMember(Member member) {
		return memberMapper.insertSelective(member);
	}

	@Override
	public List<Member> selectMember(MemberExample example) {
		return memberMapper.selectByExample(example);
	}

	@Override
	public int updateMember(Member member) {
		return memberMapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public int deleteMember(MemberExample example) {
		return memberMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllMembers() {
		return memberMapper.deleteByExample(null);
	}

}
