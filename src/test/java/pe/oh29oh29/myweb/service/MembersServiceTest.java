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

import pe.oh29oh29.myweb.dao.MembersDao;
import pe.oh29oh29.myweb.model.Members;
import pe.oh29oh29.myweb.service.MembersService;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MembersServiceTest {
	
	@Autowired MembersDao membersDao;
	@Autowired MembersService membersService;

	@Before
	public void setUp() throws Exception {
		membersDao.deleteAllMembers();
	}

	@Test
	public void signUpMember() {
		Members member = new Members();
		
		member.setId("hyukjae");
		member.setPasswd("1234");
		member.setName("권혁재");
		member.setEmail("hyukjae89@gmail.com");
		
		membersService.signUpMember(member);
		
		List<Members> members = membersService.readAllMembers();
		
		assertEquals(1, members.size());
		assertEquals("hyukjae", members.get(0).getId());
		assertEquals("1234", members.get(0).getPasswd());
		assertEquals("권혁재", members.get(0).getName());
		assertEquals("hyukjae89@gmail.com", members.get(0).getEmail());
	}
	
	@Test
	public void modifyMember() {
		signUpMember();
		
		List<Members> members = null;
		members = membersService.readAllMembers();
		assertEquals(1, members.size());
		
		Members member = new Members();
		member.setIdx(members.get(0).getIdx());
		member.setName("수정이름");
		
		membersService.modifyMember(member);
		members = membersService.readAllMembers();
		assertEquals("hyukjae", members.get(0).getId());
		assertEquals("수정이름", members.get(0).getName());

	}
	
	@Test
	public void removeMember() {
		signUpMember();
		List<Members> members = null;
		members = membersService.readAllMembers();
		assertEquals(1, members.size());
		
		membersService.removeMemberById(members.get(0).getId());
	
		members = membersService.readAllMembers();
		assertEquals(0, members.size());
	}
	
	@Test
	public void withdrawMember() {
		signUpMember();
		List<Members> members = null;
		members = membersService.readAllMembers();
		assertEquals(1, members.size());
		assertEquals(0, members.get(0).getIsExit().intValue());
		
		membersService.withdrawMemberById(members.get(0).getIdx());
		
		members = membersService.readAllMembers();
		assertEquals(1, members.get(0).getIsExit().intValue());
	}
}
