package pe.oh29oh29.myweb.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dir {

	@Value("#{config['dir.js']}")
	private String js;
	@Value("#{config['dir.css']}")
	private String css;
	
	public String getJs() {
		return js;
	}
	public String getCss() {
		return css;
	}
	
	
}
