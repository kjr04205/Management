<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %>
<%@include file ="sub_header2.jsp" %>
<style>

</style>
<script>
	$('.sub_header li a').removeClass("on");
	$('.inventory_item05 ').addClass("on");
	
	let msg = "${msg}";
	if(msg=="ADD_OK") alert("등록되었습니다.");
	if(msg=="ADD_ERR") alert("등록에 실패하였습니다. 관리자에게 문의해주세요.");
	if(msg=="ADD_CNT_ERR") alert("등록에 실패하였습니다. 수량을 확인 해 주세요.");
	if(msg=="DEL_OK") alert("삭제되었습니다.");
	if(msg=="DEL_ERR") alert("등록에 실패하였습니다. 관리자에게 문의해주세요.");
	
	$(document).ready(function(){
		var employee={};
		var inventory={};
		
		searchBoxSetting($("#search-type option:selected").val());
		
		$("#search-type").on('change',function(){
			var option = $("#search-type option:selected").val();
			searchBoxSetting(option);
		})
		
		$('#insert').click(function(){
			$.ajax({
				type:"GET",
				url:"/ManagementProgram/inventoryGoods/settingInsertData",
				dataType:"json",
				success:function(result){
					inventory = result.inventory;
					employee= result.employee;
					setMember(employee);
					setInventory(inventory);
				},
				error:function(err){
					alert('에러');
				}
			});
			
			$('.register').css("display","block");
		});	
		
		$('.form_close').click(function(){
			$('.register').css("display","none");
		});
		
		$("#employeeBox").on('change',function(){
			var idx = $("#employeeBox option").index($("#employeeBox option:selected"));
			$('input[name=ggroup]').attr('value',employee[idx-1].team);
		});
	});
	
	
	function setMember(employee){
		for(var i=0;i<employee.length;i++){
			$("#employeeBox").append('<option value="'+employee[i].eno+'">'+employee[i].name+'</option>');
		}
		$('input[name=ggroup]').attr('value',employee[0].team);
	}
	
	function setInventory(inventory){
		for(var i=0;i<inventory.length;i++){
			$("#inventoryBox").append('<option value="'+inventory[i].ino+'">'+inventory[i].name+'</option>');
		}
	}
	
	function formCheck(frm){
		if(frm.gcount.value.length==0){
			alert("수량을 입력해주세요.");
			return false;
		}
		if(frm.gcount.value <= 0){
			alert("수량은 0보다 커야합니다.");
			return false;
		}
	}
	
	function searchBoxSetting(option){
		if(option=='Date'){
			$("#search-date").attr('disabled',false);
			$("#search-date").attr("style","display:block");
			$("#search-input").attr('disabled',true);
			$("#search-input").attr('style','display:none');
		}else{
			$("#search-date").attr('disabled',true);
			$("#search-date").attr("style","display:none");
			$("#search-input").attr('disabled',false);
			$("#search-input").attr('style','display:block');
		}
	}
</script>
<style>
	#position_top{
		overflow: hidden;
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
	#position_top > h2{
		float:left;
	}
	#insert{
		float:right;
	}
	
	.position_table{
		border-collapse: collapse;
		line-height:1.5;
		text-align: center;
	}
	.position_table > thead th{
		padding: 10px;
		font-weight: bold;
		vertical-align: top;
		color: #4CAF50;
		border-bottom: 3px solid #4CAF50;
	}
	.position_table > tbody td{
		width: 350px;
		padding: 10px;
		vertical-align: top;
		border-bottom: 1px solid #E6E6FA;
	}

	.no{
		width : 20%;
	}
	
	.register{
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
	.register-form > h2{
		font-size:17px;
		font-weight:bold;
	}
	
</style>
<body>
<div class="container">
	<div id="position_top">
		<h2 class="container_title">입고 관리</h2>
		<button type="button" class="btn-1" id="insert">입고 등록</button>
	</div>
	
	<div class="register register-page form">
	    <form action="<c:url value='/inventoryGoods/save'/>" method="post" class="register-form" onsubmit="return formCheck(this)">
	    	<h2>입고 자재 등록</h2><br><br>
			<span class="form_close">X</span>
	    	<label for="id">입고 품목</label>
	    	<select name="gname" id="inventoryBox">
	   			<option value="" selected disabled hidden>입고 품목을 선택하세요.</option>
	   		</select>
	   		
	   		<label for="id">반납자</label>
	    	<select name="gmember" id="employeeBox">
	   			<option value="" selected disabled hidden>반납자를 선택하세요.</option>
	   		</select>
	   		
	   		<label for="id">반납자 부서</label>
	   		<input class="input-field" type="text" name="ggroup" readonly>
	   		<label for="id">반납 수량</label>
	   		<input class="input-field" type="number" name="gcount">
	        <button>자재 입고등록</button>
	    </form>
  	</div>
  	
  	<div>
		<table class="position_table">
			<thead>
				<tr>
					<th class="no">No.</th>
					<th>입고 품목명</th>
					<th>반납자</th>
					<th>반납자 부서</th>
					<th>반납 수량</th>
					<th>날짜</th>
					<th width="5%">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
			<c:set var ="no" value="${ph.getTotalCnt()-((ph.sc.getPage()-1) * ph.sc.getPageSize())}"/>
			<c:forEach var="goods" items="${goodsList}">
				<tr>
					<td class="no">${no}</td>
					<td>${goods.gname}</td>
					<td>${goods.gmember}</td>
					<td>${goods.ggroup}</td>
					<td>${goods.gcount}</td>
					<td>${goods.gdate}</td>
					<td><a href="<c:url value='/inventoryGoods/remove?rgno=${goods.rgno}&gcount=${goods.gcount}'/>"><img style="max-width:25px; max-height:25px; width:100%;" src="resources/img/delete.png" /></a></td>
				</tr>
				<c:set var="no" value="${no-1}"/>
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:if test="${ph.showPrev}">
				<a href="<c:url value='/inventoryGoods${ph.sc.getQueryString(ph.beginPage-1)}'/>"><img src="resources/img/prev.png" /></a>
			</c:if>
			<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				<a href="<c:url value='/inventoryGoods${ph.sc.getQueryString(i)}'/>">${i}</a>
			</c:forEach>
			<c:if test="${ph.showNext}">
				<a href="<c:url value='/inventoryGoods${ph.sc.getQueryString(ph.endPage+1)}'/>"><img src="resources/img/next.png" /></a>
			</c:if>
		</div>
		<div class="search-container">
            <form action="<c:url value="/inventoryGoods"/>" class="search-form" method="get">
                <select id="search-type" class="search-option" name="option">
                    <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>전체</option>
                    <option value="IName" ${ph.sc.option=='IName' ? "selected" : ""}>품목명</option>
                    <option value="EName" ${ph.sc.option=='EName' ? "selected" : ""}>반납자</option>
                    <option value="EGroup" ${ph.sc.option=='EGroup' ? "selected" : ""}>부서</option>
                    <option value="Date" ${ph.sc.option=='Date' ? "selected" : ""}>날짜</option>
                </select>
                <input type="text" id="search-input" name="keyword" class="search-input" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="date" disabled style="display:none" id="search-date" name="keyword" class="search-input" value="${ph.sc.keyword}" />
                <input type="image" class="search-button" src="${pageContext.request.contextPath}/resources/img/search.png" alt="검색">
            </form>
        </div>
	</div>
</div>
  
</body>
</html>