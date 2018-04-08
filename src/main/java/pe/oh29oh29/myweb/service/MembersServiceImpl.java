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
	public Members readMember(String id) {
		return null;
	}

	@Override
	public List<Members> readMembers() {
		return membersDao.selectMembers(null);
	}

	@Override
	public void modifyMember(Members member) {
		membersDao.updateMember(member);
	}

	@Override
	public void removeMember(String id) {
		MembersExample example = new MembersExample();
		example.createCriteria().andIdEqualTo(id);
		membersDao.deleteMember(example);
	}

	@Override
	public void withdrawMember(String id) {
		Members member = new Members();
		member.setId(id);
		member.setIsExit(1);
		membersDao.updateMember(member);
	}
}
