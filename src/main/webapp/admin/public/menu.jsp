<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<header class="header dark-bg">
	<div class="toggle-nav">
		<div class="icon-reorder tooltips"
			data-original-title="Toggle Navigation" data-placement="bottom"></div>
	</div>

	<!--logo start-->
	<a href="/servlet/index" class="logo">Wapple</a>
	<!--logo end-->

	<div class="nav search-row" id="top_menu">
		<!--  search form start -->
		<ul class="nav top-menu">
			<li>
				
			</li>
		</ul>
		<!--  search form end -->
	</div>

	<div class="top-nav notification-row">
		<!-- notificatoin dropdown start-->
		<ul class="nav pull-right top-menu">


	
			<!-- alert notification end-->
			<!-- user login dropdown start-->
			<li class="dropdown"><a data-toggle="dropdown"
				class="dropdown-toggle" href="#"> <span class="profile-ava">
						<img alt="" src="/admin/img/avatar1_small.jpg">
				</span> <span class="username">Jenifer Smith</span> <b class="caret"></b>
			</a>
				<ul class="dropdown-menu extended logout">
					<div class="log-arrow-up"></div>
					<li class="eborder-top"><a href="#"><i
							class="icon_profile"></i> My Profile</a></li>
					<li><a href="#"><i class="icon_mail_alt"></i> My Inbox</a></li>
					
				</ul></li>
			<!-- user login dropdown end -->
		</ul>
		<!-- notificatoin dropdown end-->
	</div>
</header>
<aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">
		   <li><a href="/servlet/index">
					<span>后台主页</span>
			</a></li>
			<li><a class="" href="/servlet/userlist">
					<span>用户管理</span>
			</a></li>
		     <li><a class="" href="/servlet/menulist">
					<span>菜单管理</span>
			</a></li>
             <li><a class="" href="/servlet/productlist">
					<span>商品管理</span>
			</a></li>
	
			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_documents_alt"></i> <span>Pages</span> <span
					class="menu-arrow arrow_carrot-right"></span>
			</a>
				<ul class="sub">
					<li><a class="" href="profile.html">Profile</a></li>
					<li><a class="" href="login.html"><span>Login Page</span></a></li>
					<li><a class="" href="blank.html">Blank Page</a></li>
					<li><a class="" href="404.html">404 Error</a></li>
				</ul></li>

		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>