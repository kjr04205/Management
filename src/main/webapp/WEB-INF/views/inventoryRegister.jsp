<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %>
<%@include file ="sub_header2.jsp" %>
<c:set var="buttonType" value="${type=='Modify' ? '수정' : '자재 등록'}"/>
<c:set var="urlType" value="${type=='Modify' ? '/inventory/update' : '/inventoryRegister/save'}"/>
<script>

	$('.sub_header li a').removeClass("on");
	if(type!='Modify')	$('.inventory_item02 ').addClass("on");
	
	let msg = "${msg}";
	if(msg=="ADD_OK") alert("등록되었습니다.");
	if(msg=="ADD_ERR") alert("등록에 실패하였습니다. 관리자에게 문의해주세요.");
	
	if(msg=="MOD_ERR") alert("수정에 실패하였습니다. 관리자에게 문의해주세요.");
</script>
<body>
  
  <div class="register-page form">
      <form action="<c:url value='${urlType}'/>" method="post" class="register-form" onsubmit="return formCheck(this)">
      	<label for="id">제품 이름</label>
	    <input class="input-field" type="text" name="name" value="${inventory.name}">
	    <label for="id">제품 코드</label>
	    <input class="input-field" type="text" name="code" value="${inventory.code}">
	    <label for="id">자재 수량</label>
	    <input class="input-field" type="number" name="count" value="${inventory.count}">
	    <label for="id">자재 위치</label>
	    <select name="lno">
	    	<c:forEach var="location" items="${location}">
	    		<option value=${location.lno} ${inventory.lno == location.name ? "selected" : ""}>${location.name}</option>
	    	</c:forEach>
	    </select>
	     <label for="id">분류</label>
	    <select name="igno">
	    	<c:forEach var="group" items="${group}">
	    		<option value=${group.igno} ${inventory.igno == group.name ? "selected" : ""}>${group.name}</option>
	    	</c:forEach>
	    </select>
	    <c:if test="${type eq 'Modify'}">
	    	<input type="hidden" name="ino" value="${inventory.ino}"/>
	    </c:if>
        <button>${buttonType}</button>
        
      </form>
      
  </div>
</body>
</html>