package pe.oh29oh29.myweb.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.oh29oh29.myweb.common.Constants;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired MemberService memberService;

	@RequestMapping(value = "signInView", method = RequestMethod.GET)
	public String signInView() {
		return "./member/signIn";
	}
	
	@RequestMapping(value ="getLoginAPIWithNaver", method = RequestMethod.GET)
	public @ResponseBody String getLoginAPIWithNaver(HttpServletRequest httpRequest) {
		
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		String clientId = Constants.naverClientId;
		String redirectURI = "http://dev.oh29oh29.pe.kr:5050/validateStateCodeWithNaver";
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		
		try {
			redirectURI = URLEncoder.encode(redirectURI, "UTF-8");
			apiURL += "&client_id=" + clientId;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&state=" + state;
			httpRequest.getSession().setAttribute("state", state);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		    
		return apiURL;
	};

	@RequestMapping(value = "validateStateCodeWithNaver", method = RequestMethod.GET)
	public String validateStateCodeWithNaver(HttpServletRequest httpRequest, RedirectAttributes redirectAttributes) {
		// CSRF 방지를 위한 상태 토큰 검증 검증
		// 세션 또는 별도의 저장 공간에 저장된 상태 토큰과 콜백으로 전달받은 state 파라미터의 값이 일치해야 함

		// 콜백 응답에서 state 파라미터의 값을 가져옴
		String state = httpRequest.getParameter("state");
		String code = httpRequest.getParameter("code");
		// 세션 또는 별도의 저장 공간에서 상태 토큰을 가져옴
		String storeState = (String) httpRequest.getSession().getAttribute("state");
		
		if (!state.equals(storeState)) {
			return "./home";
		} else {
			redirectAttributes.addAttribute("state", state);
			redirectAttributes.addAttribute("code", code);
			return "redirect:requestAccessTokenWithNaver";
		}
	}
	
	@RequestMapping(value = "requestAccessTokenWithNaver", method = RequestMethod.GET)
	public String requestAccessTokenWithNaver(HttpServletRequest httpRequest, RedirectAttributes redirectAttributes, @RequestParam("state") String state, @RequestParam("code") String code) {

		String clientId = Constants.naverClientId;				// 애플리케이션 클라이언트 아이디값
		String clientSecret = Constants.naverClientSecret;		// 애플리케이션 클라이언트 시크릿값
		String apiURL = "https://nid.naver.com/oauth2.0/token?client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=authorization_code&state=" + state + "&code=" + code;

		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode="+responseCode);
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if(responseCode==200) {
				System.out.println(res.toString());
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(res.toString());
				redirectAttributes.addAttribute("accessToken", jsonObject.get("access_token"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "redirect:searchUserProfileInfoWithNaver";
	}
	
	@RequestMapping(value = "searchUserProfileInfoWithNaver", method = RequestMethod.GET)
	public String searchUserProfileInfoWithNaver(HttpServletRequest httpRequest, @RequestParam("accessToken") String accessToken) {
		
		System.out.println("accessToken = " + accessToken);
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("authorization", "Bearer " + accessToken);
			int responseCode = con.getResponseCode();
			BufferedReader br;

			if (responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if(responseCode==200) {
				JSONParser jsonParser = new JSONParser();
				JSONObject result = (JSONObject) jsonParser.parse(res.toString());
				JSONObject response = (JSONObject) result.get("response");
				String id = (String) response.get("id");
				String email = (String) response.get("email");
				String name = (String) response.get("name");
				System.out.println(response);
				Member member = new Member();
				member.setId(id);
				member.setName(name);
				member.setEmail(email);
				member.setProvider(Constants.NAVER);
				memberService.signUpMember(member);
				
				httpRequest.getSession().setAttribute("member", member);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "redirect:/";
	}
}
