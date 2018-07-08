<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>用户详情</title>
<%@include file="/admin/public/head.jsp"%>
</head>

<body>
	<!-- container section start -->
	<section id="container" class="">
		<%@include file="/admin/public/menu.jsp" %>
               <section id="main-content">
          <section class="wrapper">
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                             Advanced Form validations
                          </header>
                          <div class="panel-body">
                              <div class="form">
                                  <form class="form-validate form-horizontal " id="register_form" method="get" action="">
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">用户名 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="username" name="fullname" value="${user.username}" type="text" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="address" class="control-label col-lg-2">中文名 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="usernameCn" name="address" type="text" value="${user.usernameCn}"  />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="username" class="control-label col-lg-2">邮箱 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="username" name="username" type="text" value="${user.email }" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="password" class="control-label col-lg-2">电话 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="password" name="password" type="text" value="${user.phone }"/>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="confirm_password" class="control-label col-lg-2">wenti<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="confirm_password" name="confirm_password" type="password" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="email" class="control-label col-lg-2">Email <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="email" name="email" type="email" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="agree" class="control-label col-lg-2 col-sm-3">用户状态 </label>
                                          <div class="col-lg-10 col-sm-9">
                                           <c:if test="${user.status==-1}">
                                                                                                                       未认证 [<a href="javascript:;" onclick="changeStatus(0,'${user.username}');">通过认证</a>]
                                           </c:if>                                                                            
                                           <c:if test="${user.status==0}">
                                                                                                                       状态正常 [<a href="javascript:;" onclick="changeStatus(-2,'${user.username}');">禁用 </a>]
                                           </c:if>
                                           <c:if test="${user.status==-2}">
                                                                                                                       已经禁用 [<a href="javascript:;" onclick="changeStatus(0,'${user.username}');">启用 </a>]
                                           </c:if>                                                       
                                          </div>
                                          
                                      </div>
                                      <div class="form-group">
                                          <div class="col-lg-offset-2 col-lg-10">
                                              <button class="btn btn-primary" type="submit"  disabled="disabled" >Save</button>
                                              <button class="btn btn-default" type="button" disabled="disabled">Cancel</button>
                                          </div>
                                      </div>
                                  </form>
                              </div>
                          </div>
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
		//knob
		$(".knob").knob();
		function changeStatus(status,username){
			$.get("/servlet/admin/changeUserStatus.ajax.vhtml",{username:username,status:status},function(json){
				if(json.success){
					 alert(json.data);
					 location.reload();
				}else{
					 alert(json.msg);
				}
			});
		}
	</script>

</body>
</html>
