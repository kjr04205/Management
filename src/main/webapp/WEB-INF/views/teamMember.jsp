<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/searchBar.css">
<script>
	$('.sub_header li a').removeClass("on");
	$('.employee_item03 ').addClass("on");
	
	$(document).ready(function(){
		$('#teamDeleteBtn').click(function(){
			$('.team_delete').css("display","block");
		});	
		$('.form_close').click(function(){
			$('.team_delete').css("display","none");
		});
	});
	
	let msg = "${msg}";
	if(msg=="DEL_ERR"){
		alert("부서 삭제에 실패하였습니다. 관리자에게 문의해주세요.");
		$('.team_delete').css("display","none");
	}
	
</script>
<style>
	#team_top{
		overflow: hidden;
	}
	#team_top > h2{
		float:left;
	}
	#teamDeleteBtn{
		float:right;
	}
	
	.btn-1{
		font-family: "Roboto", sans-serif;
		text-transform: uppercase;
		outline: 0;
		background: #4CAF50;
		width: 10%;
		border: 0;
		padding: 10px;
		color: #FFFFFF;
		font-size: 14px;
		-webkit-transition: all 0.3 ease;
		transition: all 0.3 ease;
		cursor: pointer;
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
	
	.team_delete{
		position: absolute;
		left:40%;
		display:none;
	}
	.form_close{
		position:absolute;
		top:10px;
		right:10px;
		font-size:20px;
		font-weight:bold;
		cursor:pointer;
	}
	.delete-form > h2{
		font-size:17px;
		font-weight:bold;
	}
	
</style>
<div class="container">

	<div id="team_top">
		<h2 class="container_title">${team.name}</h2>
		<button type="button" class="btn-1" id="teamDeleteBtn">부서 삭제</button>
	</div>
	
	<div class="team_delete delete-page form">
		<form action="<c:url value='/teamManagement/delete'/>" method="post" class="delete-form" onsubmit="return formCheck(${ph.getTotalCnt()})">
			<h2>정말 삭제하시겠습니까?</h2><br><br>
			<span class="form_close">X</span>
			<input type="hidden" name="tno" value="${team.tno}"/>
			<input type="hidden" name="name" value="${team.name}"/>
	        <button>부서 삭제</button>
      	</form>
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
					<td><a href="<c:url value='/employee/update?eno=${employee.eno}'/>">${employee.name }</a></td>
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
		<div class="search-container">
            <form action="<c:url value="/teamManagement/member"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                	<option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>전체검색</option>
                    <option value="N" ${ph.sc.option=='N' ? "selected" : ""}>이름</option>
                    <option value="P" ${ph.sc.option=='P' ? "selected" : ""}>전화번호</option>
                    <option value="R" ${ph.sc.option=='R' ? "selected" : ""}>직책</option>
                </select>
                <input type="text" name="keyword2" class="search-input" type="text" value="${ph.sc.keyword2}" placeholder="검색어를 입력해주세요">
                <input type="image" class="search-button" src="${pageContext.request.contextPath}/resources/img/search.png" alt="검색">
                <input type="hidden" name="tno" value="${team.tno}"/>
                <input type="hidden" name="name" value="${team.name}"/>
            </form>
        </div>
	</div>
</div>

<script>
	function formCheck(count){
		if(count!=0){
			alert("부서를 삭제하려면 팀원이 한명도 존재하지 않아야 합니다.");
			return false;
		}
		else return true;
	}
</script>