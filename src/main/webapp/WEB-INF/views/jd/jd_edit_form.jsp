<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	function closeJD(form){
		var formData = new FormData(form);
	  form.action= ctx + '/JD/edit/close';
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
						noty({type:"success",text: "Close successed!", layout: "center", timeout: 3000});
						$('#content').html(data);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) { 
					noty({type:"error",text: "Close failed!", layout: "center", timeout: 3000});
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
	function dataValiE(form){
	 var formData = new FormData(form);
	  form.action= ctx + '/JD/edit/save';
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
						$('#content').html(data);
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
	<div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="no">No.</label>
        <div class="col-sm-5">
         	<input type="number" class="form-control" id="no" name="no" value="${jd.no }" style="height:35px;" readonly>
        </div>
        <label class="col-sm-1 control-label" for="priority">Priority</label>
        <div class="col-sm-5">
         	<input type="text" class="form-control" id="priority" name="priority" value="${jd.priority }" style=height:35px;">
        </div>
    </div>
     <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="title">Title</label>
        <div class="col-sm-11">
   			<input type="text" class="form-control" id="title" name="title" value="${jd.title }" style=height:35px;"></input>
   		</div>
    </div>		
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="client">Client</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="client" name="client" value="${jd.client}" style=height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="status">Status</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="status" name="status" value="${jd.status }" style="height:35px;" readonly>
   		</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="location">Location</label>
        <div class="col-sm-11">
			<input type="text" class="form-control" id="location" name="location" value="${jd.location }" style="height:35px;"></input>							        	
    	</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="duration">Duration</label>
       	<div class="col-sm-5">
        	<input type="text" class="form-control" id="duration" name="duration" value="${jd.duration }" style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="payrate">Pay Rate</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="payrate" name="payrate" value="${jd.payrate }" style="height:35px;">
   		</div>
    </div>
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="keyword">Key word</label>
        <div class="col-sm-11">
        	<input type="text" class="form-control" id="keyword" name="keyword" value="${jd.keyword }" style="height:35px;"></input>
   		</div>
    </div>
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="local">Local</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="local" name="local" value="${jd.local }" style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="owner">Owner</label>
        <div class="col-sm-5">
       	 	<input type="text" class="form-control" id="owner" name="owner" value="${jd.owner }" style="height:35px;">
    	</div>
    </div>	
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="description">Description</label>
        <div class="col-sm-11">
       		<textarea class="form-control" id="description" name="description"  style="height:55px;">${jd.description }</textarea> 
        </div>
    </div>			                    				                    				                    		                   
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="qualification">Qualification</label>
        <div class="col-sm-11">
			<textarea class="form-control" id="qualification" name="qualification"  style="height:55px;">${jd.qualification }</textarea>							            
        </div>
    </div>
    <div class="form-group tr-new">    
        <label class="col-sm-1 control-label" for="notes">Notes</label>
        <div class="col-sm-11">
        	<textarea class="form-control" id="notes" name="notes"  style="height:55px;">${jd.notes }</textarea>
   		</div>
    </div>
</fieldset>