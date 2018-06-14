function login() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	$.post("/user/login", {
		username : username,
		password : password
	}, function(json) {
         if(json.suc){
        	 alert("登录成功");
         }else{
        	 alert(json.msg);
         }
	});
}