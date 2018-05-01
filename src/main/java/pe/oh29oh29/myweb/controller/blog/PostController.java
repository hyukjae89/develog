package pe.oh29oh29.myweb.controller.blog;

import java.util.List;

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
import pe.oh29oh29.myweb.service.AttachedFileService;
import pe.oh29oh29.myweb.service.AttachedFileService.FileType;
import pe.oh29oh29.myweb.service.PostService;

@Controller
public class PostController {

	@Autowired PostService postService;
	@Autowired AttachedFileService attachedFileService;
	
	
	/**
	 * @date	: 2018. 5. 1.
	 * @TODO	: 포스트 리스트 가져오기 (비동기)
	 */
	@RequestMapping(value = "async/posts", method = RequestMethod.GET)
	@ResponseBody
	public List<PostView> getPosts(@RequestParam(value = "tag", required = false) String tag) {
		List<PostView> posts = postService.getPosts(tag);
		return posts;
	}
	
	/**
	 * @date	: 2018. 5. 1.
	 * @TODO	: 포스트 리스트 가져오기
	 */
	@RequestMapping(value = "posts/tags/*", method = RequestMethod.GET)
	public String getPosts(HttpServletRequest httpRequest, Model model) {
		String servletPath = httpRequest.getServletPath();
		String tag = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		model.addAttribute("tag", tag);
		model.addAttribute("view", "list");
		return "blog/home";
	}
	
	/**
	 * @date	: 2018. 5. 1.
	 * @TODO	: 포스트 읽기 (비동기)
	 */
	@RequestMapping(value = "async/posts/*", method = RequestMethod.GET)
	@ResponseBody
	public PostView readPost(@RequestParam(value = "uriId") String uriId) {
		PostView post = postService.getPost(uriId);
		return post;
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
	 * @TODO	: 포스트 작성 뷰
	 */
	@RequestMapping(value = "posts", method = RequestMethod.GET)
	public String postWriteView(Model model) {
		return "blog/post/write";
	}
	
	/**
	 * @date	: 2018. 4. 26.
	 * @TODO	: 포스트 작성 완료
	 */
	@RequestMapping(value = "posts", method = RequestMethod.POST)
	public String wrtiePost(HttpServletRequest httpRequest, Post post, String categoryName) throws Exception {
		Member member = (Member) httpRequest.getSession().getAttribute(Constants.SESSION_MEMBER);
		
		post.setMemberIdx(member.getIdx());
		
		postService.writePost(post);
		
		return "redirect:/";
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
}
