<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp" %>
<style>
ul{
   list-style:none;
   }
ul li{
	padding-bottom: 10px;
}
</style>
<div class="container">

  <form action="update.me" method="post" id="frm">
    <div class="row">
	    <div class="col">
	      <label for="userid">id:</label>
	      <input type="text" class="form-control" id="userid" placeholder="Enter id" name="userid" value="${member.userid}" readonly="readonly">
	    </div>
      <div class="col align-self-end" >
          <button  type="button"  id="idcheck"  class="btn btn-primary">중복확인</button>
    </div>
    </div>
    
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="${member.name}">
    </div>

    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd" value="${member.pwd}">
    </div>
    
    <div class="form-group">
      <label for="mail">Email:</label>
      <input type="text" class="form-control" id="mail" placeholder="Enter Eamil" name="mail" value="${member.mail}">
    </div>
    <div class="form-group">
      <label for="phone">Phone:</label>
      <input type="text" class="form-control" id="phone" placeholder="Enter Phone" name="phone" value="${member.phone}">
    </div>
    <div class="form-check-inline">
  <label class="form-check-label">
    <input type="radio" class="form-check-input" name="admin" value="1">관리자
  </label>
</div>
<div class="form-check-inline">
  <label class="form-check-label">
    <input type="radio" class="form-check-input" name="admin"  value="0" checked>일반회원
  </label>
</div>
<br/>
 <button  id="send"  class="btn btn-primary">update</button>
  </form>
</div>
<script>
	if(${member.getAdmin()==0}){
		$("input:radio[value='0']").prop("checked", true);
	}else{
		$("input:radio[value='1']").prop("checked", true);d
	}
	</script>
 <%@include file="../include/footer.jsp" %>