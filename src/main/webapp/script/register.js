function reg_submit(){ 
	var username= document.getElementById("username").value;
	var inputList=document.getElementsByClassName("register")[0].getElementsByTagName("input");
    var inx=0;
	for(var i=0;i<inputList.length;i++){
		if(inputList[i].type!='submit'){
	       if(inputList[i].value.trim()==""){
	    	   inx++;
	       }
	    }
	}
	
	if(inx==0){
		return true;
	}else{
		document.getElementById("submit-msg").innerHTML=inx+"个输入框未填写";
	}
	return false;
}

$("#username").blur(function(){
	var obj=$(this);
	var val=obj.val();
    $.get("/user/validate",{value:val,type:"username"},function(json){
    	 if(json.success){
    		 msg(json.data,true);
    	  }else{
    		  obj.val("");
    		  msg(json.msg,false);
    	  }
    });
});
$("#phone").blur(function(){
	var obj=$(this);
	var val=obj.val();
    $.get("/user/validate",{value:val,type:"phone"},function(json){
    	 if(json.success){
    		 msg(json.data,true);
    	  }else{
    		  obj.val("");
    		  msg(json.msg,false);
    	  }
    });
});
$("#email").blur(function(){
	var obj=$(this);
	var val=obj.val();
    $.get("/user/validate",{value:val,type:"email"},function(json){
    	 if(json.success){
    		 msg(json.data,true);
    	  }else{
    		  obj.val("");
    		  msg(json.msg,false);
    	  }
    });
});

function msg(val,suc){
$("#msg").css("color",function(){
    	if(suc){
    		return "green";
    	}else{
    		return "red";
    	}
    }).html(val);
}



