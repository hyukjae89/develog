package pe.oh29oh29.myweb.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.MemberDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.dao.PostTagRelationDao;
import pe.oh29oh29.myweb.dao.TagDao;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestSupport {

	@Autowired PostDao postDao;
	@Autowired PostService postService;
	@Autowired MemberDao memberDao;
	@Autowired MemberService memberService;
	@Autowired CommentDao commentDao;
	@Autowired CommentService commentService;
	@Autowired TagDao tagDao;
	@Autowired PostTagRelationDao postTagRelationDao;
	
	@Before
	public void setUp() throws Exception {
		postTagRelationDao.deleteAllPostTagRelations();
		tagDao.deleteAllTags();
		commentDao.deleteAllComments();
		postDao.deleteAllPosts();
		memberDao.deleteAllMembers();
	}

}
