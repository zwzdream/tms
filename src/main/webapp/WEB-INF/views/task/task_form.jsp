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


	function addTask(form){
		/* var time=$('#duration').val()+$('#timeType').val();
		$('#duration').val(time); */
		var formData = new FormData(form);
	  form.action= ctx + '/Task/add/save';
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
					noty({type:"success",text: "Add successed!", layout: "center", timeout: 3000});
					//$('#content').html(data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				noty({type:"error",text: "Add failed!", layout: "center", timeout: 3000});
	        },
	        complete: function(XMLHttpRequest, textStatus) { 
		    	$('#loading').remove();
				$('#content').fadeIn();
				docReady();
	        }
		}); 
	}

</script>
<fieldset>

     <div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="name">Name</label>
        <div class="col-sm-4">
   			<input type="text" class="form-control" id="name" name="name" placeholder="Name" style="height:35px;"></input>
   		</div>
   		    <label class="col-sm-2 control-label" for="duration">Duration</label>
        <div class="col-sm-4">
        	<input type="text" class="form-control" id="duration" name="duration" placeholder="Duration" style="height:35px;"></input>
   		</div>
   		  <!-- <div class="input-group-btn">  
   		 	<select id="timeType" name="timeType"  class="form-control" style="width: auto;">
                  <option value="days" selected = "selected">days</option>
                  <option value="months">months</option>
                  <option value="years">years</option>
           </select>
           </div> -->
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
			<textarea class="form-control" id="description" name="description"  style="height:85px;"></textarea>							            
        </div>
    </div>						                    
     <input type="hidden" class="form-control" id="creater" name="creater" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.id}" >
</fieldset>