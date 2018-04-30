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
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.MemberDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.model.Comment;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.Post;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CommentServiceTest {

	@Autowired CommentDao commentDao;
	@Autowired CommentService commentService;
	@Autowired PostDao postDao;
	@Autowired PostService postService;
	@Autowired MemberDao memberDao;
	@Autowired MemberService memberService;
	
	private TestHelper testHelper;
	
	@Before
	public void setUp() throws Exception {
		commentDao.deleteAllComments();
		postDao.deleteAllPosts();
		memberDao.deleteAllMembers();
		
		testHelper = new TestHelper(memberService, postService);
	}

	/**
	 * @date	: 2018. 4. 12.
	 * @TODO	: 코멘트 작성 
	 */
	@Test
	public void writeComment() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		// 카테고리 추가
		Category category = testHelper.addCategory();
		// 포스트 작성
		Post post = testHelper.writePost(member.getIdx(), category.getIdx());
		
		// 코멘트 작성
		Comment comment = new Comment();
		comment.setMemberIdx(member.getIdx());
		comment.setPostIdx(post.getIdx());
		comment.setContents("Comment내용");
		commentService.writeComment(comment);
		
		// 검증
		List<Comment> comments = commentService.readComments(comment.getPostIdx());
		assertEquals(1, comments.size());
		Comment comment2 = comments.get(0);
		assertNotNull(comment2.getIdx());
		assertEquals(comment.getMemberIdx(), comment2.getMemberIdx());
		assertEquals(comment.getPostIdx(), comment2.getPostIdx());
		assertEquals(comment.getContents(), comment2.getContents());
		assertNotNull(comment2.getRegDate());
		
	}
	
	/**
	 * @date	: 2018. 4. 12.
	 * @TODO	: 코멘트 작성 (유효하지 않는 회원일 경우)
	 */
	@Test(expected=UncategorizedSQLException.class)
	public void writeCommentByInvalidMember() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		// 카테고리 추가
		Category category = testHelper.addCategory();
		// 포스트 작성
		Post post = testHelper.writePost(member.getIdx(), category.getIdx());
		
		// 코멘트 작성
		Comment comment = new Comment();
		comment.setPostIdx(post.getIdx());
		comment.setContents("Comment내용");
		commentService.writeComment(comment);
	}
	
	/**
	 * @date	: 2018. 4. 12.
	 * @TODO	: 코멘트 작성 (유효하지 않는 회원일 경우)
	 */
	@Test(expected=DataIntegrityViolationException.class)
	public void writeCommentByInvalidMember2() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		// 카테고리 추가
		Category category = testHelper.addCategory();
		// 포스트 작성
		Post post = testHelper.writePost(member.getIdx(), category.getIdx());
		
		// 코멘트 작성
		Comment comment = new Comment();
		comment.setMemberIdx(Utils.generateIdx());
		comment.setPostIdx(post.getIdx());
		comment.setContents("Comment내용");
		commentService.writeComment(comment);
	}
	
	/**
	 * @date	: 2018. 4. 12.
	 * @TODO	: 코멘트 작성 (유효하지 않는 포스트일 경우)
	 */
	@Test(expected=UncategorizedSQLException.class)
	public void writeCommentByInvalidPost() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		
		// 코멘트 작성
		Comment comment = new Comment();
		comment.setMemberIdx(member.getIdx());
		comment.setContents("Comment내용");
		commentService.writeComment(comment);
	}
	
	/**
	 * @date	: 2018. 4. 12.
	 * @TODO	: 코멘트 작성 (유효하지 않는 포스트일 경우)
	 */
	@Test(expected=DataIntegrityViolationException.class)
	public void writeCommentByInvalidPost2() {
		// 회원 생성
		Member member = testHelper.signUpMember();

		// 코멘트 작성
		Comment comment = new Comment();
		comment.setMemberIdx(member.getIdx());
		comment.setPostIdx(Utils.generateIdx());
		comment.setContents("Comment내용");
		commentService.writeComment(comment);
	}
	
	/**
	 * @date	: 2018. 4. 13.
	 * @TODO	: 코멘트 수정
	 */
	@Test
	public void modifyComment() {
		// 회원 가입
		Member member = testHelper.signUpMember();

		// 카테고리 생성
		Category category = testHelper.addCategory();

		// 포스트 작성
		Post post = testHelper.writePost(member.getIdx(), category.getIdx());
		
		// 코멘트 작성
		Comment comment = new Comment();
		comment.setMemberIdx(member.getIdx());
		comment.setPostIdx(post.getIdx());
		comment.setContents("Comment내용");
		commentService.writeComment(comment);
		
		// 검증
		List<Comment> comments = commentService.readComments(comment.getPostIdx());
		assertEquals(1, comments.size());
		Comment comment2 = comments.get(0);
		assertNotNull(comment2.getIdx());
		assertEquals(comment.getMemberIdx(), comment2.getMemberIdx());
		assertEquals(comment.getPostIdx(), comment2.getPostIdx());
		assertEquals(comment.getContents(), comment2.getContents());
		assertNotNull(comment2.getRegDate());
		
		// 코멘트 수정
		comment2.setContents("Comment내용수정");
		commentService.modifyComment(comment2);
		// 검증
		List<Comment> comments2 = commentService.readComments(comment2.getPostIdx());
		assertEquals(1, comments2.size());
		Comment comment3 = comments2.get(0);
		assertEquals(comment2.getContents(), comment3.getContents());
	}
	
	@Test
	public void removeComment() {
		// 회원 가입
		Member member = testHelper.signUpMember();
	
		// 포스트 작성
		Post post = testHelper.writePost(member.getIdx());
		
		// 코멘트 작성
		Comment comment = new Comment();
		comment.setMemberIdx(member.getIdx());
		comment.setPostIdx(post.getIdx());
		comment.setContents("Comment내용");
		commentService.writeComment(comment);
		
		// 검증
		List<Comment> comments = commentService.readComments(comment.getPostIdx());
		assertEquals(1, comments.size());
		Comment comment2 = comments.get(0);
		assertNotNull(comment2.getIdx());
		assertEquals(comment.getMemberIdx(), comment2.getMemberIdx());
		assertEquals(comment.getPostIdx(), comment2.getPostIdx());
		assertEquals(comment.getContents(), comment2.getContents());
		assertNotNull(comment2.getRegDate());
		
		commentService.removeComment(comment2.getIdx());
		
		// 검증
		List<Comment> comments2 = commentService.readComments(comment2.getPostIdx());
		assertEquals(0, comments2.size());
	}
}
