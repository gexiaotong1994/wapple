$.get("/cart/list", {}, function(json) {
	if (json.success) {
		show_list(json.data.cartListVos);
	} else {
		show_msg(json.msg);
	}
});

function show_list(data) {
	var html='';
	$.each(data, function(index, c) {
		html+='<div class="cart-header">';
		html+='<div class="close1" onclick="del_1('+c.id+');"></div>';
		html+='<div class="cart-sec simpleCart_shelfItem">';
		html+='<div class="cart-item cyc">';
		html+=' <img src="'+c.image+'" class="img-responsive" alt=""/></div>';
		html+='<div class="cart-item-info">';
		html+='<h3><a href="#">'+c.prdouctName+'</a><span>商品编号: '+c.prdouctId+'</span></h3>';
		html+='<ul class="qty">';
		html+='<li><p>单价 :'+c.price+'</p></li>';
		html+='<li><p>数量 : '+c.num+'  <button>+</button> <button>-</button></p></li></ul>';
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

function del_1(){
	
}

		
			
		
		
		
			
			
		
		
			 
			 
			 
      