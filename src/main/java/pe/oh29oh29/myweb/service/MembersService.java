package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Members;

public interface MembersService {

	/**
	 * @date	: 2018. 4. 8.
	 * @TODO	: 회원 가입
	 */
	public void signUpMember(Members member);
	
	/**
	 * @date	: 2018. 4. 8.
	 * @TODO	: 회원 조회 by ID
	 */
	public Members readMemberById(String id);
	
	/**
	 * @date	: 2018. 4. 8.
	 * @TODO	: 회원 조회 (ALL)
	 */
	public List<Members> readAllMembers();
	
	/**
	 * @date	: 2018. 4. 8.
	 * @TODO	: 회원 수정
	 */
	public void modifyMember(Members member);
	
	/**
	 * @date	: 2018. 4. 8.
	 * @TODO	: 회원 삭제
	 */
	public void removeMemberById(String id);
	
	/**
	 * @date	: 2018. 4. 8.
	 * @TODO	: 회원 탈퇴
	 */
	public void withdrawMemberById(String id);
}
