<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>商品列表</title>
<%@include file="/admin/public/head.jsp"%>


</head>

<body>
	<section id="container" class="">
		<%@include file="/admin/public/menu.jsp"%>
             <section id="main-content">
          <section class="wrapper">
	
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              <a href="/back/product/new">添加新商品</a>
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>
                                 <th>商品</th>
                                 <th>库存</th>
                                 <th>价格</th>
                                 <th>售出(个)</th>
                                 <th>创建时间</th>
                                 <th>修改时间</th>
                                 <th>首页图</th>
                                 <th>删除</th>
                                 
                              </tr>
                              <c:forEach items="${productListVos}" var="productListVo">
                              <tr>
                                 <td><a href="/back/product/${productListVo.id}">${productListVo.vname}</a></td>
                                 <td>${productListVo.stock}</td>
                                 <td>${productListVo.price}</td>
                                 <td>${productListVo.sales}</td>
                                 <td>${productListVo.createTime}</td>
                                 <td>${productListVo.updateTime}</td>
                                <td>
                                 <c:choose>
                                  <c:when test="${productListVo.image}">
                                                                                                    已上传
                                  </c:when>
                                  <c:when test="${!productListVo.image}">
                                                                                                     未上传<a href="#" onclick="upload_image('${productListVo.id}');">点击上传</a>
                                  </c:when>
                                 </c:choose>
                                </td>
                                 <td>
                                  <div class="btn-group">
                                      <a class="btn btn-danger" href="javascript:f_delete(${productListVo.id});"><i class="icon_close_alt2"></i></a>
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
		function upload_image(pid){
		    var url="/back/upload/"+pid;
			var name='商品文件主图上传';
			var params='height=500, width=500';
		    window.open(url,name,params);
		}
		function f_delete(productId){
			var cong=confirm("确认删除编号["+productId+"]的商品");
			if(cong){
				$.ajax({
					url:"/back/product/"+productId,
				    type:"detele",
				    dataType:"json",
				    success:function(json){
				    	if(json.success){
				    		alert(json.data);
				    	}else{
				    		alert(json.msg);
				    	}
				    
				    }
				});
			}
		}
	</script>

</body>
</html>
