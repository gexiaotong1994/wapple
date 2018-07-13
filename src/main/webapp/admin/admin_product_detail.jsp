<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>商品详情</title>
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
                                  <form class="form-validate form-horizontal" onsubmit="return false;">
                                      <div class="form-group ">
                                          <input type="hidden" value="${product.id}" id="pid">
                                          <label for="fullname" class="control-label col-lg-2">商品名 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="name" name="fullname" value="${product.name}" type="text" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="address" class="control-label col-lg-2">标题<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="title" name="address" type="text" value="${product.title}"  />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="username" class="control-label col-lg-2">价格<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="price" name="username" type="text" value="${product.price}" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="password" class="control-label col-lg-2">库存<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="stock" name="password" type="text" value="${product.stock}"/>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="confirm_password" class="control-label col-lg-2">图片名称<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="confirm_password" name="confirm_password" value="${product.mainImage}" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="email" class="control-label col-lg-2">详细描述<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="desc" " type="text" value="${product.desc}" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="email" class="control-label col-lg-2">商品类别<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="email" name="email" type="email" value="${product.category.name}" readonly="readonly" />
                                          </div>
                                      </div>
                                      <div class="form-group">
                                          <div class="col-lg-offset-2 col-lg-10">
                                              <button class="btn btn-primary" type="submit" onclick="update();" >修改</button>
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
		function upload_image(pid){
		    var url="/servlet/amdin/upload.vhtml?type=product_main_image&productId="+pid;
			var name='商品文件主图上传';
			var params='height=100, width=400, toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no';
		    window.open(url,name,params);
		}
		
		function update(){
			var product={
				name:$("#name").val(),
				title:$("#title").val(),
				stock:$("#stock").val(),
				price:$("#price").val(),
				desc:$("#desc").val(),
			};
			
			$.ajax({
			   url:window.location.href,
			   type:"put",
			   dataType:"json",
			   data:product,
			   success:function(json){
				   if(json.success){
					   alert(json.data)
				   }else{
					   alert(json.msg)
				   }
			   }
			})
			
		}
		
		
	</script>
     
</body>
</html>
