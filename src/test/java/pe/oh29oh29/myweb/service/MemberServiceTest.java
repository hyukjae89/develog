package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import pe.oh29oh29.myweb.dao.MemberDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.service.MemberService;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MemberServiceTest {
	
	@Autowired MemberDao memberDao;
	@Autowired MemberService memberService;
	@Autowired PostDao postDao;

	@Before
	public void setUp() throws Exception {
		postDao.deleteAllPosts();
		memberDao.deleteAllMembers();
	}

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
		memberService.signUpMember(member);
		
		// 검증
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());
		Member member2 = members.get(0);
		assertEquals("Member아이디", member2.getId());
		assertEquals("Member이름", member2.getName());
		assertEquals("hyukjae89@gmail.com", member2.getEmail());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 회원 수정
	 */
	@Test
	public void modifyMember() {
		// 회원 추가
		signUpMember();
		
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());

		// 회원 수정
		Member member = members.get(0);
		member.setName("Member이름수정");
		member.setEmail("hyukjae89@gmail.com수정");
		memberService.modifyMember(member);
		
		// 검증
		List<Member> members2 = memberService.readAllMembers();
		assertEquals(1, members2.size());
		Member member2 = members2.get(0);
		assertEquals("Member아이디", member2.getId());
		assertEquals("Member이름수정", member2.getName());
		assertEquals("hyukjae89@gmail.com수정", member2.getEmail());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 회원 삭제
	 */
	@Test
	public void removeMember() {
		// 회원 추가
		signUpMember();
		
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());
		
		// 회원 삭제
		Member member = members.get(0);
		memberService.removeMemberById(member.getId());
	
		// 검증
		List<Member> members2 = memberService.readAllMembers();
		assertEquals(0, members2.size());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 회원 탈퇴
	 */
	@Test
	public void withdrawMember() {
		// 회원 추가
		signUpMember();
		
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());
		Member member = members.get(0);
		assertEquals(0, member.getIsExit().intValue());
		
		// 회원 탈퇴
		memberService.withdrawMemberById(member.getIdx());
		
		// 검증
		List<Member> members2 = memberService.readAllMembers();
		assertEquals(1, members2.size());
		Member member2 = members2.get(0);
		assertEquals(1, member2.getIsExit().intValue());
	}
}
