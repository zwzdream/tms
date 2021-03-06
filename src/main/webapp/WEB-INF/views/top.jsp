<!-- topbar starts -->
<div class="navbar navbar-default" role="navigation">

    <div class="navbar-inner">
        <button type="button" class="navbar-toggle pull-left animated flip">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html"> <img alt="Charisma Logo" src="${ctx}/resources/ui/charisma/img/logo20.png" class="hidden-xs"/>
            <span>TMS</span></a>

        <!-- user dropdown starts -->
        <div class="btn-group pull-right">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username} </span>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <!--   <li><a href="#">Profile</a></li>
                <li class="divider"></li> -->
                <!--  <li><a  href="javascript:ajaxGet('/auth/login');">Login</a></li> -->
                <li><a  href="javascript:ajaxToLogout(logoutForm);">Logout</a></li>
            </ul>
        </div>
        <!-- user dropdown ends -->
       <form action="${ctx}/auth/logout" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	    </form>

        <!-- theme selector starts -->
        <!-- 
        <div class="btn-group pull-right theme-container animated tada">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-tint"></i><span
                    class="hidden-sm hidden-xs"> Change Theme / Skin</span>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" id="themes">
                <li><a data-value="classic" href="#"><i class="whitespace"></i> Classic</a></li>
                <li><a data-value="cerulean" href="#"><i class="whitespace"></i> Cerulean</a></li>
                <li><a data-value="cosmo" href="#"><i class="whitespace"></i> Cosmo</a></li>
                <li><a data-value="custom" href="#"><i class="whitespace"></i> Custom</a></li>
                <li><a data-value="cyborg" href="#"><i class="whitespace"></i> Cyborg</a></li>
                <li><a data-value="darkly" href="#"><i class="whitespace"></i> Darkly</a></li>
                <li><a data-value="flatly" href="#"><i class="whitespace"></i> Flatly</a></li>                
                <li><a data-value="journal" href="#"><i class="whitespace"></i> Journal</a></li>
                <li><a data-value="lumen" href="#"><i class="whitespace"></i> Lumen</a></li>
                <li><a data-value="paper" href="#"><i class="whitespace"></i> Paper</a></li>
                <li><a data-value="readable" href="#"><i class="whitespace"></i> Readable</a></li>
                <li><a data-value="sandstone" href="#"><i class="whitespace"></i> Sandstone</a></li>
                <li><a data-value="simplex" href="#"><i class="whitespace"></i> Simplex</a></li>
                <li><a data-value="slate" href="#"><i class="whitespace"></i> Slate</a></li>
                <li><a data-value="spacelab" href="#"><i class="whitespace"></i> Spacelab</a></li>
                <li><a data-value="superhero" href="#"><i class="whitespace"></i> Superhero</a></li>
                <li><a data-value="united" href="#"><i class="whitespace"></i> United</a></li>
           		<li><a data-value="yeti" href="#"><i class="whitespace"></i> Yeti</a></li>                
            </ul>
        </div> 
        -->
        <!-- theme selector ends -->

       <!--  
       <ul class="collapse navbar-collapse nav navbar-nav top-menu">
            <li><a href="#"><i class="glyphicon glyphicon-globe"></i> Visit Site</a></li>
            <li class="dropdown">
                <a href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-star"></i> Dropdown <span
                        class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                    <li class="divider"></li>
                    <li><a href="#">One more separated link</a></li>
                </ul>
            </li>
            <li>
                <form class="navbar-search pull-left">
                    <input placeholder="Search" class="search-query form-control col-md-10" name="query"
                           type="text">
                </form>
            </li>
        </ul> 
        -->

    </div>
</div>

<!-- topbar ends -->