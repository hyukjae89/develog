package pe.oh29oh29.myweb.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pe.oh29oh29.myweb.service.AttachedFileService;
import pe.oh29oh29.myweb.service.AttachedFileService.FileType;
import pe.oh29oh29.myweb.service.PostService;

@Controller
public class PostController {

	@Autowired PostService postService;
	@Autowired AttachedFileService attachedFileService;
	
	@RequestMapping(value = "postList", method = RequestMethod.GET)
	public String postListView() {
		return "blog/post/list";
	}
	
	@RequestMapping(value = "postWrite", method = RequestMethod.GET)
	public String postWriteView() {
		return "blog/post/write";
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
