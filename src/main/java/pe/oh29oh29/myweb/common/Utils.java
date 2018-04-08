package pe.oh29oh29.myweb.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class Utils {
	
	/**
	 * @date	: 2018. 4. 8.
	 * @TODO	: IDX 생성
	 */
	public static String generateIdx() {
		return UUID.randomUUID().toString().toUpperCase();
	}
	
	/**
	 * @date	: 2018. 4. 8.
	 * @TODO	: 현재 GMT 시간 생성
	 */
	public static String generateNowGMTDate() {
		SimpleDateFormat	format		= new SimpleDateFormat("yyyyMMddHHmmss");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.format(new Date());
	}

}
