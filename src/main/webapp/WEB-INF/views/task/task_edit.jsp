<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wistronits.tms.entity.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="tid" value="'${task.id}'" />
<script>
var tid = ${tid};
</script>
<div>
	<ul class="breadcrumb">
            <li>
                <a href="#"  onclick='ajaxContent("/Index/dashboard/init")'>Home</a>
            </li>
            <li>
                <a href="#" onclick='ajaxContent("/Index/task/init")'>Task Management </a>
            </li>
    </ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2>
				<i class="glyphicon glyphicon-edit"></i> Edit
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="glyphicon glyphicon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round"><i class="glyphicon glyphicon-remove"></i></a>
			</div>
		</div>
		
	<div class="box-content">
			<form class="form-horizontal" id="taskEditForm" name="taskEditForm"  action="#" method="POST">
				<%@ include file="task_edit_form.jsp"%>
				<div class="form-actions" style="text-align:right;">
					<button class="btn btn-primary" type="button" onclick="editTask(this.form);">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2>
				<i class="glyphicon glyphicon-edit"></i> Task History
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="glyphicon glyphicon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round"><i class="glyphicon glyphicon-remove"></i></a>
			</div>
		</div>
 <div class="box-content">
	  <div class="col-sm-6">
        <h3>The basic information of the task</h3>
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
       <div class="actions">
           <div class="btn-group">
           <label id="maindiv">
        <!--    <a class="btn iframe" href="">
              <i class="glyphicon glyphicon-play icon-white"></i>Start
           </a>
           <a class="btn iframe" href="">
              <i class="glyphicon glyphicon-hand-right icon-white"></i>Assign&nbsp;To
           </a>
           <a class="btn iframe" href="">
              <i class="glyphicon glyphicon-ok icon-white"></i>Complete
           </a> -->
           </label>
           </div>
       </div>
    </div>
    
	</div>
</div>