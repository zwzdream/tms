<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
function SearchJD(divId, formId,callback){
	var form = $("#" + formId)[0];
	form.action= ctx + '/JD/keyword/listPage';
	form.method = "post";	
    var formData = new FormData(form);
	formData.append("pageNum","1"); 
	$('#table').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "post",
		url : form.action,
		data :formData,
		async: false,
		cache: false,
		contentType: false,  
        processData: false,
        dataType: "html",
		success:function(data){
			if(data !=""){
				$('#content').html(data);
			 }
				if( callback != null ){
					callback();
				}
				$('#loading').remove();
				$('#table').fadeIn();
				docReady();
			}
		});
	}

 function Edit(url, data, callback){
		$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
		$.ajax({
			type : "post",
			url : encodeURI(encodeURI(ctx + url)),
			data : {no:data},
			dataType : "html",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			async: false,
			cache: false,
			success:function(returnData){
				$("#content").html(returnData);
				if( callback != null ){
					callback();
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				alert("error!");
			},
			complete: function(XMLHttpRequest, textStatus) { 
				$('#loading').remove();
				$('#content').fadeIn();
				docReady();
			}
		});
	}
 function SearchTo(url, data, callback){
		$('#table').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
		$.ajax({
			type : "post",
			url : encodeURI(encodeURI(ctx + url)),
			data : data,
			dataType : "html",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			async: false,
			cache: false,
			success:function(returnData){
				$("#content").html(returnData);
				if( callback != null ){
					callback();
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				alert("error!");
	        },
	        complete: function(XMLHttpRequest, textStatus) { 
		    	$('#loading').remove();
				$('#table').fadeIn();
				docReady();
	        }
		});
	}
</script>
<form id="splitPage" class="form-horizontal" action="#" method="POST">
	<div>
		<ul class="breadcrumb">
	            <li>
	                <a href="#">Home</a>
	            </li>
	            <li>
	                <a href="#">JD Management</a>
	            </li>
	    </ul>
	</div>
	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title>
					<h2><i class="glyphicon glyphicon-user"></i> </h2>
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round btn-default" title='Add' onclick="ajaxContent('${ctx}/resource/add.html');">
							<i class="glyphicon glyphicon-cog"></i></a> 
						<a href="#" class="btn btn-minimize btn-round btn-default">
							<i class="glyphicon glyphicon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round btn-default">
							<i class="glyphicon glyphicon-remove"></i></a>
					</div>
				</div>
				<div class="box-content">
					<fieldset>
					  	<div class="form-group">
							<label class="col-sm-1 control-label" for="name">Key word</label>
						  	<div class="col-sm-3">
							  	<input id="keyword" class="form-control" type="text" name="keyword" value='' maxlength="20" >
						  	</div>	
					  	</div>
					  	<div class="form-actions" >
					  		<div style="float:right;">
								<button type="button" class="btn btn-primary" onclick='SearchJD("table1", "splitPage");'>Search</button>
								&nbsp;&nbsp;&nbsp;
					  			<button type="button" class="btn" onclick='ajaxContent("/JD/toAdd.html");'>Add</button>
								&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn">Clear</button>
					  		</div>
					  	</div>
					</fieldset>
				</div>
			</div>
		</div><!--/span-->
	</div><!--/row-->

	<c:if test="${not empty page}">
	<div id="table" class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title="">
					<h2>
						<i class="glyphicon glyphicon-user"></i>
					</h2>
	
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round btn-default">
							<i class="glyphicon glyphicon-cog"></i></a> 
						<a href="#" class="btn btn-minimize btn-round btn-default">
							<i class="glyphicon glyphicon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round btn-default">
							<i class="glyphicon glyphicon-remove"></i></a>
					</div>
				</div>
				<div class="box-content">
					<div class="row">
						<div class="col-md-6">
							<div id="DataTables_Table_0_length" class="dataTables_length">
								<label>
									<select id="pageSize" name="pageSize" size="1"  aria-controls="DataTables_Table_0" onchange='ajaxForm("table1", "splitPage");'>
										<option value="5" selected="selected">5</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
									</select>
									records per page
								</label>
							</div>
						</div>
					</div>
					
				    <table id="table1" class="table table-striped table-bordered bootstrap-datatable ">
						    <thead>
				    <tr>
						        <th class="sr-only">No</th>
						        <th>Seq</th>
						        <th>Name</th>
						        <th>Description</th>
						        <th>Total</th>
						        <th>Complete</th>
						        <th>Actions</th>
						    </tr>
						    </thead>
		 				    <tbody>
						       <c:forEach items="${page.list}" var="jd" varStatus="status">
							    <tr>
							    <td class="sr-only">${jd.no }</td>
							        <td>${status.count}</td>
							        <td class="center">${jd.title}</td>
							        <td class="center">${jd.description}</td>
							        <td class="center">
							            <span class="label-success label label-default">228</span>
							        </td>
							        <td class="center">
							            <span class="label-success label label-default">Active</span>
							        </td>
							        <td class="center">
							            <a class="btn btn-info" href="#" onclick="ajaxContent('/JD/toEdit','no=${jd.no}');">
							                <i class="glyphicon glyphicon-edit icon-white"></i>
							                Edit
							            </a>
							            <a class="btn btn-success" href="#" onclick="ajaxContent('/JD/toAdd.html');">
							                <i class="glyphicon glyphicon-edit icon-white"></i>
							                Add Resource
							            </a>
							        </td>
							    </tr>
							    </c:forEach>
						    </tbody> 
				    </table>			    
         <table class="gridtable" style="width:100%;text-align: center;">
                <tr>
                    <c:if test="${page.hasPreviousPage}">
                        <td><a href="#" onclick="SearchTo('/JD/keyword/listPage','keyword=${keyword}&pageNum=${page.prePage}&pageSize=${page.pageSize}');">Previous</a></td>
                    </c:if>
                    <c:forEach items="${page.navigatepageNums}" var="nav">
                        <c:if test="${nav == page.pageNum}">
                            <td style="font-weight: bold;">${nav}</td>
                        </c:if>
                        <c:if test="${nav != page.pageNum}">
                            <td><a href="#" onclick="SearchTo('/JD/keyword/listPage','keyword=${keyword}&pageNum=${nav}&pageSize=${page.pageSize}')">${nav}</a></td>
                        </c:if>
                    </c:forEach>
                    <c:if test="${page.hasNextPage}">
                        <td><a href="#" onclick="SearchTo('/JD/keyword/listPage','keyword=${keyword }&pageNum=${page.nextPage}&pageSize=${page.pageSize}');">Next</a></td>
                    </c:if>
                </tr>
            </table> 
                   <div id="nav"></div>
				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	
	<!--/row-->
	</c:if>
</form>
