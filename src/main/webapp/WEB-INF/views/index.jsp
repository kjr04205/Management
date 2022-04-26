<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="header.jsp" %>

<%-- 회원가입시 msg --%>
<script>
	let msg = "${msg}";
	if(msg=="ADD_OK") alert("회원등록되었습니다.");
	if(msg=="ADD_ERR") alert("회원등록에 실패하였습니다. 관리자에게 문의해주세요.");
	
</script>
	<div class="container">
   		<div class="box">
    		<div class="item">
    			<a href="<c:url value="/employee"/>"><img src="resources/img/main_01.jpg"></a>
    			<p>직원 관리</p>
    			<p>회사 내 직원정보를 관리할 수 있습니다.</p>
    		</div>
    	</div>
    	
    	<div class="box">
    		<div class="item">
    			<a href="<c:url value="/inventory"/>"><img src="resources/img/main_02.png"></a>
    			<p>자재 관리</p>
    			<p>사내 자재, 반입출 자재들을 관리할 수 있습니다.</p>
    		</div>
    	</div>
	</div>
</body>
</html>