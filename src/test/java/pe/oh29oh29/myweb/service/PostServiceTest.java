package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostView;

public class PostServiceTest extends MemberServiceTest {

	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 작성 
	 */
	@Test
	public void writePost() {
		signUpMember();
		Member member = memberDao.selectMember(null).get(0);
		
		// 포스트 작성
		Post post = new Post();
		post.setMemberIdx(member.getIdx());
		post.setTitle("PostTest타이틀");
		post.setContents("PostTest내용");
		post.setDescription("PostTest설명");
		post.setUriId("post-test-uri-id");
		
		String tag = "post-write post-write-2";
		
		postService.writePost(post, tag);
		
		// 검증
		List<PostView> postsView = postService.getPosts(null, 1);
		assertEquals(1, postsView.size());
		PostView postView = postsView.get(0);
		assertEquals(post.getMemberIdx(), member.getIdx());
		assertEquals(post.getTitle(), postView.getTitle());
		assertEquals(post.getContents(), postView.getContents());
		assertEquals(post.getDescription(), postView.getDescription());
		assertEquals(post.getUriId(), postView.getUriId());
		
		tag = tag.replaceAll("\\s", ",");
		assertEquals(tag, postView.getTags());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 수정
	 */
	@Test
	public void modifyPost() {
		// 포스트 작성
		writePost();
		
		// 포스트 수정
		PostView post = postService.getPosts(null, 1).get(0);
		Member member = memberService.readAllMembers().get(0);
		Post updatePost = new Post();
		updatePost.setIdx(post.getIdx());
		updatePost.setMemberIdx(member.getIdx());
		updatePost.setTitle("PostTest타이틀수정");
		updatePost.setContents("PostTest내용수정");
		updatePost.setDescription("PostTest설명수정");
		updatePost.setUriId("post-test-update");
		
		String tag = "post-update post-write";
		
		postService.modifyPost(updatePost, tag);
		
		List<PostView> postsView = postService.getPosts(null, 1);
		assertEquals(1, postsView.size());
		PostView postView = postsView.get(0);
		
		assertEquals(updatePost.getTitle(), postView.getTitle());
		assertEquals(updatePost.getContents(), postView.getContents());
		assertEquals(updatePost.getDescription(), postView.getDescription());
		assertEquals(updatePost.getUriId(), postView.getUriId());
		
		tag = tag.replaceAll("\\s", ",");
		assertEquals(tag, postView.getTags());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 포스트 삭제
	 */
	@Test
	public void removePost() {
		// 포스트 작성
		writePost();
		
		List<PostView> posts = postService.getPosts(null, 1);
		Member member = memberService.readAllMembers().get(0);
		
		// 포스트 삭제
		postService.removePost(posts.get(0).getUriId(), member.getIdx());
		
		// 검증
		List<PostView> posts2 = postService.getPosts(null, 1);
		assertEquals(0, posts2.size());
	}
}
