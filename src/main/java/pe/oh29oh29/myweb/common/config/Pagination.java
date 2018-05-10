package pe.oh29oh29.myweb.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Pagination {

	@Value("#{config['pagination.countPerPage']}")
	private int countPerPage;
	@Value("#{config['pagination.countPerBlock']}")
	private int countPerBlock;

	public int getCountPerPage() {
		return countPerPage;
	}
	public int getCountPerBlock() {
		return countPerBlock;
	}
	
}
