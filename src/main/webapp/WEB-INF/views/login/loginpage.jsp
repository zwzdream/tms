    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    <%@ page language="java" contentType="text/html; charset=UTF-8"  
        pageEncoding="UTF-8"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
    <html>  
    <head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    <title>LoginPage</title>  
    </head>  
    <body>  
      
        <h1>Login</h1>  
      
        <div id="login-error">${error}</div>  
      
        <form action="<%=basePath %>j_spring_security_check" method="post">  
      
            <p>  
                <label for="j_username">Username</label> <input id="j_username"  
                    name="j_username" type="text" />  
            </p>  
      
            <p>  
                <label for="j_password">Password</label> <input id="j_password"  
                    name="j_password" type="password" />  
            </p>  
            
         <p>
            <input type="checkbox" name="_spring_security_remember_me">两周之内不必登陆
        </p>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
     
     
            <input type="submit" value="Login" />  
      
        </form>  
      
    </body>  
    </html>  