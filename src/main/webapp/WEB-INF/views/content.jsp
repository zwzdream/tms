<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div>
	<ul class="breadcrumb">
		<li><a href="${ctx}/Index/home">Home</a></li>
	</ul>
</div>

<div class="sortable row-fluid">
 	<div class="col-md-6 col-sm-6 col-xs-6">
        <a data-toggle="tooltip" title="6 new members." class="well top-block" href="#">
            <i class="glyphicon glyphicon-user blue"></i>

            <div>JD Count</div>
            <div>507</div>
            <span class="notification">6</span>
        </a>
    </div>

    <div class="col-md-6 col-sm-6 col-xs-6">
        <a data-toggle="tooltip" title="4 new pro members." class="well top-block" href="#">
            <i class="glyphicon glyphicon-star green"></i>

            <div>Resources Count</div>
            <div>228</div>
            <span class="notification green">4</span>
        </a>
    </div>  
</div>