package io.abhi.hotelmanagement.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSessionUtils {
	
	public static HttpServletRequest getHttpRequest(Object[] objects) {
		for (Object object : objects) {
			if (object instanceof HttpServletRequest) {
				HttpServletRequest detail = (HttpServletRequest) object;
				return detail;
			}

		}
		return null;
	}


	public static HttpServletResponse getHttpResponse(Object[] objects) {
		for (Object object : objects) {
			if (object instanceof HttpServletResponse) {
				HttpServletResponse detail = (HttpServletResponse) object;
				return detail;
			}

		}
		return null;
	}
	
}
