package pe.oh29oh29.myweb.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginAPI {

	@Value("#{config['loginAPI.naver.clientId']}")
	private String naverClientId;
	@Value("#{config['loginAPI.naver.clientSecret']}")
	private String naverClientSecret;
	@Value("#{config['loginAPI.naver.url.authorize']}")
	private String naverAuthorizeURL;
	@Value("#{config['loginAPI.naver.url.callback']}")
	private String naverCallbackURL;
	@Value("#{config['loginAPI.naver.url.token']}")
	private String naverTokenURL;
	@Value("#{config['loginAPI.naver.url.profile']}")
	private String naverProfileURL;
	
	public String getNaverClientId() {
		return naverClientId;
	}
	public String getNaverClientSecret() {
		return naverClientSecret;
	}
	public String getNaverAuthorizeURL() {
		return naverAuthorizeURL;
	}
	public String getNaverCallbackURL() {
		return naverCallbackURL;
	}
	public String getNaverTokenURL() {
		return naverTokenURL;
	}
	public String getNaverProfileURL() {
		return naverProfileURL;
	}
}
