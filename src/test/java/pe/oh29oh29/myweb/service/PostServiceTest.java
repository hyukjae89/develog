package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CategoryDao;
import pe.oh29oh29.myweb.dao.MemberDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.Post;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PostServiceTest {

	@Autowired PostDao postDao;
	@Autowired PostService postService;
	@Autowired CategoryDao categoryDao;
	@Autowired CategoryService categoryService;
	@Autowired MemberDao memberDao;
	@Autowired MemberService memberService;
	
	@Before
	public void setUp() throws Exception {
		postDao.deleteAllPosts();
		categoryDao.deleteAllCategories();
		memberDao.deleteAllMembers();
	}

	// 포스트 작성 테스트 (정상적인 경우)
	@Test
	public void writePost() {
		// 회원 생성
		Member member = new Member();
		member.setId("PostTestMember아이디");
		member.setPasswd("1234");
		member.setName("PostTestMember이름");
		member.setEmail("PostTestMember이메일");
		memberService.signUpMember(member);
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());
		assertEquals("PostTestMember아이디", members.get(0).getId());
		assertEquals("1234", members.get(0).getPasswd());
		assertEquals("PostTestMember이름", members.get(0).getName());
		assertEquals("PostTestMember이메일", members.get(0).getEmail());
		
		// 카테고리 생성
		Category category = new Category();
		category.setName("PostTestCategory이름");
		categoryService.addCategory(category);
		List<Category> categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		assertEquals("PostTestCategory이름", categories.get(0).getName());
		
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(members.get(0).getIdx());
		post.setCategoryIdx(categories.get(0).getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
		List<Post> posts = postService.readPosts(post.getCategoryIdx());
		assertEquals(1, posts.size());
		assertEquals("PostTest타이틀", posts.get(0).getTitle());
		assertEquals("PostTest내용", posts.get(0).getContents());
	}
	
	// 포스트 작성 테스트 (유효하지 않는 회원일 경우)
	@Test(expected=UncategorizedSQLException.class)
	public void writePostByInvalidMember() {
		// 카테고리 생성
		Category category = new Category();
		category.setName("PostTestCategory이름");
		categoryService.addCategory(category);
		List<Category> categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		assertEquals("PostTestCategory이름", categories.get(0).getName());
		
		// 포스트 작성
		Post post = new Post();
		post.setCategoryIdx(categories.get(0).getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
	}
	
	// 포스트 작성 테스트 (유효하지 않는 회원일 경우)
	@Test(expected=DataIntegrityViolationException.class)
	public void writePostByInvalidMember2() {
		// 카테고리 생성
		Category category = new Category();
		category.setName("PostTestCategory이름");
		categoryService.addCategory(category);
		List<Category> categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		assertEquals("PostTestCategory이름", categories.get(0).getName());
		
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(Utils.generateIdx());
		post.setCategoryIdx(categories.get(0).getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
	}
}
