$(function(){
	//取得所有不在该分组下的用户

	getAllNotIncludedUser();
	getAllIncludedUser();
	$('#multiselect').multiselect( {
		
		beforeMoveToLeft:function(){
			var groupId=$("#id").val();
			var groupName=$("#groupName").text();
			var userId=$("#multiselect_to").val();
			$.ajax({
				url:ctx+"/UserGroup/removeRole",
				data:{groupId:groupId,userId:parseInt(userId[0]),groupName:groupName},
				type:"post",
				dataType : 'html',
			    cache:false,
			    success:function(data){
			    	if(data !=""){
					noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
					$('#group_user').html(data);
					//$('#content').html(data);
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
			 var groupId=$("#id").val();
			 var groupName=$("#groupName").text();
			  var userId=$("#multiselect").val();
			  $.ajax({
					url:ctx+"/UserGroup/addRole",
					data:{groupId:groupId,userId:parseInt(userId[0]),groupName:groupName},
					type:"post",
					dataType : 'html',
				    cache:false,
				    success:function(data){
				    	if(data !=""){
						noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
						$('#group_user').html(data);
						//$('#content').html(data);
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



function getAllNotIncludedUser(){
	var id=$("#id").val();
	var option;
	$.ajax({
		url:ctx+"/UserGroup/getAllNotIncludedUser",
		data:{groupId:id},
		type:"post",
		dataType : 'html',
	    cache:false,
	    success:function(data){
	    	var json = eval(data);
	    	$.each(json,function(index, item) {
	    		//循环获取数据
	    		var userid= json[index].id;
				var username = json[index].username;
	    		option += ("<option value='"+userid+"'>"+username+"</option>");
	    	});
	    	$("#multiselect").html(option);
	    },
	    error:function(XMLHttpRequest, textStatus, errorThrown) {   
	    	noty({type:"error",text: "An internal error has occurred. Please contact your system administrator!", layout: "center", timeout: 3000});
	    },  
	    async: false
		
		
	});
	
}
function getAllIncludedUser(){
	var id=$("#id").val();
	var option;
	$.ajax({
		url:ctx+"/UserGroup/getAllIncludedUser",
		data:{groupId:id},
		type:"post",
		dataType : 'json',
		cache:false,
		success:function(data){
			var json = eval(data);
			$.each(json,function(index, item) {
				//循环获取数据
				var userid= json[index].id;
				var username = json[index].username;
				option += ("<option value='"+userid+"'>"+username+"</option>");
			});
			$("#multiselect_to").html(option);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {   
			noty({type:"error",text: "An internal error has occurred. Please contact your system administrator!", layout: "center", timeout: 3000});
		},  
		async: false
		
		
	});
	
}

