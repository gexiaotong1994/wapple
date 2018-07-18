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
                              <a href="new">添加视频</a>
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>
                                 <th>视频名</th>
                                 <th>类型</th>
                                 <th>视频URL</th>
                                 <th>添加下部</th>
                                <th>操作</th>
                              </tr>
                              <c:forEach items="${videoVos}" var="v">
                              <tr>
                                 <td><a href="${v.id}">${v.nameCn}</a></td>
                                 <td>${v.mtypeStr}</td>
                                 <td>${v.url}</td>
                                 <td><a href="${v.name}/next_${v.type}">link</a></td>
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
