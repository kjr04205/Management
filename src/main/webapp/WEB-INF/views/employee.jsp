<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header.jsp" %>

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
	</div>
</div>