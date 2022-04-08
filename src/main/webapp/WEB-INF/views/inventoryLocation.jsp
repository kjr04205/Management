<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %>
<%@include file ="sub_header2.jsp" %>
<style>

</style>
<script>
	$('.sub_header li a').removeClass("on");
	$('.inventory_item03 ').addClass("on");
	
	let msg = "${msg}";
	if(msg=="ADD_OK") alert("등록되었습니다.");
	if(msg=="ADD_ERR") alert("등록에 실패하였습니다. 관리자에게 문의해주세요.");
</script>
<body>
  
  <div class="register-page form">
      <form action="<c:url value='/inventoryLocation/save'/>" method="post" class="register-form" onsubmit="return formCheck(this)">
      	<label for="id">위치명</label>
	    <input class="input-field" type="text" name="name" >
        <button>위치 등록</button>
      </form>
      
  </div>
</body>
</html>