package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

/**
 * @author hyukj
 *
 */
/**
 * @author hyukj
 *
 */
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
	
	Member member;
	Category category;
	
	/**
	 * @date	: 2018. 4. 10.
	 * @TODO	: 회원 생성
	 */
	private void signUpMember() {
		member.setId("PostTestMember아이디");
		member.setPasswd("1234");
		member.setName("PostTestMember이름");
		member.setEmail("PostTestMember이메일");
		memberService.signUpMember(member);
		
		// 검증
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());
		Member member2 = members.get(0);
		assertEquals("PostTestMember아이디", member2.getId());
		assertEquals("1234", member2.getPasswd());
		assertEquals("PostTestMember이름", member2.getName());
		assertEquals("PostTestMember이메일", member2.getEmail());
		
		member = member2;
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 생성
	 */
	private void addCategory() {
		category.setName("PostTestCategory이름");
		categoryService.addCategory(category);
		
		// 검증
		List<Category> categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		Category category2 = categories.get(0);
		assertEquals("PostTestCategory이름", category2.getName());
		
		category = category2;
	}
	
	@Before
	public void setUp() throws Exception {
		postDao.deleteAllPosts();
		categoryDao.deleteAllCategories();
		memberDao.deleteAllMembers();
		
		member = new Member();
		category = new Category();
	}

	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 테스트 (정상적인 경우) 
	 */
	@Test
	public void writePost() {
		// 회원 생성
		signUpMember();
		
		// 카테고리 생성
		addCategory();
		
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(member.getIdx());
		post.setCategoryIdx(category.getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
		
		// 검증
		List<Post> posts = postService.readPosts(post.getCategoryIdx());
		assertEquals(1, posts.size());
		Post post2 = posts.get(0);
		assertNotNull(post2.getIdx());
		assertEquals(member.getIdx(), post2.getMemberIdx());
		assertEquals(category.getIdx(), post2.getCategoryIdx());
		assertEquals("PostTest타이틀", post2.getTitle());
		assertEquals("PostTest내용", post2.getContents());
		assertNotNull(post2.getRegDate());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 테스트 (유효하지 않는 회원일 경우) 
	 */
	@Test(expected=UncategorizedSQLException.class)
	public void writePostByInvalidMember() {
		// 카테고리 생성
		addCategory();
		
		// 포스트 작성
		Post post = new Post();
		post.setCategoryIdx(category.getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 테스트 (유효하지 않는 회원일 경우) 
	 */
	@Test(expected=DataIntegrityViolationException.class)
	public void writePostByInvalidMember2() {
		// 카테고리 생성
		addCategory();
		
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(Utils.generateIdx());
		post.setCategoryIdx(category.getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 테스트 (유효하지 않는 카테고리일 경우) 
	 */
	@Test(expected=UncategorizedSQLException.class)
	public void writePostByInvalidCategory() {
		// 회원 생성
		signUpMember();
				
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(member.getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 테스트 (유효하지 않는 카테고리일 경우) 
	 */
	@Test(expected=DataIntegrityViolationException.class)
	public void writePostByInvalidCategory2() {
		// 회원 생성
		signUpMember();
		
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(member.getIdx());
		post.setCategoryIdx(Utils.generateIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 수정
	 */
	@Test
	public void updatePost() {
		// 포스트 작성
		writePost();
		
		List<Post> posts = postService.readPosts(category.getIdx());
		assertEquals(1, posts.size());
		
		// 포스트 수정
		Post post2 = posts.get(0);
		post2.setTitle("PostTest타이틀수정");
		post2.setContents("PostTest내용수정");
		postService.modifyPost(post2);
		
		// 검증
		List<Post> posts2 = postService.readPosts(post2.getCategoryIdx());
		assertEquals(1, posts2.size());
		Post post3 = posts2.get(0);
		assertEquals("PostTest타이틀수정", post3.getTitle());
		assertEquals("PostTest내용수정", post3.getContents());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 삭제
	 */
	@Test
	public void removePost() {
		// 포스트 작성
		writePost();
		
		List<Post> posts = postService.readPosts(category.getIdx());
		assertEquals(1, posts.size());
		
		// 포스트 삭제
		Post post2 = posts.get(0);
		postService.removePost(post2.getIdx());
		
		// 검증
		List<Post> posts2 = postService.readPosts(post2.getCategoryIdx());
		assertEquals(0, posts2.size());
	}
}
