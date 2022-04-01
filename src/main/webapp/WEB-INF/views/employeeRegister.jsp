<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %>
<%@include file ="sub_header.jsp" %>
<style>
.register-page {
	width: 360px;
	margin: auto;
}
.register-form label{
	float:left;
	margin-bottom:12px;
}
.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 100px auto;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}

.form input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}
.form select{
	font-family: "Roboto", sans-serif;
	outline: 0;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
	background: #f2f2f2;
}

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 89%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

.form button:hover, .form button:active, .form button:focus {
	background: #43A047;
}

</style>
<script>
	$('.sub_header li a').removeClass("on");
	$('.employee_item02').addClass("on");
	
	let msg = "${msg}";
	if(msg=="ADD_OK") alert("등록되었습니다.");
	if(msg=="ADD_ERR") alert("등록에 실패하였습니다. 관리자에게 문의해주세요.");
</script>
<body>
  
  <div class="register-page form">
      <form action="<c:url value='/employeeRegister/save'/>" method="post" class="register-form" onsubmit="return formCheck(this)">
      	<label for="id">이름</label>
	    <input class="input-field" type="text" name="name" >
	    <label for="id">전화번호</label>
	    <input class="input-field" type="text" name="number" placeholder="010********">
	    <label for="id">직책</label>
	    <%-- <input class="input-field" type="text" name="position" >--%>
	    <select name="position">
	    	<c:forEach var="item" items="${position}">
	    		<option value=${item.pno}>${item.name}</option>
	    	</c:forEach>
	    </select>
	    <label for="id">부서</label>
	    <%-- <input class="input-field" type="text" name="team" > --%>
	    <select name="team">
	    	<c:forEach var="item" items="${team}">
	    		<option value=${item.tno}>${item.name}</option>
	    	</c:forEach>
	    	
	    </select>
	    <label for="id">입사날짜</label>
	    <input class="input-field" type="text" name="startdate" placeholder="2020-04-20">
        <button>직원등록</button>
      </form>
      
  </div>
</body>
</html>