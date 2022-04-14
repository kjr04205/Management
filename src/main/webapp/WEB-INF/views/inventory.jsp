<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header2.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sort.css">

<script>
	$('.sub_header li a').removeClass("on");
	$('.inventory_item01 ').addClass("on");
	
	let msg = "${msg}";
	if(msg=="REMOVE_OK") alert("삭제되었습니다.");
	if(msg=="REMOVE_ERR") alert("삭제 할 수 없습니다.");
	if(msg=="MOD_OK") alert("수정이 완료되었습니다.");
	if(msg=="MOD_ERR") alert("수정 할 수 없습니다.");
	
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
		<h2 class="container_title" style="float:left">자재 목록</h2>
		<div style="overflow:hidden; float:right;">
			<form action="<c:url value="/inventory"/>" method="get" id="sortFrm">
				<input type="hidden" id="sortType" name="sortType" value="${ph.sc.getSortType()}"/>
				<input type="hidden" name="keyword" value="${ph.sc.keyword}"/>
				<input type="hidden" name="option" value="${ph.sc.option}"/>
				<input type="image" class="sort-button" src="${pageContext.request.contextPath}/resources/img/sortIcon.png" alt="정렬" >
				<select style="float:right" id="sort" class="sort" name="sort">
					<option value="" ${ph.sc.sort=='' ? "selected" : ""}>최신순</option>
					<option value="N" ${ph.sc.sort=='N' ? "selected" : ""}>이름</option>
					<option value="C" ${ph.sc.sort=='C' ? "selected" : ""}>코드</option>
					<option value="CNT" ${ph.sc.sort=='CNT' ? "selected" : ""}>수량</option>
					<option value="L" ${ph.sc.sort=='L' ? "selected" : ""}>위치</option>
					<option value="G" ${ph.sc.sort=='G' ? "selected" : ""}>분류</option>
				</select>
			</form>
		</div>
		
	</div>
	
	<div>
		<a href="<c:url value='/inventory/excel'/>" class="excel-download"><img src="resources/img/excel.png" /></a>
		<table class="inventory_table">
			<thead>
				<tr>
					<th width="8%">No.</th>
					<th width="22%">제품 이름</th>
					<th width="15%">제품 코드</th>
					<th width="10%">재고 수량</th>
					<th width="20%">재고 위치</th>
					<th width="20%">분류</th>
					<th width="5%">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="inventory" items="${list}">
				<tr>
					<td>${inventory.ino}</td>
					<td><a href="<c:url value='/inventory/update?ino=${inventory.ino}'/>">${inventory.name}</a></td>
					<td>${inventory.code}</td>
					<td>${inventory.count}</td>
					<td>${inventory.lno}</td>
					<td>${inventory.igno}</td>
					<td><a href="<c:url value='/inventory/delete?ino=${inventory.ino}'/>"><img style="max-width:25px; max-height:25px; width:100%;" src="resources/img/delete.png" /></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div class="paging">
			<c:if test="${ph.showPrev}">
				<a href="<c:url value='/inventory${ph.sc.getQueryString(ph.beginPage-1)}'/>"><img src="resources/img/prev.png" /></a>
			</c:if>
			<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				<a href="<c:url value='/inventory${ph.sc.getQueryString(i)}'/>">${i}</a>
			</c:forEach>
			<c:if test="${ph.showNext}">
				<a href="<c:url value='/inventory${ph.sc.getQueryString(ph.endPage+1)}'/>"><img src="resources/img/next.png" /></a>
			</c:if>
		</div>
		<div class="search-container">
            <form action="<c:url value="/inventory"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                	<option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>전체검색</option>
                    <option value="N" ${ph.sc.option=='N' ? "selected" : ""}>이름</option>
                    <option value="C" ${ph.sc.option=='C' ? "selected" : ""}>코드</option>
                    <option value="R" ${ph.sc.option=='R' ? "selected" : ""}>분류</option>
                </select>
                <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="image" class="search-button" src="${pageContext.request.contextPath}/resources/img/search.png" alt="검색">
            </form>
        </div>
		
	</div>
</div>