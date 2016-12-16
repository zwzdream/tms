<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div>
	<ul class="breadcrumb">
		<li><a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a></li>
	</ul>
</div>

<div class="sortable row-fluid">
 	<div class="col-md-6 col-sm-6 col-xs-6">
        <a data-toggle="tooltip" title=" ${curJDCount } new members." class="well top-block" href="javascript:ajaxGet('/Index/home/JD');">
            <i class="glyphicon glyphicon-user blue"></i>

            <div>JD Count</div>
            <div>${jdCount }</div>
            <span class="notification">${curJDCount }</span>
        </a>
    </div>

    <div class="col-md-6 col-sm-6 col-xs-6">
        <a data-toggle="tooltip" title=" ${curResourceCount } new pro members." class="well top-block" href="javascript:ajaxGet('/Index/home/resource');">
            <i class="glyphicon glyphicon-star green"></i>

            <div>Resources Count</div>
            <div>${resourceCount}</div>
            <span class="notification green">${curResourceCount }</span>
        </a>
    </div>  
</div>