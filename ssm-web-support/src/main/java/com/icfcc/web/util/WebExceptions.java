package com.icfcc.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by whydk on 2015/10/25.
 */
public class WebExceptions {

	/**
	 * 在request中获取异常类
	 * @param request
	 * @return
	 */
	public static Throwable getThrowable(HttpServletRequest request){
		Throwable ex = null;
		if (request.getAttribute("exception") != null) {
			ex = (Throwable) request.getAttribute("exception");
		} else if (request.getAttribute("javax.servlet.error.exception") != null) {
			ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
		}
		return ex;
	}
}
