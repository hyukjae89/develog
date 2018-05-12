package pe.oh29oh29.myweb.controller.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pe.oh29oh29.myweb.common.Constants;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.model.Tag;
import pe.oh29oh29.myweb.service.AttachedFileService;
import pe.oh29oh29.myweb.service.AttachedFileService.FileType;
import pe.oh29oh29.myweb.service.PostService;
import pe.oh29oh29.myweb.service.TagService;

@Controller
public class PostController {

	@Autowired PostService postService;
	@Autowired AttachedFileService attachedFileService;
	@Autowired TagService tagService;
	
	
	/**
	 * @date	: 2018. 5. 1.
	 * @TODO	: 포스트 리스트 가져오기 (비동기)
	 */
	@RequestMapping(value = "async/posts/tags/*", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPosts(@RequestParam(value = "tag", required = false) String tag, @RequestParam(value = "nowPage") int nowPage) {
		
		List<PostView> posts = postService.getPosts(tag, nowPage);
		Map<String, Object> paginationInfo = postService.getPostListPaginationInfo(tag);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("posts", posts);
		result.putAll(paginationInfo);
		
		return result;
	}
	
	/**
	 * @date	: 2018. 5. 1.
	 * @TODO	: 포스트 리스트 가져오기
	 */
	@RequestMapping(value = {"posts", "posts/tags/*"}, method = RequestMethod.GET)
	public String getPosts(HttpServletRequest httpRequest, Model model) {
		String servletPath = httpRequest.getServletPath();
		String tag = null;
		
		if (servletPath.indexOf("tags") > -1) {
			tag = servletPath.substring(servletPath.lastIndexOf("/") + 1);
			model.addAttribute("tag", tag);
		}
		
		model.addAttribute("view", "list");
		
		return "blog/home";
	}
	
	/**
	 * @date	: 2018. 5. 1.
	 * @TODO	: 포스트 읽기 (비동기)
	 */
	@RequestMapping(value = "async/posts/*", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> readPost(HttpServletRequest httpRequest, @RequestParam(value = "uriId") String uriId) {
		Member member = (Member) httpRequest.getSession().getAttribute(Constants.SESSION_MEMBER);
		PostView post = postService.getPost(uriId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("post", post);
		if (member == null) {
			result.put("isWriter", false);
		} else {
			result.put("isWriter", member.getIdx().equals(post.getMemberIdx()));
		}
		return result;
	}
	
	/**
	 * @date	: 2018. 5. 1.
	 * @TODO	: 포스트 읽기
	 */
	@RequestMapping(value = "posts/*", method = RequestMethod.GET)
	public String readPost(HttpServletRequest httpRequest, Model model) {
		String servletPath = httpRequest.getServletPath();
		String uriId = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		model.addAttribute("uriId", uriId);
		model.addAttribute("view", "read");
		return "blog/home";
	}
	
	/**
	 * @date	: 2018. 4. 26.
	 * @TODO	: 포스트 작성 완료 (비동기)
	 */
	@RequestMapping(value = "async/posts", method = RequestMethod.POST)
	@ResponseBody
	public String wrtiePost(HttpServletRequest httpRequest, Post post, String tags) throws Exception {
		Member member = (Member) httpRequest.getSession().getAttribute(Constants.SESSION_MEMBER);
		
		if (member == null)
			throw new Exception();
		
		post.setMemberIdx(member.getIdx());
		
		postService.writePost(post, tags);
		
		return post.getUriId();
	}
	
	/**
	 * @date	: 2018. 4. 25.
	 * @TODO	: 포스트 작성 시, 이미지 업로드
	 */
	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	public String uploadImage(MultipartHttpServletRequest multipartRequest) {
		MultipartFile file = multipartRequest.getFile("Filedata");
		String callback = multipartRequest.getParameter("callback");
		String callbackFunc = multipartRequest.getParameter("callback_func");
		String fileURL = multipartRequest.getScheme() + "://" + multipartRequest.getServerName() + ":" + multipartRequest.getServerPort() + "/";
		try {
			fileURL += attachedFileService.transfer(file, FileType.IMAGE_IN_CONTENTS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuffer sb = new StringBuffer();
		sb.append("&bNewLine=true")
			.append("&sFileName=").append(file.getOriginalFilename())
			.append("&sFileURL=").append(fileURL);

         return "redirect:" + callback + "?callback_func=" + callbackFunc + sb.toString(); 
	}
	
	/**
	 * @date	: 2018. 5. 3.
	 * @TODO	: 포스트 삭제하기 (비동기)
	 */
	@RequestMapping(value = "async/posts/*", method = RequestMethod.DELETE)
	@ResponseBody
	public void removePost(HttpServletRequest httpRequest, String uriId) throws Exception {
		Member member = (Member) httpRequest.getSession().getAttribute(Constants.SESSION_MEMBER);
		
		if (member == null)
			throw new Exception();
		
		postService.removePost(uriId, member.getIdx());
	}
	
	/**
	 * @date	: 2018. 5. 5.
	 * @TODO	: 포스트 수정 완료 (비동기)
	 */
	@RequestMapping(value = "async/posts", method = RequestMethod.PUT)
	@ResponseBody
	public String modifyPost(HttpServletRequest httpRequest, Post post, String tags) throws Exception {
		Member member = (Member) httpRequest.getSession().getAttribute(Constants.SESSION_MEMBER);
		
		post.setMemberIdx(member.getIdx());

		postService.modifyPost(post, tags);
		
		return post.getUriId();
	}
	
	/**
	 * @date	: 2018. 5. 13.
	 * @TODO	: 태그 조회하기 (비동기)
	 */
	@RequestMapping(value = "async/tags/*", method = RequestMethod.GET)
	@ResponseBody
	public List<Tag> getTags(@RequestParam(value = "tag") String tag) {
		
		List<Tag> tags = tagService.getTags(tag);
		
		return tags;
	}
}
