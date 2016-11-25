<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE>ZTREE LEARN</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/resources/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${ctx}/resources/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript">var ctx = "${ctx}";</script>
<script type="text/javascript" src="${ctx}/resources/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/ztree/js/jquery.ztree.all.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.excheck.min.js"></script> 
  <script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.exedit.min.js"></script>--%>
<script type="text/javascript" src="${ctx}/resources/ztree/js/jquery.ztree.exhide.min.js"></script>
<SCRIPT>
	var setting = {
		async : {
			enable : true,
			url : ctx+"/Menu/getMenuByGroupName",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			autoParam : [ "id", "name", "level" ],
			otherParam : {
				"groupName" : "admin"//假设用户属于"admin"用户组
			},
			dataFilter : filter
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)  return null;
		for(var i=0,l=childNodes.length;i<l;i++){
			childNodes[i].url=ctx+childNodes[i].url;
		}
		return childNodes;
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
	});
</SCRIPT>
</HEAD>
<BODY>
	<div>
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</BODY>
</HTML>