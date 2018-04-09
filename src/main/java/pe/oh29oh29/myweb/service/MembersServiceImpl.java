package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.MembersDao;
import pe.oh29oh29.myweb.model.Members;
import pe.oh29oh29.myweb.model.MembersExample;

@Service
public class MembersServiceImpl implements MembersService{
	
	@Autowired MembersDao membersDao;

	@Override
	public void signUpMember(Members member) {
		member.setIdx(Utils.generateIdx());
		member.setRegDate(Utils.generateNowGMTDate());
		membersDao.insertMember(member);
	}

	@Override
	public Members readMemberById(String id) {
		MembersExample example = new MembersExample();
		example.createCriteria().andIdEqualTo(id);
		List<Members> members = membersDao.selectMembers(example);
		return members.size() > 0 ? members.get(0) : null;
	}

	@Override
	public List<Members> readAllMembers() {
		return membersDao.selectMembers(null);
	}

	@Override
	public void modifyMember(Members member) {
		membersDao.updateMember(member);
	}

	@Override
	public void removeMemberById(String id) {
		MembersExample example = new MembersExample();
		example.createCriteria().andIdEqualTo(id);
		membersDao.deleteMember(example);
	}

	@Override
	public void withdrawMemberById(String idx) {
		Members member = new Members();
		member.setIdx(idx);
		member.setIsExit(1);
		membersDao.updateMember(member);
	}
}
