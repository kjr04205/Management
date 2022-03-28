<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header.jsp" %>

<script>
	$('.sub_header li a').removeClass("on");
	$('.employee_item03 ').addClass("on");
</script>
<style>
	#team_top{
		overflow: hidden;
	}
	.btn-1{
		width:auto;
		background: #2ecc71;
		border: 1px solid;
		border-radius: 5px;
		color: white;
		padding:5px;
		margin:5px;
		
		text-align: center;
		font-size: 1rem;
	}
	#team_top > h2{
		float:left;
	}
	#teamInsert{
		float:right;
	}
	
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
		<h2 class="container_title">부서 관리</h2>
		<button type="button" class="btn-1" id="teamInsert">부서 등록</button>
	</div>
	
	<div>
		<table class="team_table">
			<thead>
				<tr>
					<th class="no">No.</th>
					<th>부서명</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="team" items="${teamList}">
				<tr>
					<td class="no">${team.tno}</td>
					<td><a href="#">${team.name }</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:if test="${ph.showPrev}">
				<a href="<c:url value='/teamManagement?page=${ph.beginPage-1}&pageSize=${ph.pageSize }'/>"><img src="resources/img/prev.png" /></a>
			</c:if>
			<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				<a href="<c:url value='/teamManagement?page=${i}&pageSize=${ph.pageSize }'/>">${i}</a>
			</c:forEach>
			<c:if test="${ph.showNext}">
				<a href="<c:url value='/teamManagement?page=${ph.endPage+1}&pageSize=${ph.pageSize }'/>"><img src="resources/img/next.png" /></a>
			</c:if>
		</div>
	</div>
</div>