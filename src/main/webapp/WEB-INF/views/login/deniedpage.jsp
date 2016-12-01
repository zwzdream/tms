<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 


  <script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();}
	</script>  


	 <h1>你的权限不够!</h1>  
        <p>只有拥有Admin权限才能访问!</p>  
   <form action="${ctx}/auth/logout" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		  <a href="javascript:formSubmit();">退出登录</a>  
	</form>


  

