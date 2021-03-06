<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sort.css">
<script>
	$('.sub_header li a').removeClass("on");
	$('.employee_item01 ').addClass("on");
	
	let msg = "${msg}";
	if(msg=="REMOVE_OK") alert("삭제되었습니다.");
	if(msg=="UPDATE_OK") alert("수정이 완료되었습니다.");
	if(msg=="UPDATE_LOAD_ERR") alert("직원을 불러 올 수 없습니다.");

	$(document).ready(function(){
		$("#sort").on('change',function(){
			document.getElementById('sortFrm').submit();
		});
	
		$(".sort-button").on("click",function(){
			let type = document.getElementById('sortType').value;
			if(type=='DESC' || type=='')
				document.getElementById('sortType').value= 'ASC';
			else
				document.getElementById('sortType').value= 'DESC';
			
		});	
	});
	
</script>
<div class="container">
	<div style="overflow: hidden">
		<h2 class="container_title" style="float:left">직원 관리</h2>
		
		<div style="overflow:hidden; float:right;">
			<form action="<c:url value="/employee"/>" method="get" id="sortFrm">
				<input type="hidden" id="sortType" name="sortType" value="${ph.sc.getSortType()}"/>
				<input type="hidden" name="keyword" value="${ph.sc.keyword}"/>
				<input type="hidden" name="option" value="${ph.sc.option}"/>
				<input style="float:left" type="image" class="sort-button" src="${pageContext.request.contextPath}/resources/img/sortIcon.png" alt="정렬" >
				<select style="float:right" id="sort" class="sort" name="sort">
					<option value="" ${ph.sc.sort=='' ? "selected" : ""}>최신순</option>
					<option value="N" ${ph.sc.sort=='N' ? "selected" : ""}>이름</option>
					<option value="P" ${ph.sc.sort=='P' ? "selected" : ""}>전화번호</option>
					<option value="R" ${ph.sc.sort=='R' ? "selected" : ""}>직책</option>
					<option value="T" ${ph.sc.sort=='T' ? "selected" : ""}>부서</option>
					<option value="D" ${ph.sc.sort=='D' ? "selected" : ""}>입사날짜</option>
				</select>
			</form>
			
		</div>
		
	</div>
	
	<div>
		<a href="<c:url value='/excel'/>" class="excel-download"><img src="resources/img/excel.png" /></a>
		
		<table class="employee_table">
			<thead>
				<tr>
					<th width="10%">No.</th>
					<th width="15%">이름</th>
					<th width="20%">전화번호</th>
					<th width="10%">직책</th>
					<th width="20%">부서</th>
					<th width="20%">입사날짜</th>
					<th width="5%">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="employee" items="${list}">
				<tr>
					<td>${employee.eno}</td>
					<td><a href="<c:url value='/employee/update?eno=${employee.eno}'/>">${employee.name }</a></td>
					<td>${employee.number }</td>
					<td>${employee.position }</td>
					<td>${employee.team }</td>
					<td>${employee.startdate }</td>
					<td><a href="<c:url value='/employee/remove?eno=${employee.eno}'/>"><img style="max-width:25px; max-height:25px; width:100%;" src="resources/img/delete.png" /></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:if test="${ph.showPrev}">
				<a href="<c:url value='/employee${ph.sc.getQueryString(ph.beginPage-1)}'/>"><img src="resources/img/prev.png" /></a>
			</c:if>
			<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				<a href="<c:url value='/employee${ph.sc.getQueryString(i)}'/>">${i}</a>
			</c:forEach>
			<c:if test="${ph.showNext}">
				<a href="<c:url value='/employee${ph.sc.getQueryString(ph.endPage+1)}'/>"><img src="resources/img/next.png" /></a>
			</c:if>
		</div>
		<div class="search-container">
            <form action="<c:url value="/employee"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                	<option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>전체검색</option>
                    <option value="N" ${ph.sc.option=='N' ? "selected" : ""}>이름</option>
                    <option value="P" ${ph.sc.option=='P' ? "selected" : ""}>전화번호</option>
                    <option value="R" ${ph.sc.option=='R' ? "selected" : ""}>직책</option>
                    <option value="T" ${ph.sc.option=='T' ? "selected" : ""}>부서</option>
                </select>
                <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="image" class="search-button" src="${pageContext.request.contextPath}/resources/img/search.png" alt="검색">
            </form>
        </div>
	</div>
</div>