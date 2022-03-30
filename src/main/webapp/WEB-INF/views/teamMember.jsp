<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header.jsp" %>

<script>
	$('.sub_header li a').removeClass("on");
	$('.employee_item03 ').addClass("on");
	
	$(document).ready(function(){
		$('#teamInsert').click(function(){
			$('.team_register').css("display","block");
		});	
		$('.form_close').click(function(){
			$('.team_register').css("display","none");
		});
	});
	
</script>
<style>
	
	.team_table{
		border-collapse: collapse;
		line-height:1.5;
		text-align: center;
	}
	.team_table > thead th{
		padding: 10px;
		font-weight: bold;
		vertical-align: top;
		color: #4CAF50;
		border-bottom: 3px solid #4CAF50;
	}
	.team_table > tbody td{
		width: 350px;
		padding: 10px;
		vertical-align: top;
		border-bottom: 1px solid #E6E6FA;
	}

	.no{
		width : 20%;
	}
	
</style>
<div class="container">
	<div id="team_top">
		<h2 class="container_title">${team.name}</h2>
	</div>

	<div>
		<table class="team_table">
			<thead>
				<tr>
					<th class="no">No.</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>직책</th>
					
				</tr>
			</thead>
			<tbody>
			<c:forEach var="employee" items="${employeeList}">
				<tr>
					<td class="no">${employee.eno}</td>
					<td>${employee.name }</td>
					<td>${employee.number }</td>
					<td>${employee.position }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:if test="${ph.showPrev}">
				<a href="<c:url value='/teamManagement/member${ph.sc.getQueryString(ph.beginPage-1)}&tno=${team.tno}&name=${team.name}'/>"><img src="resources/img/prev.png" /></a>
			</c:if>
			<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				<a href="<c:url value='/teamManagement/member${ph.sc.getQueryString(i)}&tno=${team.tno}&name=${team.name}'/>">${i}</a>
			</c:forEach>
			<c:if test="${ph.showNext}">
				<a href="<c:url value='/teamManagement/member?${ph.sc.getQueryString(ph.endPage+1)}&tno=${team.tno}&name=${team.name}'/>"><img src="resources/img/next.png" /></a>
			</c:if>
		</div>
	</div>
</div>