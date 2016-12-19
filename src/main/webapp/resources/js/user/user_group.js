$(function(){
	//取得所有不在该分组下的用户

	getCanJoinGroups();
	getTheBelongGroup();
	$('#multiselect').multiselect( {
		
		beforeMoveToLeft:function(){
			var userId=$("#id").val();
			var userName=$("#userName").text();
			var groupId=$("#multiselect_to").val();
			$.ajax({
				url:ctx+"/UserGroup/editTheBelongGroup",
				data:{groupId:parseInt(groupId[0]),userId:userId,userName:userName},
				type:"post",
				dataType : 'html',
			    cache:false,
			    success:function(data){
			    	if(data !=""){
					noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
					$('#user_group').html(data);
				//	$('#content').html(data);
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
			 var userId=$("#id").val();
			 var userName=$("#userName").text();
			  var groupId=$("#multiselect").val();
				console.log(userName);
			  $.ajax({
					url:ctx+"/UserGroup/editTheBelongGroup",
					data:{groupId:parseInt(groupId[0]),userId:userId,userName:userName},
					type:"post",
					dataType : 'html',
				    cache:false,
				    success:function(data){
				    	if(data !=""){
						noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
						$('#user_group').html(data);
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



function getCanJoinGroups(){
	var id=$("#id").val();
	var option;
	$.ajax({
		url:ctx+"/UserGroup/getCanJoinGroups",
		data:{userId:id},
		type:"post",
		dataType : 'html',
	    cache:false,
	    success:function(data){
	    	var json = eval(data);
	    	$.each(json,function(index, item) {
	    		//循环获取数据
	    		var groupid= json[index].id;
				var groupname = json[index].name;
	    		option += ("<option value='"+groupid+"'>"+groupname+"</option>");
	    	});
	    	$("#multiselect").html(option);
	    },
	    error:function(XMLHttpRequest, textStatus, errorThrown) {   
	    	noty({type:"error",text: "An internal error has occurred. Please contact your system administrator!", layout: "center", timeout: 3000});
	    },  
	    async: false
		
		
	});
	
}
function getTheBelongGroup(){
	var id=$("#id").val();
	var option;
	$.ajax({
		url:ctx+"/UserGroup/getTheBelongGroup",
		data:{userId:id},
		type:"post",
		dataType : 'json',
		cache:false,
		success:function(data){
			var json = eval(data);
			var groupid= json.id;
			var groupname = json.name;
			option += ("<option value='"+groupid+"'>"+groupname+"</option>");
			
			$("#multiselect_to").html(option);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {   
			noty({type:"warning",text: "The user are not yet grouped!,you can assign a group to the user", layout: "center", timeout: 3000});
		},  
		async: false
		
		
	});
	
}

