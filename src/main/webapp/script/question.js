var username;
function get_question(){
    var name=$("#username").val();
    $.get("/user/get_question_by_username",{username:name},function(json){
    	if(json.success){
    		show_question(json.data);
    		username=name;
    	}else{
    		alert(json.msg);
    	}
    });
}

function show_question(question){
	var node= $("#forgot-div");
	
	var html='<div><span>密保问题:'+question+'</span> <input type="text" name="answer" id="answer"></div>';

	html+='<input type="submit" value="确定" onclick="check_answer();" >';
	
	node.html(html);
}

function check_answer(){
	 var answer= $("#answer").val();
	 alert(answer);
}