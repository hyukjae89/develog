package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.MemberDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.dao.RelationDao;
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
	@Autowired CommentDao commentDao;
	@Autowired CommentService commentService;
	@Autowired RelationDao relationDao;
	
	private TestHelper testHelper;
	
	@Before
	public void setUp() throws Exception {
		commentDao.deleteAllComments();
		relationDao.deleteAllRelations();
		postDao.deleteAllPosts();
		categoryDao.deleteAllCategories();
		memberDao.deleteAllMembers();
		
		testHelper = new TestHelper(memberService, categoryService, postService, commentService);
	}

	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 테스트 (정상적인 경우) 
	 */
	@Test
	public void writePost() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		
		// 카테고리 생성
		Category category = testHelper.addCategory();
		
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
		assertEquals(post.getMemberIdx(), post2.getMemberIdx());
		assertEquals(post.getCategoryIdx(), post2.getCategoryIdx());
		assertEquals(post.getTitle(), post2.getTitle());
		assertEquals(post.getContents(), post2.getContents());
		assertNotNull(post2.getRegDate());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 2개
	 */
	@Test
	public void writePost2() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		
		// 카테고리 생성
		Category category = testHelper.addCategory();
		
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(member.getIdx());
		post.setCategoryIdx(category.getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Post post2 = new Post();
		post2.setMemberIdx(member.getIdx());
		post2.setCategoryIdx(category.getIdx());
		post2.setTitle("secondPostTest타이틀");
		post2.setContents("secondPostTest내용");
		// 연관 포스트 추가
		List<String> relatedPostIdxList = new ArrayList<String>();
		
		List<Post> posts = postService.readPosts(post.getCategoryIdx());
		assertEquals(1, posts.size());
		Post post3 = posts.get(0);
		
		relatedPostIdxList.add(post3.getIdx());
		postService.writePost(post2, relatedPostIdxList);
		
		// 검증
		List<Post> posts2 = postService.readPosts(post.getCategoryIdx());
		assertEquals(2, posts2.size());
		Post post4 = posts2.get(1);
		assertNotNull(post4.getIdx());
		assertEquals(post.getMemberIdx(), post4.getMemberIdx());
		assertEquals(post.getCategoryIdx(), post4.getCategoryIdx());
		assertEquals(post.getTitle(), post4.getTitle());
		assertEquals(post.getContents(), post4.getContents());
		assertNotNull(post4.getRegDate());
		Post post5 = posts2.get(0);
		assertNotNull(post5.getIdx());
		assertEquals(post2.getMemberIdx(), post5.getMemberIdx());
		assertEquals(post2.getCategoryIdx(), post5.getCategoryIdx());
		assertEquals(post2.getTitle(), post5.getTitle());
		assertEquals(post2.getContents(), post5.getContents());
		assertNotNull(post5.getRegDate());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 테스트 (유효하지 않는 회원일 경우) 
	 */
	@Test(expected=UncategorizedSQLException.class)
	public void writePostByInvalidMember() {
		// 카테고리 생성
		Category category = testHelper.addCategory();
		
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
		Category category = testHelper.addCategory();
		
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
		Member member = testHelper.signUpMember();
				
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
		Member member = testHelper.signUpMember();
		
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
	public void modifyPost() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		
		// 카테고리 생성
		Category category = testHelper.addCategory();
		
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(member.getIdx());
		post.setCategoryIdx(category.getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		postService.writePost(post);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Post post2 = new Post();
		post2.setMemberIdx(member.getIdx());
		post2.setCategoryIdx(category.getIdx());
		post2.setTitle("PostTest타이틀2");
		post2.setContents("PostTest내용2");
		postService.writePost(post2);
		
		List<Post> posts = postService.readPosts(category.getIdx());
		assertEquals(2, posts.size());
		Post post3 = posts.get(0);
		assertEquals(post2.getMemberIdx(), post3.getMemberIdx());
		assertEquals(post2.getCategoryIdx(), post3.getCategoryIdx());
		assertEquals(post2.getTitle(), post3.getTitle());
		assertEquals(post2.getContents(), post3.getContents());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<String> relatedPostIdxList = new ArrayList<String>();
		relatedPostIdxList.add(post3.getIdx());
		
		Post post4 = new Post();
		post4.setMemberIdx(member.getIdx());
		post4.setCategoryIdx(category.getIdx());
		post4.setTitle("PostTest타이틀3");
		post4.setContents("PostTest내용3");
		postService.writePost(post4, relatedPostIdxList);
				
		List<Post> posts2 = postService.readPosts(category.getIdx());
		assertEquals(3, posts2.size());
		
		// 포스트 수정
		Post post5 = posts2.get(0);
		post5.setTitle("PostTest타이틀수정");
		post5.setContents("PostTest내용수정");
		
		List<String> relatedPostIdxListForAdd = new ArrayList<String>();
		relatedPostIdxListForAdd.add(post3.getIdx());
		;List<String> relatedPostIdxListForDelete = new ArrayList<String>();
		relatedPostIdxListForDelete.add(post3.getIdx());
		
		postService.modifyPost(post5);
		
		// 검증
		List<Post> posts3 = postService.readPosts(post5.getCategoryIdx());
		assertEquals(3, posts3.size());
		Post post6 = posts3.get(0);
		assertEquals(post5.getTitle(), post6.getTitle());
		assertEquals(post5.getContents(), post6.getContents());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 삭제
	 */
	@Test
	public void removePost() {
		// 회원 가입
		Member member = testHelper.signUpMember();

		// 카테고리 생성
		Category category = testHelper.addCategory();

		// 포스트 작성
		Post post = testHelper.writePost(member.getIdx(), category.getIdx());
		
		List<String> relatedPostIdxList = new ArrayList<String>();
		relatedPostIdxList.add(post.getIdx());
		
		testHelper.writePostWithRelation(member.getIdx(), category.getIdx(), relatedPostIdxList);
		
		// 코멘트 작성
		testHelper.writeComment(member.getIdx(), post.getIdx());
		
		// 포스트 삭제
		postService.removePost(post.getIdx());
		
		// 검증
		List<Post> posts2 = postService.readPosts(post.getCategoryIdx());
		assertEquals(1, posts2.size());
	}
}
