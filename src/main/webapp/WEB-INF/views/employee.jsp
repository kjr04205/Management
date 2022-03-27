<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header.jsp" %>

<script>
	$('.sub_header li a').removeClass("on");
	$('.employee_item01 ').addClass("on");
</script>
<div class="container">
	<div>
		<h2 class="container_title">직원 관리</h2>
	</div>
	
	<div>
		<table class="employee_table">
			<thead>
				<tr>
					<th>No.</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>직책</th>
					<th>부서</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="employee" items="${list}">
				<tr>
					<td>${employee.eno}</td>
					<td>${employee.name }</td>
					<td>${employee.number }</td>
					<td>${employee.position }</td>
					<td>${employee.team }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:if test="${ph.showPrev}">
				<a href="<c:url value='/employee?page=${ph.beginPage-1}&pageSize=${ph.pageSize }'/>"><img src="resources/img/prev.png" /></a>
			</c:if>
			<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				<a href="<c:url value='/employee?page=${i}&pageSize=${ph.pageSize }'/>">${i}</a>
			</c:forEach>
			<c:if test="${ph.showNext}">
				<a href="<c:url value='/employee?page=${ph.endPage+1}&pageSize=${ph.pageSize }'/>"><img src="resources/img/next.png" /></a>
			</c:if>
		</div>
	</div>
</div>