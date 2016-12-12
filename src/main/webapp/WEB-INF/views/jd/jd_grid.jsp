<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function gridConvert(url, data, callback){
	$('#grid').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "post",
		url : encodeURI(encodeURI(ctx + url)),
		data : data,
		dataType : "html",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		cache: false,
		success:function(returnData){
			$("#grid").html(returnData);
			if( callback != null ){
				callback();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("error!");
        },
        complete: function(XMLHttpRequest, textStatus) { 
	    	$('#loading').remove();
			$('#grid').fadeIn();
			docReady();
        }
	});
}
</script>

<div id="grid" class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-th"></i> Grid
				</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round btn-default"><i
						class="glyphicon glyphicon-cog"></i></a> <a href="#"
						class="btn btn-minimize btn-round btn-default"><i
						class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
						class="btn btn-close btn-round btn-default"><i
						class="glyphicon glyphicon-remove"></i></a>
				</div>
				<div class="box-content">
					<table class="table table-bordered table-striped table-condensed">
						<thead>
							<tr>
								<th>No</th>
								<th>Name</th>
								<th>Status</th>
								<th>Notes</th>
								<th>Modify Date</th>
								<th>Owner</th>
							</tr>
						</thead>
						<tbody>
 							<c:forEach items="${page.list}" var="jd" varStatus="status"> 
								<tr>
									<td class="center">${jd.no}</td>
									<td class="center">${jd.title}</td>
									<td><c:if test="${jd.status==1}">
											<span class="label-success label label-default">Active</span>
										</c:if> <c:if test="${jd.status==0}">
											<span class="label-warning label label-default">Close</span>
										</c:if></td>
									<td class="center">${jd.notes}</td>
								 <td class="center"><fmt:formatDate
											value="${jd.modifydate}" pattern="yyyy-MM-dd HH:mm:ss" /></td> 
									<td class="center">${jd.owner}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table  class="gridtable" style="width: 100%; text-align: center;">
						<tr>
							<c:if test="${page.hasPreviousPage}">
								<td><a href="#"
									onclick="gridConvert('/JD/all/list','pageNum=${page.prePage}&pageSize=${page.pageSize}');">Previous</a></td>
							</c:if>
							<c:forEach items="${page.navigatepageNums}" var="nav">
								<c:if test="${nav == page.pageNum}">
									<td style="font-weight: bold;">${nav}</td>
								</c:if>
								<c:if test="${nav != page.pageNum}">
									<td><a href="#"
										onclick="gridConvert('/JD/all/list','pageNum=${nav}&pageSize=${page.pageSize}')">${nav}</a></td>
								</c:if>
							</c:forEach>
							<c:if test="${page.hasNextPage}">
								<td><a href="#"
									onclick="gridConvert('/JD/all/list','pageNum=${page.nextPage}&pageSize=${page.pageSize}');">Next</a></td>
							</c:if>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
	</div>