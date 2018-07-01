function login() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	$.post("/user/login", {
		username : username,
		password : password
	}, function(json) {
         if(json.success){
        	 url=window.location.href;
        	 if(url.indexOf("backurl=")>-1){
        		 var backurl=url.split("backurl=")[1];
        		  backurl=decodeURIComponent(backurl)
        		 window.location.href=backurl;
        	 }else{
        		 window.location.href="/account/main/";
        	 }
        	 
         }else{
        	 alert(json.msg);
         }
	});
}