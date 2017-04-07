<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wistronits.tms.entity.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
 <script>
$(function(){
	listAllUser();
	var creater='${task.creater}';
	if(typeof(creater) != "undefined"& creater!=''){
		var createrName=$("#assigneeId option[value="+creater+"]").text();
	    $("#createrName").html("The task was founded by "+createrName+" in "+ '${task.create_time}');
		
	}
})

function listAllUser(){
	var option;
	$.ajax({
		url:ctx+"/User/listAll",
		type:"post",
		dataType : 'json',
	    cache:false,
	    success:function(data){
	    	var json = eval(data);
	    	$.each(json,function(index, item) {
	    		var id= json[index].id;
				var name = json[index].username;
	    		option += ("<option value='"+id+"'>"+name+"</option>");
		
	    	});
	    	$("#assigneeId").html(option);
	    },
	    error:function(XMLHttpRequest, textStatus, errorThrown) {   
	    	noty({type:"error",text: "An internal error has occurred. Please contact your system administrator!", layout: "center", timeout: 3000});
	    },  
	    async: false
		
		
	});
}

function assignTask(form){
	var formData = new FormData(form);
  form.action= ctx + '/Task/assignTask/save';
  $('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
  $.ajax({
		type : "post",
		url : form.action,
		data :formData,
		async: false,
		cache: false,
		contentType: false,  
        processData: false,
        dataType : 'html',
        success:function(data){
			if(data !=""){
				noty({type:"success",text: "Assign successed!", layout: "center", timeout: 3000});
				//$('#content').html(data);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			noty({type:"error",text: "Assign failed!", layout: "center", timeout: 3000});
        },
        complete: function(XMLHttpRequest, textStatus) { 
	    	$('#loading').remove();
			$('#content').fadeIn();
			docReady();
        }
	}); 
}
</script>	
</head>
<div>
	<ul class="breadcrumb">
            <li>
                <a href="#"  onclick='ajaxContent("/Index/dashboard/init")'>Home</a>
            </li>
            <li>
                <a href="#" onclick='ajaxContent("/Index/task/init")'>Task Management </a>
            </li>
            <li>
                <a href="#" onclick='ajaxContent("/Task/toEdit","id=${task.id}")'>Task Edit </a>
            </li>
    </ul>
</div>
<body>
<h3>The history of this task</h3>
  <div class="col-sm-4"> 
  <label id="createrName"> </label><br>
             <%
           TaskBean taskBean=(TaskBean)request.getAttribute("task");
             ArrayList<TaskNotes> taskNotes=taskBean.getTaskNotes();
          for(TaskNotes taskNote : taskNotes){ 
           %>
            <%if (taskNote.getAssignee()!=""){%>
         <label>Assign to <span> <%=taskNote.getAssignee() %> </span> in <%=taskNote.getUpdate_time() %><br/>
         
         </label>  <br/>
          <%} %>
            <%if (taskNote.getNote()!=null){%>
             <span style="color:RED;margin-left: 50px" ><%=taskNote.getNote() %></span><br/>
             <%} %>
           <% } %>
  </div> 
  <div class="col-sm-8"> 
  <form class="form-horizontal" id="noteForm" name="noteForm"  action="#" method="POST">
            <input type="hidden"  id="task_id" name="task_id" value="${task.id}"">
         <div class="form-group">
				  <label class="col-sm-2 control-label" for="assignee">AssignTo</label>
   		            <div class="col-sm-4">
       	            <select id="assigneeId" name="assigneeId"  class="form-control" > 
       	            </select>
          	       </div> 
          </div> 
						 
		 <div class="form-group">
				    <label class="col-sm-2 control-label" for="note">Task note</label>
                     <div class="col-sm-8" >
			           <textarea class="form-control" id="note" name="note"  style="height:85px;"></textarea>							            
                     </div>
          </div>
   <div style="text-align:center;">
		  <button type="button" class="btn btn-primary" onclick="assignTask(noteForm);">Submit
		   </button>  &nbsp;&nbsp;&nbsp;
		  <button type="reset" class="btn">Clear</button>
   </div> 
 </form>
  </div>





<%-- <i class="glyphicon glyphicon-edit icon-white " data-toggle="modal" data-target="#myModal" id="modal">click</i> 
<div class="modal fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					Assign &nbsp; To
				</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="noteForm" name="noteForm"  action="#" method="POST">
				   <div class="form-group tr-new">
				  <label class="col-sm-2 control-label" for="assignee">AssignTo</label>
   		            <div class="col-sm-4">
       	            <select id="assignee" name="assignee"  class="form-control" > 
       	            </select>
          	       </div> 
				   </div> 
						 
			 	 <div class="form-group tr-new">
				    <label class="col-sm-2 control-label" for="note">Task note</label>
                     <div class="col-sm-8" >
			           <textarea class="form-control" id="note" name="note"  style="height:85px;"></textarea>							            
                     </div>
			  </div>  
			  
			  	<input type="hidden"  id="task_id" name="task_id" value="${taskId}">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close
				</button>
				<button type="button" class="btn btn-primary" onclick="assignTask(noteForm);">
					Submit
				</button>
			</div>
		</div>
	</div>
</div> --%>
</body> 
</html>