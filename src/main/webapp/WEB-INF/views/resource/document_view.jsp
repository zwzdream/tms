<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/flex/flexpaper_flash.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/flex/flexpaper_flash_debug.js"></script>
<style type="text/css" media="screen">
html, body {
	height: 100%;
}

body {
	margin: 0;
	padding: 0;
	overflow: auto;
}

#flashContent {
	display: none;
}
</style>

<div class="container">
<div style="/* position: absolute; */left: 50px; top: 10px;">
	<a id="viewerPlaceHolder"
		style="width: 80%; height: 750px; display: block"></a>

	<script type="text/javascript"> 
	var rfilePath='${ctx}'+'\/file/'+'${filePath}'+'.swf';
		var fp = new FlexPaperViewer('${ctx}/resources/flex/FlexPaperViewer',
				'viewerPlaceHolder',/* 对应于a 标签的id */
				{
					config : {
						SwfFile : escape(rfilePath), /*   这句是关键: SwfFile: 指示导入的.swf的路径,必须是可以访问的(虚拟）目录 */
						Scale : 0.6,
						ZoomTransition : 'easeOut',
						ZoomTime : 0.5,
						ZoomInterval : 0.2,
						FitPageOnLoad : true,
						FitWidthOnLoad : true,
						FullScreenAsMaxWindow : false,
						ProgressiveLoading : false,
						MinZoomSize : 0.2,
						MaxZoomSize : 5,
						SearchMatchAll : true,
						InitViewMode : 'Portrait',

						ViewModeToolsVisible : true,
						ZoomToolsVisible : true,
						NavToolsVisible : true,
						CursorToolsVisible : true,
						SearchToolsVisible : true,

						localeChain : 'en_US'
					}
				});
		
		var fileError='${fileError}';
		if(fileError!=""){
			noty({type:"warning",text:fileError, layout: "center", timeout: 3000});
		}
	
	</script>
</div>
</div>
