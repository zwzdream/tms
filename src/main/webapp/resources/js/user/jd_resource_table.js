$(function(){
	//取得所有不在该分组下的用户
	$joinTable=$("#joinTable");
	$belongTable=$("#belongTable");
	listCanJoinResources();
	listTheBelongResources();
});

function searchCanJoinResource(url){
	var keyWord = $('#keyWord').val();
	if(keyWord=='') {
		noty({type:"warning",text: "The keyword is empty! Please enter it.", layout: "bottom", timeout: 3000});
		listCanJoinResources();
	}else{
		$joinTable.dataTable().fnClearTable(false);
		listCanJoinResources();
		
		
	}

}

function listCanJoinResources(){
	var no=$("#id").val();
	var keyWord = $('#keyWord').val();
	$joinTable.DataTable({
    	searching: false,
    	iDisplayLength: 5,//pagesize
    	aLengthMenu: [5, 10, 15, 20],
    	bLengthChange: true,//是否允许选择分页
    	bProcessing: true,
    	bServerSide: true,
    	destroy: true,
    	bFilter:false,
    	bSort:false,
    	bPaginate: true,
       // sPaginationType: "bootstrap",
    	sDom: '<"top"f>rt<"bottom"ip><"clear">',
        oLanguage: {
        	"sProcessing":"Loading......",
        	"sLengthMenu":" _MENU_ records per page",
            "sZeroRecords":"NO Records for Table!",
            "sSearch":"Search",
            "oPaginate" : {
                "sFirst" : "First",
                "sPrevious" : "Previous",
                "sNext" : "Next",
                "sLast" : "Last"
            }
        },
    	sAjaxSource:ctx + "/Resource/listCanJoinResources", //'POST'to server url
    	sServerMethod:"POST",
    	 fnServerParams:function(aoData){
         	aoData.push({"name":"no","value":no});
         	aoData.push({"name":"keyWord","value":keyWord});
         },
      
     	aoColumns: [
			 {sDefaultContent: ''},
	         {mData: 'name'},
	         {mData: 'industryExperience'},
	         {mData: 'title'},
	         {mData: 'location'},
	         {mData: 'workEligibility'},
	         {sDefaultContent: ''},
	],
    	fnRowCallback: function(nRow, aData, iDisplayIndex) {
    		var tableSetings = this.fnSettings(); 
    		var page_start = tableSetings._iDisplayStart;//查询页开始的索引
    		$('td:eq(0)', nRow).html(page_start+iDisplayIndex+1);
    		$('td:eq(6)', nRow).html('<a  href="#" onclick=ajaxContent(\"/Resource/toEditFromJD\","resourceId='+ aData.id+'&no='+no+'");>'
	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit</a>&nbsp;'
	    			+'<a  href="#" onclick=ajaxContent(\"/Resource/addTheResourceToJD\","rId='+ aData.id+'&jdId='+no+'");>'
	    			+'<i class="glyphicon glyphicon-plus icon-white"></i>Add</a>');
    	},

    	fnInitComplete: function(oSettings, json) { 
            //
        }
    });
		
}
function listTheBelongResources(){
	var no=$("#id").val();
	var keyword = $('#keyword').val();
	$belongTable.DataTable({
    	searching: false,//去掉搜索框
    	iDisplayLength: 5,//pagesize
    	aLengthMenu: [5, 10, 15, 20],
    	bLengthChange: true,//是否允许选择分页
    	bProcessing: true,
    	bServerSide: true,
    	destroy: true,
    	bFilter:false,
    	bSort:false,
    	bPaginate: true,
       // sPaginationType: "bootstrap",
    	sDom: '<"top"f>rt<"bottom"ip><"clear">',
        oLanguage: {
        	"sProcessing":"Loading......",
        	"sLengthMenu":" _MENU_ records per page",
            "sZeroRecords":"NO Records for Table!",
            "sSearch":"Search",
            "oPaginate" : {
                "sFirst" : "First",
                "sPrevious" : "Previous",
                "sNext" : "Next",
                "sLast" : "Last"
            }
        },
    	sAjaxSource:ctx + "/Resource/listTheBelongResources", //'POST'to server url
    	sServerMethod:"POST",
    	 fnServerParams:function(aoData){
         	aoData.push({"name":"no","value":no});
         },
      
     	aoColumns: [
			 {sDefaultContent: ''},
	         {mData: 'name'},
	         {mData: 'industryExperience'},
	         {mData: 'title'},
	         {mData: 'location'},
	         {mData: 'workEligibility'},
	         {sDefaultContent: ''},
	],
   	fnRowCallback: function(nRow, aData, iDisplayIndex) {
   		var tableSetings = this.fnSettings(); 
   		var page_start = tableSetings._iDisplayStart;//查询页开始的索引
   		$('td:eq(0)', nRow).html(page_start+iDisplayIndex+1);
   		$('td:eq(6)', nRow).html('<a  href="#" onclick=ajaxContent(\"/Resource/toEditFromJD2\","resourceId='+ aData.id+'&no='+no+'");>'
	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit</a>&nbsp;'
	    			+'<a  href="#" onclick=ajaxContent(\"/Resource/deleteTheResourceFromJD\","rId='+ aData.id+'&jdId='+no+'");>'
	    			+'<i class="glyphicon glyphicon-remove icon-white"></i>Remove</a>'
	    			);
   	},

    	fnInitComplete: function(oSettings, json) { 
            //
        }
    });
	
}

function addTheResource(rId,no){
	 $.ajax({
			url:ctx+"/Resource/addTheResourceToJD",
			data:{rId:rId,jdId:no},
			type:"post",
			dataType : 'html',
		    cache:false,
		    success:function(data){
		    	if(data!=null){
				noty({type:"success",text: "Add successed!", layout: "center", timeout: 3000});
				$('#content').html(data);
				//$('#jd_add_resource').html(data);
				return true;
			}
		    },
		    error:function(XMLHttpRequest, textStatus, errorThrown) {   
		    	noty({type:"error",text: "Add failed!", layout: "center", timeout: 3000});
		    	return false;
		    	},  
		    async: false
		});
}

function removeTheResource(rId,no){
	$.ajax({
		url:ctx+"/Resource/deleteTheResourceFromJD",
		data:{rId:rId,jdId:no},
		type:"post",
		dataType : 'html',
		cache:false,
		success:function(data){
			if(data!=null){
				noty({type:"success",text: "Remove successed!", layout: "center", timeout: 3000});
				$('#content').html(data);
				//$('#jd_add_resource').html(data);
				return true;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {   
			noty({type:"error",text: "Remove failed!", layout: "center", timeout: 3000});
			return false;
		},  
		async: false
	});
}


