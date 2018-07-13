<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>用户详情</title>
<%@include file="/admin/public/head.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                             Advanced Form validations
                          </header>
                          <div class="panel-body">
                              <div class="form">
                                  <form class="form-validate form-horizontal " id="register_form" method="get" action="">
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">视频名(英语)<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="fullname" type="text" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="address" class="control-label col-lg-2">视频名(汉语) <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="usernameCn" name="address" type="text"  />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="username" class="control-label col-lg-2">选择国家和地区</label>
                                          <div class="col-lg-10">
                                              <c:forEach items="${countrys}" var="country">
                                               <span>${country.nameCn}</span><input type="radio" name="countryId" value="${country.id}"> 
                                               </c:forEach>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="password" class="control-label col-lg-2">出品公司 <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <select name="companyId">
                                                 <c:forEach items="${companys}" var="company">
                                                    <option value="${company.id}">${company.nameCn}</option>
                                                 </c:forEach>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="confirm_password" class="control-label col-lg-2">视频语言<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <c:forEach items="${languages}" var="language">
                                                <span>${language.nameCn}</span><input type="checkbox" name="languages" value="${country.id}"> 
                                               </c:forEach>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="confirm_password" class="control-label col-lg-2">字幕<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <c:forEach items="${languages}" var="language">
                                                <span>${language.nameCn}</span><input type="checkbox" name="languages" value="${country.id}"> 
                                               </c:forEach>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="email" class="control-label col-lg-2">Email<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="email" name="email" type="email" />
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
