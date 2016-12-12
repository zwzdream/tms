<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
 <script src="${ctx}/resources/ui/multiselect/js/prettify.min.js"></script>
 <script src="${ctx}/resources/ui/multiselect/js/multiselect.min.js"></script>
 <link href='${ctx}/resources/ui/multiselect/css/style.css' rel='stylesheet'>
 <link href='${ctx}/resources/ui/multiselect/lib/google-code-prettify/prettify.css' rel='stylesheet'>
 <script src="${ctx}/resources/js/user/jd_resource.js"></script>
<div>
	<ul class="breadcrumb">
            <li>
                <a href="#">Home</a>
            </li>
            <li>
                <a href="#">JD Resource Management </a>
            </li>
    </ul>
</div>
<div class="row-fluid sortable">
 <div class="form-control">
	 <label class="col-sm-12 control-label" for="title">JD:<span id="title" class="text-success">${jdTitle }</span></label>
	 <input type="hidden" id="id" name="id" value="${jdNo }">
</div>
<div class="row">
	<div class="col-xs-5">
	 
		<select name="from" id="multiselect" class="form-control" size="8" multiple="multiple">
		</select>
		    <div>you can add the above resources</div>
	</div>
	
	<div class="col-xs-2">
	<div>operations</div>
	     <br>
		<button type="button" id="multiselect_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
		<br>
		<br>
		<button type="button" id="multiselect_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
	</div>
	
	<div class="col-xs-5">
	
		<select name="to" id="multiselect_to" class="form-control" size="8" multiple="multiple"></select>
		<div>The JD have The above resources  </div>
	</div>

</div>
</div>
<!--/row-->