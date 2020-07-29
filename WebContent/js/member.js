$(document).ready(function() {
	//userid
	$("#send").click(function() {
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		if($("#name").val()==""){
			alert("이름를 입력하세요");
			$("#name").focus();
			return false;
		}
		if($("#pwd").val()==""){
			alert("비밀번호를 입력하세요");
			$("#pwd").focus();
			return false;
		}
		if($("#email").val()==""){
			alert("이메일을 입력하세요");
			$("#email").focus();
			return false;
		}
		if($("#phone").val().match(exp)){
			alert("전화번호 입력 양식이 아닙니다");
			$("#phone").focus();
			return false;
		}
		
	})//send

});//document