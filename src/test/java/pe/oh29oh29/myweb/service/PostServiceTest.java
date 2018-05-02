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
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.MemberDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.dao.PostTagRelationDao;
import pe.oh29oh29.myweb.dao.TagDao;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.model.Tag;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PostServiceTest {

	@Autowired PostDao postDao;
	@Autowired PostService postService;
	@Autowired MemberDao memberDao;
	@Autowired MemberService memberService;
	@Autowired CommentDao commentDao;
	@Autowired CommentService commentService;
	@Autowired TagDao tagDao;
	@Autowired PostTagRelationDao postTagRelationDao;
	
	private Member member;
	
	/**
	 * @date	: 2018. 4. 10.
	 * @TODO	: 회원 생성
	 */
	private Member signUpMember() {
		Member member = new Member();
		member.setId("Member아이디");
		member.setName("Member이름");
		member.setEmail("Member이메일");
		memberService.signUpMember(member);
		
		// 검증
		List<Member> members = memberService.readAllMembers();
		assertEquals(1, members.size());
		Member member2 = members.get(0);
		assertEquals(member.getId(), member2.getId());
		assertEquals(member.getName(), member2.getName());
		assertEquals(member.getEmail(), member2.getEmail());
		
		return member2;
	}
	
	@Before
	public void setUp() throws Exception {
		postTagRelationDao.deleteAllPostTagRelations();
		tagDao.deleteAllTags();
		commentDao.deleteAllComments();
		postDao.deleteAllPosts();
		memberDao.deleteAllMembers();

		member = signUpMember();
	}

	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 테스트 (정상적인 경우) 
	 */
	@Test
	public void writePost() {
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(member.getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		post.setDescription("PostTest설명");
		post.setUriId("post-test-uri-id");
		
		String tag = "post-write-tag-name";
		
		postService.writePost(post, tag);
		
		// 검증
		List<PostView> posts = postService.getPosts();
		assertEquals(1, posts.size());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 수정
	 */
	@Test
	public void modifyPost() {
		// 포스트 작성
		writePost();
		
		// 작성한 포스트 가져오기
		List<PostView> posts = postService.getPosts();
		assertEquals(1, posts.size());
		
		// 포스트 수정
		PostView post = posts.get(0);
		
		Post updatePost = new Post();
		updatePost.setIdx(post.getIdx());
		updatePost.setTitle("PostTest타이틀수정");
		updatePost.setContents("PostTest내용수정");
		updatePost.setDescription("PostTest설명수정");
		updatePost.setUriId("post-test-update");
		
		String tag = "post-update-tag-name";
		
		postService.modifyPost(updatePost, tag);
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 삭제
	 */
	@Test
	public void removePost() {
		// 포스트 작성
		writePost();
		
		List<PostView> posts = postService.getPosts();
		
		// 포스트 삭제
//		postService.removePost(posts.get(0).getIdx());
		
		// 검증
		List<PostView> posts2 = postService.getPosts();
		assertEquals(0, posts2.size());
	}
}
