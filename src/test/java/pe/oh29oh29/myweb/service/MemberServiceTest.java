package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import pe.oh29oh29.myweb.model.Member;

public class MemberServiceTest extends TestSupport {
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 회원 가입
	 */
	@Test
	public void signUpMember() {
		Member member = new Member();
		member.setId("Member아이디");
		member.setName("Member이름");
		member.setEmail("hyukjae89@gmail.com");
		member.setIsAdmin(1);
		member.setProvider("GOOGLE");
		memberService.signUpMember(member);
		
		// 검증
		List<Member> members = memberService.readAllMembers();
		assertTrue(0 < members.size());
		Member member2 = members.get(0);
		assertEquals(member.getId(), member2.getId());
		assertEquals(member.getName(), member2.getName());
		assertEquals(member.getEmail(), member2.getEmail());
		assertEquals(member.getIsAdmin(), member2.getIsAdmin());
		assertEquals(member.getProvider(), member2.getProvider());
	}
/*	
	*//**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 회원 수정
	 *//*
	@Test
	public void modifyMember() {
		// 회원 추가
		signUpMember();
		
		// 회원 수정
		Member member = memberService.readAllMembers().get(0);
		member.setName("Member이름수정");
		member.setEmail("hyukjae89@gmail.com수정");
		memberService.modifyMember(member);
		
		// 검증
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());
		Member member2 = members.get(0);
		assertEquals(member.getName(), member2.getName());
		assertEquals(member.getEmail(), member2.getEmail());
	}
	
	*//**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 회원 삭제
	 *//*
	@Test
	public void removeMember() {
		// 회원 추가
		signUpMember();
		
		// 회원 삭제
		Member member = memberService.readAllMembers().get(0);
		memberService.removeMemberById(member.getId());
	
		// 검증
		List<Member> members = memberService.readAllMembers();
		assertEquals(0, members.size());
	}
	
	*//**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 회원 탈퇴
	 *//*
	@Test
	public void withdrawMember() {
		// 회원 추가
		signUpMember();
		
		Member member = memberService.readAllMembers().get(0);
		assertEquals(0, member.getIsExit().intValue());
		
		// 회원 탈퇴
		memberService.withdrawMemberById(member.getIdx());
		
		// 검증
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());
		Member member2 = members.get(0);
		assertEquals(1, member2.getIsExit().intValue());
	}*/
}
