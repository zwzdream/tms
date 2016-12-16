$(function(){
	//取得所有不在该分组下的用户

	getCanJoinResources();
	getTheBelongResources();
	$('#multiselect').multiselect( {
		
		beforeMoveToLeft:function(){
			var jdId=$("#id").val();
			//var jdTitle=$("#title").text();
			var resourceId=$("#multiselect_to").val();
			var resourceName=$('#multiselect_to option:selected').text();
			$.ajax({
				url:ctx+"/Resource/deleteResourceFromJD",
				data:{resourceId:parseInt(resourceId[0]),jdId:jdId,resourceName:resourceName},
				type:"post",
				dataType : 'html',
			    cache:false,
			    success:function(data){
			    	if(data!=null){
					noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
					//$('#content').html(data);
					$('#jd_add_resource').html(data);
					return true;
					
				}
			    },
			    error:function(XMLHttpRequest, textStatus, errorThrown) {   
			    	noty({type:"error",text: "Edit failed!", layout: "center", timeout: 3000});
			    	return false;
			    	},  
			    async: false
				
				
			});
			 
		},
		beforeMoveToRight:function(){
			 var jdId=$("#id").val();
			// var jdTitle=$("#title").text();
			  var resourceId=$("#multiselect").val();
			  var resourceName=$('#multiselect option:selected').text();
			  $.ajax({
					url:ctx+"/Resource/addResourceToJD",
					data:{resourceId:parseInt(resourceId[0]),jdId:jdId,resourceName:resourceName},
					type:"post",
					dataType : 'html',
				    cache:false,
				    success:function(data){
				    	if(data!=null){
						noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
						//$('#content').html(data);
						$('#jd_add_resource').html(data);
						return true;
					}
				    },
				    error:function(XMLHttpRequest, textStatus, errorThrown) {   
				    	noty({type:"error",text: "Edit failed!", layout: "center", timeout: 3000});
				    	return false;
				    	},  
				    async: false
				});
			
			
		}
	
	
	    
	});

});



function getCanJoinResources(){
	var no=$("#id").val();
	var option;
	$.ajax({
		url:ctx+"/Resource/getCanJoinResources",
		data:{no:no},
		type:"post",
		dataType : 'json',
	    cache:false,
	    success:function(data){
	    	var json = data.resources;
			$.each(json,function(index, item) {
	    		//循环获取数据
	    		var rid= json[index].id+"("+json[index].type+")";
				var rname = json[index].firstName+"-"+json[index].lastName+"("+json[index].type+")";
	    		option += ("<option value='"+rid+"'>"+rname+"</option>");
	    	});
	    	$("#multiselect").html(option);
	    },
	    error:function(XMLHttpRequest, textStatus, errorThrown) {   
	    	noty({type:"error",text: "An internal error has occurred. Please contact your system administrator!", layout: "center", timeout: 3000});
	    },  
	    async: false
		
		
	});
	
}
function getTheBelongResources(){
	var no=$("#id").val();
	var option;
	$.ajax({
		url:ctx+"/Resource/getTheBelongResources",
		data:{no:no},
		type:"post",
		dataType : 'json',
		cache:false,
		success:function(data){
			var json = data.resources;
			$.each(json,function(index, item) {
	    		//循环获取数据
	    		var rid= json[index].id+"("+json[index].type+")";
				var rname =json[index].firstName+"-"+json[index].lastName+"("+json[index].type+")";
	    		option += ("<option value='"+rid+"'>"+rname+"</option>");
	    	});
			
			$("#multiselect_to").html(option);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {   
			noty({type:"warning",text: "The user are not yet grouped!,you can assign a group to the user", layout: "center", timeout: 3000});
		},  
		async: false
		
		
	});
	
}

