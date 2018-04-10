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
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.service.MemberService;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MemberServiceTest {
	
	@Autowired MemberDao memberDao;
	@Autowired MemberService memberService;

	@Before
	public void setUp() throws Exception {
		memberDao.deleteAllMembers();
	}

	@Test
	public void signUpMember() {
		Member member = new Member();
		
		member.setId("hyukjae");
		member.setPasswd("1234");
		member.setName("권혁재");
		member.setEmail("hyukjae89@gmail.com");
		
		memberService.signUpMember(member);
		
		List<Member> members = memberService.readAllMembers();
		
		assertEquals(1, members.size());
		assertEquals("hyukjae", members.get(0).getId());
		assertEquals("1234", members.get(0).getPasswd());
		assertEquals("권혁재", members.get(0).getName());
		assertEquals("hyukjae89@gmail.com", members.get(0).getEmail());
	}
	
	@Test
	public void modifyMember() {
		signUpMember();
		
		List<Member> members = null;
		members = memberService.readAllMembers();
		assertEquals(1, members.size());
		
		Member member = new Member();
		member.setIdx(members.get(0).getIdx());
		member.setName("수정이름");
		
		memberService.modifyMember(member);
		members = memberService.readAllMembers();
		assertEquals("hyukjae", members.get(0).getId());
		assertEquals("수정이름", members.get(0).getName());

	}
	
	@Test
	public void removeMember() {
		signUpMember();
		List<Member> members = null;
		members = memberService.readAllMembers();
		assertEquals(1, members.size());
		
		memberService.removeMemberById(members.get(0).getId());
	
		members = memberService.readAllMembers();
		assertEquals(0, members.size());
	}
	
	@Test
	public void withdrawMember() {
		signUpMember();
		List<Member> members = null;
		members = memberService.readAllMembers();
		assertEquals(1, members.size());
		assertEquals(0, members.get(0).getIsExit().intValue());
		
		memberService.withdrawMemberById(members.get(0).getIdx());
		
		members = memberService.readAllMembers();
		assertEquals(1, members.get(0).getIsExit().intValue());
	}
}
