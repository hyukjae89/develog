package pe.oh29oh29.myweb.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.oh29oh29.myweb.common.Constants;

@Controller
public class MemberController {

	@RequestMapping(value = "signInView", method = RequestMethod.GET)
	public String signInView() {
		return "./member/signIn";
	}
	
	@RequestMapping(value ="getLoginAPIWithNaver", method = RequestMethod.GET)
	public @ResponseBody String getLoginAPIWithNaver(HttpServletRequest httpRequest) {
		
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		String clientId = Constants.naverClientId;
		String redirectURI = "http://dev.oh29oh29.pe.kr";
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
	public String validateStateCodeWithNaver(HttpServletRequest httpRequest) {
		// CSRF 방지를 위한 상태 토큰 검증 검증
		// 세션 또는 별도의 저장 공간에 저장된 상태 토큰과 콜백으로 전달받은 state 파라미터의 값이 일치해야 함

		// 콜백 응답에서 state 파라미터의 값을 가져옴
		String state = httpRequest.getParameter("state");
		// 세션 또는 별도의 저장 공간에서 상태 토큰을 가져옴
		String storeState = (String) httpRequest.getSession().getAttribute("state");
		
		if (!state.equals(storeState)) {
			return "./home";
		} else {
			return "redirect:requestAccessTokenWithNaver";
		}
		
	}
	
	@RequestMapping(value = "requestAccessTokenWithNaver", method = RequestMethod.GET)
	public String requestAccessTokenWithNaver(HttpServletRequest httpRequest) {

		String clientId = Constants.naverClientId;				// 애플리케이션 클라이언트 아이디값
		String clientSecret = Constants.naverClientSecret;		// 애플리케이션 클라이언트 시크릿값
		String grantType = "authorization_code";
		String apiURL = "https://nid.naver.com/oauth2.0/token";
		
		
		String access_token = "";
		String refresh_token = "";
		System.out.println("apiURL="+apiURL);
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
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
				out.println(res.toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "redirect:searchUserProfileInfoWithNaver";
	}
	
	@RequestMapping(value = "searchUserProfileInfoWithNaver", method = RequestMethod.GET)
	public String searchUserProfileInfoWithNaver() {
		
		return "./home";
	}
}
