<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div  class="box-content ">
      <c:if test="${filePath !='error'}">
    <div><%@ include file="document_view.jsp"%></div>
    </c:if>
    
  <c:choose> 

  <c:when test="${filePath !='error'}">   
   <div><%@ include file="document_view.jsp"%></div>
  </c:when> 
  <c:otherwise>   
  <script> alert("There is no resume to see!");
  ajaxContent('/Index/resource/init')</script>
  </c:otherwise> 

</c:choose> 

    </div>