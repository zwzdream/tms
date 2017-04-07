
<style type="text/css">
	.tr-new {
		position: static;
		top: auto;
		right: auto;
		bottom: auto;
		left: auto;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 5px;
		margin-left: 0px;
		color: #333333;
		display: block;
		visibility: visible;
	}
</style>
<script type="text/javascript">

$(function(){
	listAllUser();
	var id='${task.id}';
	
	var priority = '${task.priority}';
	if(typeof(priority) != "undefined"&priority!=''){
		$('#priority').val(priority);
	}
	
	var status='${task.status}';
	if(typeof(status) != "undefined"&status!=''){
		$('#status').val(status);
		
		if(status==0){
			$("#maindiv").html(
					'<a class="btn iframe" href="#" onclick=ajaxJSON(\"/Task/startTask\","id='+ id +'",refresh);>'
    				+'<i class="glyphicon glyphicon-play icon-white"></i>Start</a>&nbsp;'
	    			+'<a class="btn iframe"  href="#" onclick=ajaxContent(\"/Task/toAssign\","id='+id +'");>'
	    			+'<i class="glyphicon glyphicon-hand-right  icon-white"></i>Assign&nbsp;To</a>'
	    			+'<a class="btn iframe" href="#" onclick=ajaxJSON(\"/Task/completeTask\","id='+id+'&uid='+uid +'",refresh);>'
	    			+'<i class="glyphicon glyphicon-ok  icon-white"></i>Complete</a>' 
	    			+'<a  href="#" onclick=ajaxJSON(\"/Task/deleteTask\","id='+id +'");>'
	    		    +'<i class="glyphicon glyphicon-trash  icon-white"></i>Delete</a>');
		}else if(status==1){
			$("#maindiv").html(
					'<a class="btn iframe" href="#" >'
    				+'<i class="glyphicon glyphicon-play icon-white" style="color:gray;"></i>Start</a>&nbsp;'
	    			+'<a class="btn iframe" href="#" onclick=ajaxContent(\"/Task/toAssign\","id='+ id +'");>'
	    			+'<i class="glyphicon glyphicon-hand-right  icon-white"></i>Assign&nbsp;To</a>'
	    			+'<a class="btn iframe" href="#" onclick=ajaxJSON(\"/Task/completeTask\","id='+ id +'&uid='+uid+'",refresh);>'
	    			+'<i class="glyphicon glyphicon-ok  icon-white"></i>Complete</a>'
	    			+'<a  href="#" onclick=ajaxJSON(\"/Task/deleteTask\","id='+ id +'");>'
	    		    +'<i class="glyphicon glyphicon-trash  icon-white"></i>Delete</a>');
		}else if(status==2){
			$("#maindiv").html(
			'<a class="btn iframe" href="#">'
			+'<i class="glyphicon glyphicon-play icon-white" style="color:gray;"></i>Start</a>&nbsp;'
			+'<a class="btn iframe" href="#" onclick=ajaxContent(\"/Task/toAssign\","id='+id +'");>'
			+'<i class="glyphicon glyphicon-hand-right  icon-white"></i>Assign&nbsp;To</a>'
			+'<a class="btn iframe" href="#">'
			+'<i class="glyphicon glyphicon-ok  icon-white" style="color:gray;"></i>Complete</a>'
			+'<a  href="#" onclick=ajaxJSON(\"/Task/deleteTask\","id='+ id +'");>'
		    +'<i class="glyphicon glyphicon-trash  icon-white"></i>Delete</a>');
		}
	}
	
	
	
	var assignee='${task.last_assignee}';
	if(typeof(last_assignee) != "undefined"&last_assignee!=''){
		$('#last_assignee').val(assignee);
		//var $('#last_assignee').find("option:selected").text();
		
	}
	
	var creater='${task.creater}';
	if(typeof(creater) != "undefined"& creater!=''){
		var createrName=$("#last_assignee option[value="+creater+"]").text();
	    $("#createrName").html("The task was founded by "+createrName+" in "+ '${task.create_time}');
		
	}

	
	
	

})


function refresh(){
	$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "post",
		url : encodeURI(encodeURI(ctx + "/Task/toEdit")),
		data : {"id":tid},
		dataType : "html",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		cache: false,
		success:function(returnData){
			$("#content").html(returnData);
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
	    		//循环获取数据
	    		var id= json[index].id;
				var name = json[index].username;
	    		option += ("<option value='"+id+"'>"+name+"</option>");
	    	});
	    	$("#last_assignee").html(option);
	    },
	    error:function(XMLHttpRequest, textStatus, errorThrown) {   
	    	noty({type:"error",text: "An internal error has occurred. Please contact your system administrator!", layout: "center", timeout: 3000});
	    },  
	    async: false
		
		
	});
}

	function editTask(form){
		 var formData = new FormData(form);
		  form.action= ctx + '/Task/edit/save';
		  form.method = "post";	
		  if(confirm('Are you sure?')){
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
							noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
							//$('#content').html(data);
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						noty({type:"error",text: "Edit failed!", layout: "center", timeout: 3000});
			        },
			        complete: function(XMLHttpRequest, textStatus) { 
				    	$('#loading').remove();
						$('#content').fadeIn();
						docReady();
			        }
				}); 
			  return true;
		  }
		  return false;
	}

</script>
<fieldset>
   <input type="hidden" class="form-control" id="id" name="id" value="${task.id}" >
	   <div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="name">Name</label>
        <div class="col-sm-4">
   			<input type="text" class="form-control" id="name" name="name" value="${task.name}" style="height:35px;"></input>
   		</div>
   		    <label class="col-sm-2 control-label" for="duration">Duration</label>
        <div class="col-sm-4">
        	<input type="text" class="form-control" id="duration" name="duration" value="${task.duration}" style="height:35px;"></input>
   		</div>
    </div>					                    
    <div class="form-group tr-new">
         <label class="col-sm-2 control-label" for="priority">Priority</label>
        <div class="col-sm-2">
                        <select id="priority" name="priority"  class="form-control" data-rel="chosen">
                              <option value="0" selected = "selected">Common</option>
                              <option value="1">Priority</option>
                               <option value="2">Emergency</option>
                        </select>
        </div>
    	  <label class="col-sm-2 control-label" for="status">Status</label>
   		  <div class="col-sm-2">
       	  <select id="status" name="status"  class="form-control" data-rel="chosen">
                              <option value="0" selected = "selected">Closed</option>
                              <option value="1">Processing</option>
                               <option value="2">Complete</option>
           </select>
     	</div>
    	  <label class="col-sm-2 control-label" for="last_assignee">AssignTo</label>
   		  <div class="col-sm-2">
       	  <select id="last_assignee" name="last_assignee"  class="form-control" data-rel="chosen"> </select>
    	</div> 
    </div>	
    
     <div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="description">Task Description</label>
        <div class="col-sm-10" >
			<textarea class="form-control" id="description" name="description"  style="height:85px;">${task.description }</textarea>							            
        </div>
    </div>	
    
    
    					                    
</fieldset>