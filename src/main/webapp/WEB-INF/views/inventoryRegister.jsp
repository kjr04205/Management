<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %>
<%@include file ="sub_header2.jsp" %>
<style>

</style>
<script>
	$('.sub_header li a').removeClass("on");
	$('.inventory_item02 ').addClass("on");
	
	let msg = "${msg}";
	if(msg=="ADD_OK") alert("등록되었습니다.");
	if(msg=="ADD_ERR") alert("등록에 실패하였습니다. 관리자에게 문의해주세요.");
</script>
<body>
  
  <div class="register-page form">
      <form action="<c:url value='/inventoryRegister/save'/>" method="post" class="register-form" onsubmit="return formCheck(this)">
      	<label for="id">제품 이름</label>
	    <input class="input-field" type="text" name="name" >
	    <label for="id">제품 코드</label>
	    <input class="input-field" type="text" name="code">
	    <label for="id">자재 수량</label>
	    <input class="input-field" type="number" name="count">
	    <label for="id">자재 위치</label>
	    <select name="location">
	    	<c:forEach var="location" items="${location}">
	    		<option value=${location.lno}>${location.name}</option>
	    	</c:forEach>
	    </select>
	     <label for="id">분류</label>
	    <select name="group">
	    	<c:forEach var="group" items="${group}">
	    		<option value=${group.igno}>${group.name}</option>
	    	</c:forEach>
	    </select>
	    
        <button>자재 등록</button>
        
      </form>
      
  </div>
</body>
</html>