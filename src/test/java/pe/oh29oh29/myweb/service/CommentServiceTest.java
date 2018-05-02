package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import pe.oh29oh29.myweb.model.Comment;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.PostView;

public class CommentServiceTest extends PostServiceTest {

	/**
	 * @date	: 2018. 4. 12.
	 * @TODO	: 코멘트 작성 
	 */
	@Test
	public void writeComment() {
		writePost();
		
		Member member = memberService.readAllMembers().get(0);
		PostView post = postService.getPosts(null).get(0);
		
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
	 * @date	: 2018. 4. 13.
	 * @TODO	: 코멘트 수정
	 */
	@Test
	public void modifyComment() {
		writeComment();
		
		PostView post = postService.getPosts(null).get(0);
		Comment comment = commentService.readComments(post.getIdx()).get(0);
		
		// 코멘트 수정
		comment.setContents("Comment내용수정");
		commentService.modifyComment(comment);
		
		// 검증
		List<Comment> comments = commentService.readComments(comment.getPostIdx());
		assertEquals(1, comments.size());
		Comment comment2 = comments.get(0);
		assertEquals(comment.getContents(), comment2.getContents());
	}
	
	@Test
	public void removeComment() {
		writeComment();

		PostView post = postService.getPosts(null).get(0);
		Comment comment = commentService.readComments(post.getIdx()).get(0);
		
		commentService.removeComment(comment.getIdx());
		
		// 검증
		List<Comment> comments = commentService.readComments(comment.getPostIdx());
		assertEquals(0, comments.size());
	}
}
