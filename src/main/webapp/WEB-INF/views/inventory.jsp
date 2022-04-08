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
		<table class="inventory_table">
			<thead>
				<tr>
					<th>No.</th>
					<th>제품 이름</th>
					<th>제품 코드</th>
					<th>재고 수량</th>
					<th>재고 위치</th>
					<th>분류</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>i5-6700</td>
					<td>COM20220330</td>
					<td>10</td>
					<td>4F B20</td>
					<td>CPU</td>
				</tr>
			</tbody>
		</table>
		
	</div>
</div>