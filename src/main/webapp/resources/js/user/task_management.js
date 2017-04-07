$(function(){
	$table=$('#taskTable');
   initTable();
    notEnter();
    listAllUser();
});

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
	    	$("#assignee").html(option);
	    },
	    error:function(XMLHttpRequest, textStatus, errorThrown) {   
	    	noty({type:"error",text: "An internal error has occurred. Please contact your system administrator!", layout: "center", timeout: 3000});
	    },  
	    async: false
		
		
	});
}


function notEnter()
{
	$(this).keydown( function(e) {
	    var key = window.event?e.keyCode:e.which;
	    if(key.toString() == "13"){
	        return false;
	    }
	});
}

function doQry(){
	var keyword = $('#keyword').val();
	if(keyword=='') {
		noty({type:"warning",text: "The keyword is empty! Please enter a keyword to accurately query.", layout: "bottom", timeout: 3000});
		initTable();
	}else{
		$table.dataTable().fnClearTable(false);
		initTable();
	}
	
}
function initTable(){
	var keyword = $('#keyword').val();
	$table.DataTable({
    	searching: false,
    	iDisplayLength: 10,//pagesize
    	aLengthMenu: [5, 10, 15, 20],
    	bLengthChange: true,//是否允许选择分页
    	bProcessing: true,
    	bServerSide: true,
    	destroy: true,
    	bFilter:false,
    	bSort:false,
    	bPaginate: true,
    	//sDom: "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
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
    	sAjaxSource:ctx + '/Task/keyword/listPage', //'POST'to server url
    	sServerMethod:"POST",
    	 fnServerParams:function(aoData){
         	aoData.push(
         			{"name":"keyword","value":keyword}
         			);
         },
      
   	aoColumns:[
    		{mData: 'name'},
    		{sDefaultContent: ''},
    		{sDefaultContent: ''},
    		{mData:'create_time'},
    		{mData:'duration'},
    		{sDefaultContent: ''},
    		{sDefaultContent: ''},
    		{sDefaultContent: ''}
    	],
    	fnRowCallback: function(nRow, aData, iDisplayIndex) {
    		//var tableSetings = this.fnSettings(); 
    		//var page_start = tableSetings._iDisplayStart;//查询页开始的索引
    		//$('td:eq(0)', nRow).html(page_start+iDisplayIndex+1);
    		//$('td:eq(0)', nRow).html(aData.no);
    		if(aData.priority==0){
    			$('td:eq(1)', nRow).html('<span class="label-primary label label-default">Common</span>');
    		}else if(aData.priority==1){
    			$('td:eq(1)', nRow).html('<span class="label-error label label-default">Priority</span>');
    		}else if(aData.priority==2){
    			$('td:eq(1)', nRow).html('<span class="label-danger label label-default">Emergency</span>');
    		}
    		if(aData.status==0){
    			$('td:eq(2)', nRow).html('<span class="label-danger label label-default">Closed</span>');
    		}else if(aData.status==1){
    			$('td:eq(2)', nRow).html('<span class="label-primary label label-default">Processing</span>');
    		}else if(aData.status==2){
    			$('td:eq(2)', nRow).html('<span class="label-success label label-default">Complete</span>');
    		}
    		if(aData.last_assignee!=null&&aData.last_assignee!=0){
    			$.ajax({
    				type : "get",
    				url : ctx + '/User/getUser/'+aData.last_assignee,
    				cache: false,
    				dataType : 'json',
    				success:function(data){
    					if(data !=""){
    						$('td:eq(5)', nRow).html(data.username);
    					}
    				}
    			});
    		}
    		if(aData.consummator!=null&&aData.consummator!=0){
    			 $.ajax({
    					type : "get",
    					url : ctx + '/User/getUser/'+aData.consummator,
    					cache: false,
    			        dataType : 'json',
    			        success:function(data){
    						if(data !=""){
    							$('td:eq(6)', nRow).html(data.username);
    						}
    					}
    			        });
    		}
    		if(aData.status==0){
    		$('td:eq(7)', nRow).html(
    				'<a  href="#" onclick=ajaxJSON(\"/Task/startTask\","id='+ aData.id +'",initTable);>'
    				+'<i class="glyphicon glyphicon-play icon-white"></i></a>&nbsp;'
    				+'<a  href="#" onclick=ajaxContent(\"/Task/toEdit\","id='+ aData.id +'");>'
	    			+'<i class="glyphicon glyphicon-edit icon-white"></i></a>&nbsp;'
	    			+'<a  href="#" onclick=ajaxContent(\"/Task/toAssign\","id='+ aData.id +'");>'
	    			+'<i class="glyphicon glyphicon-hand-right  icon-white"></i></a>'
	    			+'<a  href="#" onclick=ajaxJSON(\"/Task/completeTask\","id='+ aData.id +'",initTable);>'
	    			+'<i class="glyphicon glyphicon-ok  icon-white"></i></a>');
    		}else if(aData.status==1){
    			$('td:eq(7)', nRow).html(
        				'<a href="#" >'
        				+'<i class="glyphicon glyphicon-play icon-white" style="color:gray;"></i></a>&nbsp;'
        				+'<a  href="#" onclick=ajaxContent(\"/Task/toEdit\","id='+ aData.id +'");>'
    	    			+'<i class="glyphicon glyphicon-edit icon-white"></i></a>&nbsp;'
    	    			+'<a  href="#" onclick=ajaxContent(\"/Task/toAssign\","id='+ aData.id +'");>'
    	    			+'<i class="glyphicon glyphicon-hand-right  icon-white"></i></a>'
    	    			+'<a  href="#" onclick=ajaxJSON(\"/Task/completeTask\","id='+ aData.id +'",initTable);>'
    	    			+'<i class="glyphicon glyphicon-ok  icon-white"></i></a>');
    		}else if(aData.status==2){
    			$('td:eq(7)', nRow).html(
    			'<a href="#">'
				+'<i class="glyphicon glyphicon-play icon-white" style="color:gray;"></i></a>&nbsp;'
				+'<a  href="#" onclick=ajaxContent(\"/Task/toEdit\","id='+ aData.id +'");>'
    			+'<i class="glyphicon glyphicon-edit icon-white"></i></a>&nbsp;'
    			+'<a  href="#" onclick=ajaxContent(\"/Task/toAssign\","id='+ aData.id +'");>'
    			+'<i class="glyphicon glyphicon-hand-right  icon-white"></i></a>'
    			+'<a href="#">'
    			+'<i class="glyphicon glyphicon-ok  icon-white" style="color:gray;"></i></a>');
    			
    		}
    	},

    	fnInitComplete: function(oSettings, json) { 
            //
        }
    });
}

