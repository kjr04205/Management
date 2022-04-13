<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header2.jsp" %>

<script>
	$('.sub_header li a').removeClass("on");
	$('.inventory_item01 ').addClass("on");
	
	let msg = "${msg}";
	if(msg=="REMOVE_OK") alert("삭제되었습니다.");
</script>
<div class="container">
	<div>
		<h2 class="container_title">자재 목록</h2>
	</div>
	
	<div>
		<a href="<c:url value='/inventory/excel'/>" class="excel-download"><img src="resources/img/excel.png" /></a>
		<table class="inventory_table">
			<thead>
				<tr>
					<th width="8%">No.</th>
					<th width="22%">제품 이름</th>
					<th width="20%">제품 코드</th>
					<th width="10%">재고 수량</th>
					<th width="20%">재고 위치</th>
					<th width="20%">분류</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="inventory" items="${list}">
				<tr>
					<td>${inventory.ino}</td>
					<td>${inventory.name}</td>
					<td>${inventory.code}</td>
					<td>${inventory.count}</td>
					<td>${inventory.lno}</td>
					<td>${inventory.igno}</td>
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