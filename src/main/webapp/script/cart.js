$(function(){
	$("#jiesuan").click(function(){
	   var count=$("#countNum").html();
	   var icount=parseInt(count);
	   if(icount<=0){
		   alert("购物车是空的!");
		   return;
	   }else{
		   var myform = $("<form></form>");
		    myform.attr('method','post')  
		    myform.attr('action',"/order/choosePickUpType");
		    var myInput=$('<input name="totalPrice"/>');
		    myInput.attr("value",count);
		    myform.append(myInput);
		    myform.appendTo("body");
		    myform.submit(); 
		    return false;
	   }
	});
});

$.get("/cart/list", {}, function(json) {
	if (json.success) {
		show_list(json.data);
	} else {
		show_msg(json.msg);
	}
});

function show_list(cartVo) {
	    $("#countNum").html(cartVo.countNum);
	    $("#countPrice").html(cartVo.countPrice);
	    $("#zongji").html(cartVo.countPrice);
	var html='';
	$.each(cartVo.cartListVos, function(index, c) {
		html+='<div class="cart-header">';
		html+='<div class="close1" onclick="del('+c.id+');"></div>';
		html+='<div class="cart-sec simpleCart_shelfItem">';
		html+='<div class="cart-item cyc">';
		html+=' <img src="'+c.image+'" class="img-responsive" alt=""/></div>';
		html+='<div class="cart-item-info">';
		html+='<h3><a href="#">'+c.prdouctName+'</a><span>商品编号: '+c.prdouctId+'</span></h3>';
		html+='<ul class="qty">';
		html+='<li><p>单价 :'+c.price+'</p></li>';
		html+='<li><p>数量 : '+c.num+'<button onclick="change_cart_num('+(c.num+1)+','+c.id+')">+</button> <button onclick="change_cart_num('+(c.num-1)+','+c.id+')">-</button></p></li></ul>';
		html+='<div class="delivery">';
		html+='<p>共'+c.num+'件 价格:'+c.totalPrice+'</p>';
		html+='<div class="clearfix"></div>';
		html+='</div></div><div class="clearfix"></div></div></div>';	
	});
	$("#base-cart-div").html(html);

}

function show_msg(msg) {
	$("#base-cart-div").html('<B>'+msg+'</B>');
}

function change_cart_num(num,cartId){
	 if(num>5){
		 alert("同一件商品最多添加5件");
	 }else if(num<=0){
		 alert("购物车中至少为一件！");
	 }else{
		 $.get("/cart/change_num",{cartId:cartId,num:num},function(json){
			 show_list(json.data);
		 });
	 }
}

function del(cartId){
	$.get("/cart/del",{cartId:cartId},function(json){
		  
	 });
}



		
			
		
		
		
			
			
		
		
			 
			 
			 
      