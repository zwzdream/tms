<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<ul class="breadcrumb">
            <li>
               <a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a>
            </li>
            <li>
                <a href="javascript:ajaxContent('/Index/resource/init')">Resource Repository</a>
            </li>
             <li>
               <a href="javascript:ajaxContent('/Resource/toEditResource','resourceId=${resourceId}')">Resource Edit</a>
            </li>
    </ul>
</div>
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