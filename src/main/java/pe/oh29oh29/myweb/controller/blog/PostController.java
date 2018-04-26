package pe.oh29oh29.myweb.controller.blog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pe.oh29oh29.myweb.common.Constants;
import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.service.AttachedFileService;
import pe.oh29oh29.myweb.service.AttachedFileService.FileType;
import pe.oh29oh29.myweb.service.CategoryService;
import pe.oh29oh29.myweb.service.CategoryService.AccessSpecifier;
import pe.oh29oh29.myweb.service.PostService;

@Controller
public class PostController {

	@Autowired PostService postService;
	@Autowired CategoryService categoryService;
	@Autowired AttachedFileService attachedFileService;
	
	@RequestMapping(value = "category/*", method = RequestMethod.GET)
	public String postListView(HttpServletRequest httpRequest, Model model) {
		String servletPath = httpRequest.getServletPath();
		String categoryName = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		
		List<Category> categories = categoryService.findCategories(AccessSpecifier.PUBLIC);
		List<PostView> posts = postService.readPosts(categoryName);
		
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("categories", categories);
		model.addAttribute("posts", posts);
		
		return "blog/home";
	}
	
	/**
	 * @date	: 2018. 4. 26.
	 * @TODO	: 포스트 작성 뷰
	 */
	@RequestMapping(value = "post/write", method = RequestMethod.GET)
	public String postWriteView(Model model) {
		List<Category> categories = categoryService.findCategories(AccessSpecifier.TOTAL);
		model.addAttribute("categories", categories);
		return "blog/post/write";
	}
	
	/**
	 * @date	: 2018. 4. 26.
	 * @TODO	: 포스트 작성 완료
	 */
	@RequestMapping(value = "post/write", method = RequestMethod.POST)
	public String wrtiePost(HttpServletRequest httpRequest, Post post, String categoryName) throws Exception {
		Member member = (Member) httpRequest.getSession().getAttribute(Constants.SESSION_MEMBER);
		Category category = categoryService.findCategory(categoryName);
		
		if (category == null)
			throw new Exception();
		
		post.setMemberIdx(member.getIdx());
		post.setCategoryIdx(category.getIdx());
		
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

         System.out.println(sb.toString());
         return "redirect:" + callback + "?callback_func=" + callbackFunc + sb.toString(); 
	}
}
