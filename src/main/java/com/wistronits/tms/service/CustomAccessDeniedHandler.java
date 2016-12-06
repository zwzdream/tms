package com.wistronits.tms.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;


public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 boolean isAjax = isAjaxRequest(request);
	        if(!isAjax){
	            request.setAttribute("isAjaxRequest", isAjax);
	            request.setAttribute("message", accessDeniedException.getMessage());
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/denied");
//	            response.sendRedirect(request.getContextPath()+"/authNotPass.jsp");
	            dispatcher.forward(request, response);
	        }else{
	            response.setCharacterEncoding("UTF-8");
	            response.setContentType("text/plain");
	            StringBuffer callString=new StringBuffer("you have no access!<hr>");
	            String href="/auth/login";
	            callString.append("<a href='javascript:ajaxToLogin(\""+href+"\");'>Switch Identity </a>"+"or");
	            href="/Index/home/page";
	            callString.append("<a href='javascript:ajaxToLogin(\""+href+"\");'>Return Home</a>");
	            response.getWriter().write(callString.toString());
	            response.getWriter().close();
	        }
	        
	}

	 private boolean isAjaxRequest(HttpServletRequest request) {
	        String header = request.getHeader("X-Requested-With");
	        if (header != null && "XMLHttpRequest".equals(header))
	            return true;
	        else
	            return false;
	    }

}
