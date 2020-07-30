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
	
	//아이디중복확인 페이지이동
	$("#idcheckBtn").click(function(){
		window.open("idCheck.me","","width=500 height=300")
	});//idcheckBtn
	//id중복확인
	$("#useBtn").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		$.ajax({
			type : "post",
			url : "idCheck.me",
			data : {"userid":$("#userid").val()},
			success : function(val){
				if(val.trim()=="yes"){
					alert("아이디 사용가능")
					$(opener.document).find("#userid").val($("#userid").val());
					$(opener.document).find("#uid").val($("#userid").val());
				}else if(val.trim()=="no"){
					alert("이미 존재하는 아이디 입니다")
					$("#userid").val("")
				}
			},
			error : function(e){
				alert("error : "+e) 
			}
		
		});//ajax
	});//useBtn

});//document

function del(userid) {
	if(confirm("정말 삭제할까요?")){
		$.getJSON("userDelete.me?userid="+userid,function(data){
			var htmlStr="";
			$.each(data.root, function(key,val){
				htmlStr+="<tr>";
				htmlStr+="<td>"+val.name+"</td>";
				htmlStr+="<td>"+val.userid+"</td>";
				htmlStr+="<td>"+val.phone+"</td>";
				htmlStr+="<td>"+val.email+"</td>";
				htmlStr+="<td>"+val.mode+"</td>";
				if(val.mode=='일반회원'){
					htmlStr+="<td onclick=del('"+val.userid+"'>삭제</td>";
				}else{
					htmlStr+="<td>Admin</td>";
				}
				htmlStr+="</tr>";
			})
			$("table tbody").html(htmlStr);
			$("#cntspan").text(data.rootCount.count);
		})
}
}	
