<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<c:set var="loginMenu" value="${sessionScope.id==null ? '로그인' : '로그아웃' }"/>
<c:set var="loginLink" value="${sessionScope.id==null ? '/login' : '/logout' }"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MP(ManagementProgram)</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sub_main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>
<style>
  @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap');

  /* point color : #4CAF50 */
  body {
    font-family: "Nanum Gothic", sans-serif;
  }
  .header{
  	background-color:#4CAF50 !important;
  	padding:15px 20px;
  }
  .logo{
  	font-size:25px;
  	font-weight:bold;
  }
  .container{
  	max-width:1200px;
  	width:100%;
  }
  .container > div{
  	margin:50px 50px 50px 0px;
  }
</style>
<body>
  <nav class="navbar navbar-expand-md header navbar-dark bg-dark">
 	<a class="navbar-brand logo" href="<c:url value="/"/>">MP(Management Program)</a>
 	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
   	  <span class="navbar-toggler-icon"></span>
	</button>
    <div class="collapse navbar-collapse header_text_wrap" id="collapsibleNavbar">
      <!--  <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="<c:url value="${loginLink}"/>">${loginMenu}</a>
        </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value="/register"/>">회원가입</a>
	    </li>
        <li class="nav-item">
          <a class="nav-link" href="#">문의접수</a>
        </li>    
      </ul> -->
    </div>  
  </nav>
  
  <div class="sub_header">
	<div>
		<ul>
			<li><a href="<c:url value="/employee"/>" class="employee_item01">직원 관리</a></li>
			<li><a href="<c:url value="/employeeRegister"/>" class="employee_item02">직원 등록</a></li>
			<li><a href="<c:url value="/teamManagement"/>" class="employee_item03">부서 관리</a></li>
			<li><a href="#" class="employee_item04">휴가 관리</a></li>
		</ul>
	</div>	
</div>
