<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %>
<%@include file ="header.jsp" %>
<style>
.register-page {
	width: 360px;
	margin: auto;
}
.register-form label{
	float:left;
}
.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
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

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 100%;
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

.form .message {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 12px;
}

.form .message a {
	color: #4CAF50;
	text-decoration: none;
}

#msg {
	height: 15px;
	text-align: center;
	font-size: 14px;
	color: red;
	margin-bottom: 15px;
}
</style>
<body>
  <div class="register-page form">
      <form action="<c:url value='/register/save'/>" method="post" class="register-form" onsubmit="return formCheck(this)">
      	<div id="msg">
      		<c:if test="${not empty param.msg}">
      			<i class="fa fa-exclamation-circle">${URLDecoder.decode(param.msg)}</i>
      		</c:if>
      	</div>
      	<label for="id">아이디</label>
	    <input class="input-field" type="text" name="id" placeholder="아이디를 입력해주세요.">
	    <label for="id">비밀번호</label>
	    <input class="input-field" type="text" name="pwd" placeholder="패스워드를 입력해주세요.">
	    <label for="id">이름</label>
	    <input class="input-field" type="text" name="name" placeholder="홍길동">
	    <label for="id">이메일</label>
	    <input class="input-field" type="text" name="email" placeholder="example@naver.com">
	    <label for="id">생년월일</label> 
	    <input class="input-field" type="text" name="birth" placeholder="20201231">
        <button>회원가입하기</button>
      </form>
      <script>
      	function formCheck(frm){
      		var msg = '';
      		
      		if(frm.id.value.length<3){
      			setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
      			return false;
      		}
      		
      		if(frm.pwd.value.length<4){
      			setMessage('pw의 길이는 4이상이어야 합니다.', frm.pwd);
      			return false;
      		}
      		
      		if(frm.name.value.length<1){
      			setMessage('이름을 입력하지 않으셨습니다. 입력해주세요.', frm.name);
      			return false;
      		}
      		
      		if(frm.email.value.length<1){
      			setMessage('이메일을 입력하지 않으셨습니다. 입력해주세요.', frm.email);
      			return false;
      		}
      		
      		if(frm.birth.value.length < 5){
      			setMessage('8글자를 입력해주세요. ex)19971105', frm.birth);
      			return false;
      		}
      		
      		return true;
      	}
      	
      	function setMessage(msg, element){
      		document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'} </i>`;
      		
      		if(element){
      			element.select();
      		}
      	}
      </script>
  </div>
</body>
</html>