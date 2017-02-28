$(function(){
	$table=$('#table1');
   initTable();
});
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
    	sAjaxSource:ctx + '/JD/keyword/listPage', //'POST'to server url
    	sServerMethod:"POST",
    	 fnServerParams:function(aoData){
         	aoData.push(
         			{"name":"keyword","value":keyword}
         			);
         },
      
   	aoColumns:[
    		{mData: 'no'},
    		{mData:'title'},
    		{mData:'description'},
    		{sDefaultContent: 'Pending'},
    		{sDefaultContent: ''},
    		{sDefaultContent: ''}
    	],
    	fnRowCallback: function(nRow, aData, iDisplayIndex) {
    		//var tableSetings = this.fnSettings(); 
    		//var page_start = tableSetings._iDisplayStart;//查询页开始的索引
    		//$('td:eq(0)', nRow).html(page_start+iDisplayIndex+1);
    		//$('td:eq(0)', nRow).html(aData.no);
    		if(aData.duration>0){
    		$('td:eq(3)', nRow).html(aData.duration);
    		}
    		if(aData.status==1){
    			$('td:eq(4)', nRow).html('<span class="label-success label label-default">Active</span>');
    		}else if(aData.status==0){
    			$('td:eq(4)', nRow).html('<span class="label-error label label-default">Close</span>');
    		}
    		$('td:eq(5)', nRow).html('<a  href="#" onclick=ajaxContent(\"/JD/toEdit/\","no='+ aData.no +'");>'
	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit</a>&nbsp;'
	    		/*	+'<a class="btn btn-success" href="#" onclick=ajaxContent(\"/JD/toAddResource\","no='+ aData.no +'");>'
	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Add Resource</a>'*/);
    	},

    	fnInitComplete: function(oSettings, json) { 
            //
        }
    });
}

