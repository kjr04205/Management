<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %>
<%@include file ="header.jsp" %>

<h1>아이디=${user.id }</h1>
<h1>비밀번호=${user.pwd }</h1>
<h1>이름=${user.name }</h1>
<h1>이메일=${user.email }</h1>
<h1>생일=${user.birth }</h1>