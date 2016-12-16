$(function(){
	//取得所有不在该分组下的用户

	getCanJoinJDs();
	getTheBelongJDs();
	$('#multiselect').multiselect( {
		
		beforeMoveToLeft:function(){
			var resourceId=$("#resourceId").val();
			var resourceType=$("#resourceType").val();
			var jdId=$("#multiselect_to").val();
			$.ajax({
				url:ctx+"/Resource/deleteJDFromResource",
				data:{jdId:parseInt(jdId[0]),resourceId:resourceId,resourceType:resourceType},
				type:"post",
				dataType : 'html',
				//dataType : 'json',
			    cache:false,
			    success:function(data){
			    	if(data!=null){
					noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
					$('#resource_edit_jd').html(data);
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
			var resourceId=$("#resourceId").val();
			var resourceType=$("#resourceType").val();
			var jdId=$("#multiselect").val();
			  $.ajax({
					url:ctx+"/Resource/addJDToResource",
					data:{jdId:parseInt(jdId[0]),resourceId:resourceId,resourceType:resourceType},
					type:"post",
					dataType : 'html',
					//dataType : 'json',
				    cache:false,
				    success:function(data){
				    	if(data!=null){
						noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
						//$('#content').html(data);
						$('#resource_edit_jd').html(data);

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



function getCanJoinJDs(){
	var resourceId=$("#resourceId").val();
	var resourceType=$("#resourceType").val();
	var option;
	$.ajax({
		url:ctx+"/Resource/getCanJoinJDs",
		data:{resourceId:resourceId,resourceType:resourceType},
		type:"post",
		dataType : 'json',
	    cache:false,
	    success:function(data){
	    	var json = data.jdList;
			$.each(json,function(index, item) {
	    		//循环获取数据
	    		var no= json[index].no;
				var title = json[index].title;
	    		option += ("<option value='"+no+"'>"+title+"</option>");
	    	});
	    	$("#multiselect").html(option);
	    },
	    error:function(XMLHttpRequest, textStatus, errorThrown) {   
	    	noty({type:"error",text: "An internal error has occurred. Please contact your system administrator!", layout: "center", timeout: 3000});
	    },  
	    async: false
		
		
	});
	
}
function getTheBelongJDs(){
	var resourceId=$("#resourceId").val();
	var resourceType=$("#resourceType").val();
	var option;
	$.ajax({
		url:ctx+"/Resource/getTheBelongJDs",
		data:{resourceId:resourceId,resourceType:resourceType},
		type:"post",
		dataType : 'json',
		cache:false,
		success:function(data){
			var json = data.jdList;
			$.each(json,function(index, item) {
	    		//循环获取数据
				var no= json[index].no;
				var title = json[index].title;
	    		option += ("<option value='"+no+"'>"+title+"</option>");
	    	});
			
			$("#multiselect_to").html(option);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {   
			noty({type:"warning",text: "The user are not yet grouped!,you can assign a group to the user", layout: "center", timeout: 3000});
		},  
		async: false
		
		
	});
	
}

