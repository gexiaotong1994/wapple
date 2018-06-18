function login() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	$.post("/user/login", {
		username : username,
		password : password
	}, function(json) {
         if(json.success){
        	 window.location.href="/myaccount/main/";
         }else{
        	 alert(json.msg);
         }
	});
}