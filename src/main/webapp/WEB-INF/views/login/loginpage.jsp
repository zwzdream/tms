    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    <%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

      
        <h1>Login</h1>  
      
        <div id="login-error">${error}</div>  
     <div class="well col-md-5 center login-box"> 
        <form action="<%=basePath %>j_spring_security_check" class="form-horizontal" method="post">  
        <fieldset>
        
                <div class="input-group input-group-lg">
					<span class="input-group-addon"><i	class="glyphicon glyphicon-user red"></i></span> 
						 <label for="j_username">Username</label>
						  <input id="j_username"  class="form-control" name="j_username" type="text"  />  
				</div>
				<div class="clearfix"></div>
        		<div class="input-group input-group-lg">
					<span class="input-group-addon"><i	class="glyphicon glyphicon-lock red"></i></span> 
						 <label for="j_username">Password</label>
						 <input id="j_password" class="form-control"  name="j_password" type="password" />  
				</div>
		<p class="center col-md-5">
		<input type="checkbox" name="_spring_security_remember_me">两周之内不必登陆
		</p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
            <p class="center col-md-5">
					<button type="submit" class="btn btn-primary">Login</button>
		    </p>
          
        </fieldset>
      
        </form>  
        </div>