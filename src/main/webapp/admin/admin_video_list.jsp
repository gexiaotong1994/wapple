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
                                 <td><a href="quickAdd?videoName=${v.name}&videoType=${v.type}">快速添加系列</a></td>
                                 <td>
                                  <div class="btn-group">
                                      <a class="btn btn-danger" href="javascript:del(${v.id });"><i class="icon_close_alt2"></i></a>
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


<%@include file="/admin/public/footer-script.jsp"%>

	
<script type="text/javascript">
       function del(id){
    	  var r=confirm("确认删除["+id+"]?");
    	   if(r){
    		  $.ajax({
    			  url:id,
    			  type:"delete",
    			  dataType:"json",
    			  success:function(json){
    				  if(json){
    					  alert("删除["+id+"]成功!");
    					  location.reload();
    				  }else{
    					  alert("删除["+id+"]失败!");
    				  }
    			  }
    			  
    		  });
    	   }
       }


</script>
</body>
</html>
