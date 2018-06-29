function add_cart1(){
	var r=confirm("强烈建议您注册会员后进行购买 以便获取更好的体验 是否继续非会员购买？");
	if(r){
	    alert("已确认");	
	}
}


function add_cart(id,num){
 alert("id:"+id+"num:"+num)
  $.get("/cart/add",{productId:id,num:num},function(json){
	  if(json.success){
		   alert(json.data);
	  }else{
		  alert(json.msg);
	  }
  });
}