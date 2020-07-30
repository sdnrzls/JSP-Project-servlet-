<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp" %>
   
    <div class="container">
    <br>
 	<h3>회원리스트(<span id="cntspan">${count }</span>)</h3><br>
 	<table class="table table-dark table-hover">
    <thead>
      <tr>
        <th>Name</th>
        <th>Userid</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Admin</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${members}" var="mem"> <!-- items(저장소):members, var=mem(변수명)-->
    	<tr>
    		<td>${mem.name}</td>
    		<td>${mem.userid}</td>
    		<td>${mem.phone}</td>
    		<td>${mem.mail}</td>
    		<td>${mode}</td>	
    		<c:if test="${mem.admin==0 }">
    		<td>일반회원</td>
    		<td onclick="del('${mem.userid}')">삭제</td>
    		 </c:if>
    		 <c:if test="${mem.admin==1 }">	
    		 <td>관리자</td>
    		 <td>Admin</td>
    		 </c:if>
    	</tr>
    </c:forEach>
    </tbody>
  </table>
 	</div>
 <%@include file="../include/footer.jsp" %>