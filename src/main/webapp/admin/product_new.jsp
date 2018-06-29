<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>添加新商品</title>
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
                                                                      添加新商品
                          </header>
                          <div class="panel-body">
                              <div class="form">
                                  <form class="form-validate form-horizontal " id="register_form" method="post">
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">商品名 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="username" name="name" type="text" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">类别<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                             <select class="control-label col-lg-2" name="category_id">
                                              <c:forEach items="${categoryList}" var="category">
                                                  <option value="${category.id }">${category.name}</option>
                                              
                                              </c:forEach>
                                             </select>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="address" class="control-label col-lg-2">中文标题 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="usernameCn" name="title" type="text"  />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="username" class="control-label col-lg-2">库存 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="username" name="stock" type="text"  />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="password" class="control-label col-lg-2">价格 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="password" name="price" type="text" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="confirm_password" class="control-label col-lg-2">详细描述<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " type="text" name="desc" />
                                          </div>
                                      </div>
                                     
                                      
                                      <div class="form-group">
                                          <div class="col-lg-offset-2 col-lg-10">
                                              <button class="btn btn-primary" type="submit" >确定</button>
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
			$.get("/servlet/user/changeStatus",{username:username,status:status},function(json){
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
