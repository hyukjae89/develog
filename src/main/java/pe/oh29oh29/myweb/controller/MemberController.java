package pe.oh29oh29.myweb.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import pe.oh29oh29.myweb.common.Constants;
import pe.oh29oh29.myweb.common.config.LoginAPI;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired MemberService memberService;
	@Autowired LoginAPI loginAPI;

	@RequestMapping(value = "signInView", method = RequestMethod.GET)
	public String signInView() {
		return "./member/signIn";
	}
	
	@RequestMapping(value ="getLoginAPIWithNaver", method = RequestMethod.GET)
	public @ResponseBody String getLoginAPIWithNaver(HttpServletRequest httpRequest) {
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = loginAPI.getNaverAuthorizeURL();
		apiURL += "?response_type=code";
		apiURL += "&client_id=" + loginAPI.getNaverClientId();
		apiURL += "&state=" + state;
		try {
			apiURL += "&redirect_uri=" + URLEncoder.encode(loginAPI.getNaverCallbackURL(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		    
		httpRequest.getSession().setAttribute("state", state);
		return apiURL;
	};

	@RequestMapping(value = "verifyStateCodeWithNaver", method = RequestMethod.GET)
	public String verifyStateCodeWithNaver(HttpServletRequest httpRequest, RedirectAttributes redirectAttributes) {
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

		String apiURL = "https://nid.naver.com/oauth2.0/token";
		apiURL += "?client_id=" + loginAPI.getNaverClientId();
		apiURL += "&client_secret=" + loginAPI.getNaverClientSecret();
		apiURL += "&grant_type=authorization_code";
		apiURL += "&state=" + state;
		apiURL += "&code=" + code;
		
		String apiResult = invokeAPI(apiURL, Constants.GET);
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(apiResult);
			redirectAttributes.addAttribute("accessToken", jsonObject.get("access_token"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "redirect:searchUserProfileInfoWithNaver";
	}
	
	@RequestMapping(value = "searchUserProfileInfoWithNaver", method = RequestMethod.GET)
	public String searchUserProfileInfoWithNaver(HttpServletRequest httpRequest, @RequestParam("accessToken") String accessToken) {
		
		String apiURL = loginAPI.getNaverProfileURL();
		String apiResult = invokeAPI(apiURL, Constants.GET, new String[]{"authorization"}, new String[]{"Bearer " + accessToken});
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject result = (JSONObject) jsonParser.parse(apiResult);
			JSONObject response = (JSONObject) result.get("response");
			String id = (String) response.get("id");
			String email = (String) response.get("email");
			String name = (String) response.get("name");

			Member member = new Member();
			member.setId(id);
			member.setName(name);
			member.setEmail(email);
			member.setProvider(Constants.NAVER);
			memberService.signUpMember(member);
			
			httpRequest.getSession().setAttribute("member", member);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "verifyIdTokenWithGoogle", method = RequestMethod.POST)
	@ResponseBody
	public String verifyIdTokenWithGoogle(HttpServletRequest httpRequest, @RequestBody String idTokenString) {
		// (Receive authCode via HTTPS POST)
		try {
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
					// Specify the CLIENT_ID of the app that accesses the backend:
					.setAudience(Collections.singletonList(loginAPI.getGoogleClientId()))
					// Or, if multiple clients access the backend:
					//.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
					.build();

			// (Receive idTokenString by HTTPS POST)

			GoogleIdToken idToken = verifier.verify(idTokenString);
			if (idToken != null) {
				Payload payload = idToken.getPayload();

				// Print user identifier
				String id = payload.getSubject();
				// Get profile information from payload
				String email = payload.getEmail();
				String name = (String) payload.get("name");

				Member member = new Member();
				member.setId(id);
				member.setName(name);
				member.setEmail(email);
				member.setProvider(Constants.GOOGLE);
				memberService.signUpMember(member);
				
				httpRequest.getSession().setAttribute("member", member);
			} else {
				System.out.println("Invalid ID token.");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	private String invokeAPI(String apiURL, String requestMethod) {
		return invokeAPI(apiURL, requestMethod, null, null);
	}
	private String invokeAPI(String apiURL, String requestMethod, String[] headerFieldNames, String[] headerFieldValues) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(requestMethod);
			
			if (headerFieldNames != null && headerFieldNames.length > 0) {
				for (int i = 0; i < headerFieldNames.length; i++) {
					con.setRequestProperty(headerFieldNames[i], headerFieldValues[i]);
				}
			}
			int responseCode = con.getResponseCode();
			BufferedReader br;

			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				return res.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
