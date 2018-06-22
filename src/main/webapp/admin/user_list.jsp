<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>用户列表</title>
<%@include file="/admin/public/head.jsp"%>


</head>

<body>
	<!-- container section start -->
	<section id="container" class="">
		<%@include file="/admin/public/menu.jsp"%>
             <section id="main-content">
          <section class="wrapper">
	
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              <a href="userinit">添加用户</a>
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>
                                 <th>用户名</th>
                                 <th>中文名</th>
                                 <th>注册时间</th>
                                 <th>邮箱</th>
                                 <th>手机号</th>
                                 <th>状态</th>
                                 <th> 动作</th>
                              </tr>
                              <c:forEach items="${userList}" var="user">
                              <tr>
                                 <td><a href="userdetail?userId=${user.id}">${user.username }</a></td>
                                 <td>${user.usernameCn }</td>
                                 <td>${user.createTime }</td>
                                 <td>${user.email }</td>
                                 <td>${user.phone }</td>
                                 <td>${user.statusMsg}</td>
                                 <td>
                                  <div class="btn-group">
                                      <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                                  </div>
                                  </td>
                              </tr> 
                              </c:forEach>                           
                           </tbody>
                        </table>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      </section>

	</section>


	<!-- javascripts -->
	<script src="/admin/js/jquery.js"></script>
	<script src="/admin/js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="/admin/js/jquery.scrollTo.min.js"></script>
	<script src="/admin/js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- jquery knob -->
	<!--custome script for all page-->
	<script src="/admin/js/scripts.js"></script>

   <script>
	
		$(".knob").knob();
	</script>

</body>
</html>
