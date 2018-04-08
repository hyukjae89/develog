package service;

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

@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}
)
public class MembersServiceTest {
	
	@Autowired MembersDao membersDao;
	@Autowired MembersService membersService;

	@Before
	public void setUp() throws Exception {
		membersDao.deleteAllMembers();
	}

	@Test
	public void test() {
		Members member = new Members();
		
		member.setId("hyukjae");
		member.setPasswd("1234");
		member.setName("권혁재");
		member.setEmail("hyukjae89@gmail.com");
		
		membersService.signUpMember(member);
		
		List<Members> members = membersService.readMembers();
		
		assertEquals(members.size(), 1);
	}
}
