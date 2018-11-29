package com.fanduel.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fanduel.Sport;
import com.fanduel.exception.UnsupportedSportException;

public class SupportedSportInterceptor extends HandlerInterceptorAdapter{
	
	private static final String SPORT_PATH_VARIABLE = "sport";
	
	/**
	 * Check if sport in path variable is supported
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		@SuppressWarnings("unchecked")
		Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		if(pathVariables.containsKey(SPORT_PATH_VARIABLE)) {
			checkSupportedSport(pathVariables.get(SPORT_PATH_VARIABLE));			
		}
		
		return true;
	}
	
	public static void checkSupportedSport(final String sport) throws UnsupportedSportException {
		try {
			Sport.valueOf(sport.toUpperCase());
		}
		catch(IllegalArgumentException e) {
			throw new UnsupportedSportException(sport);				
		}
	}
}
