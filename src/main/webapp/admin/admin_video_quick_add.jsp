<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>快速添加 新视频</title>
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
                                  <form class="form-validate form-horizontal " action="/back/videos/new" id="register_form" method="post" enctype=multipart/form-data>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">视频名(英语)<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="name" type="text" value="${video.name }" readonly="readonly" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="address" class="control-label col-lg-2">视频名(汉语) <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="usernameCn" name="title" type="text"  value="${video.title }" readonly="readonly" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="username" class="control-label col-lg-2">视频类型</label>
                                          <div class="col-lg-10" id="type_div">
                                              <c:forEach items="${videoTypes}" var="videoType">
                                               <span>${videoType.nameCn}</span><input type="radio" name="videoTypeId" value="${videoType.id}"> 
                                               </c:forEach>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="username" class="control-label col-lg-2">选择国家和地区</label>
                                          <div class="col-lg-10" id="country_div">
                                              <c:forEach items="${countrys}" var="country">
                                               <span>${country.nameCn}</span><input type="radio" name="countryId" value="${country.id}"> 
                                               </c:forEach>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="password" class="control-label col-lg-2">出品公司 <span class="required">*</span></label>
                                          <div class="col-lg-10" id="company_div">
                                              <select name="companyId">
                                                 <c:forEach items="${companys}" var="company">
                                                    <option value="${company.id}">${company.nameCn}</option>
                                                 </c:forEach>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">导演<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="director" type="text"  value="${video.director}"/>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">演员<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="actor" type="text" value="${video.actor}" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">季数<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="season" type="text" value="${video.season}"  readonly="readonly" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">第几集(适用于电视剧)<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="episode" type="text" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">需要多少积分<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="jf" type="text" value="${video.jf}" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">会员级别<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="vipLevel" type="text" value="${video.vipLevel}" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="confirm_password" class="control-label col-lg-2">视频语言<span class="required">*</span></label>
                                          <div class="col-lg-10" id="lan_div">
                                              <c:forEach items="${languages}" var="language">
                                                <span>${language.nameCn}</span><input type="checkbox" name="languageArr" value="${language.id}"> 
                                               </c:forEach>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="confirm_password" class="control-label col-lg-2">字幕<span class="required">*</span></label>
                                          <div class="col-lg-10" id="sub_div">
                                              <c:forEach items="${languages}" var="language">
                                                <span>${language.nameCn}</span><input type="checkbox"  name="subtitleArr" value="${language.id}"> 
                                               </c:forEach>
                                          </div>
                                      </div>
                                        <div class="form-group ">
                                          <label for="confirm_password" class="control-label col-lg-2">视频小类<span class="required">*</span></label>
                                          <div class="col-lg-10" id="mtype_div">
                                              <c:forEach items="${videoMillTypes}" var="videoMillType">
                                                <span>${videoMillType.nameCn}</span><input type="checkbox" name="videoMillTypeArr" value="${videoMillType.id}"> 
                                               </c:forEach>
                                          </div>
                                      </div>
                                       <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">星级<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="start" type="text" value="${video.start}" />
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">排序权重<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="name" name="weight" type="text" value="${video.weight}" />
                                          </div>
                                      </div>
                                      
                                      <div class="form-group ">
                                          <label for="email" class="control-label col-lg-2">上传封面<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                                <input type="file" name="mainImageFile">
                                          </div>
                                      </div>
                                       <div class="form-group ">
                                          <label for="fullname" class="control-label col-lg-2">简介<span class="required">*</span></label>
                                          <div class="col-lg-10">
                                               <textarea rows="10" cols="30"  name="desc"></textarea>
                                          </div>
                                      </div>
                                      <div class="form-group">
                                          <div class="col-lg-offset-2 col-lg-10">
                                              <button class="btn btn-primary" type="submit" >Save</button>
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
    <script type="text/javascript">
       function video_init(params){
    	    var tid=params.tid//类型
    	    var cid=params.cid;
    	    var mids=params.mids.split(",");
    	    var lans=params.lans.split(",");
    	    var subs=params.subs.split(",");
    	    var cmid=params.cmid;
    	    $("#type_div input").each(function(i){
    	    	if(tid==this.value){
    	    		$(this).attr("checked","checked")
    	    	}
    	    });
    	    
    	    $("#country_div input").each(function(i){
    	    	if(cid==this.value){
    	    		$(this).attr("checked","checked")
    	    	}
    	    });
    	    $("#lan_div input").each(function(index){
    	    	for(var i=0;i<lans.length;i++){
    	    		if(lans[i]==this.value){
        	    		$(this).attr("checked","checked")
        	    	}
    	    	}
    	    	
    	    });
    	    $("#sub_div input").each(function(index){
    	    	for(var i=0;i<subs.length;i++){
    	    		if(subs[i]==this.value){
        	    		$(this).attr("checked","checked")
        	    	}
    	    	}
    	    	
    	    });
    	    $("#mtype_div input").each(function(index){
    	    	for(var i=0;i<mids.length;i++){
    	    		if(mids[i]==this.value){
        	    		$(this).attr("checked","checked")
        	    	}
    	    	}
    	    	
    	    });
    	    $("#company_div option").each(function(i){
    	    	if(cmid==this.value){
    	    		$(this).attr("selected","selected")
    	    	}
    	    });
    	       
    	    
       }
       video_init({
    	   tid:"${video.type}",
    	   mids:"${video.mtypes}",
    	   cmid:"${video.companyId}",
    	   lans:"${video.languageIds}",
    	   subs:"${video.subtitles}",
    	   cid:"${video.countryId}"
       });
    
    </script>
</body>
</html>
