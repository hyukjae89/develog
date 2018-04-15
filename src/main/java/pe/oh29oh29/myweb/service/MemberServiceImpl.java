package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.MemberDao;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.MemberExample;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired MemberDao memberDao;

	@Override
	public void signUpMember(Member member) {
		member.setIdx(Utils.generateIdx());
		member.setRegDate(Utils.generateNowGMTDate());
		memberDao.insertMember(member);
	}

	@Override
	public Member readMemberById(String id) {
		MemberExample example = new MemberExample();
		example.createCriteria().andIdEqualTo(id);
		List<Member> member = memberDao.selectMember(example);
		return member.size() > 0 ? member.get(0) : null;
	}

	@Override
	public List<Member> readAllMembers() {
		return memberDao.selectMember(null);
	}

	@Override
	public void modifyMember(Member member) {
		memberDao.updateMember(member);
	}

	@Override
	public void removeMemberById(String id) {
		MemberExample example = new MemberExample();
		example.createCriteria().andIdEqualTo(id);
		memberDao.deleteMember(example);
	}

	@Override
	public void withdrawMemberById(String idx) {
		Member member = new Member();
		member.setIdx(idx);
		member.setIsExit(1);
		member.setExitDate(Utils.generateNowGMTDate());
		memberDao.updateMember(member);
	}
}
