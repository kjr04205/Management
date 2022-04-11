<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/searchBar.css">
<script>
	$('.sub_header li a').removeClass("on");
	$('.employee_item04 ').addClass("on");
	
	$(document).ready(function(){
		$('#insert').click(function(){
			$('.register').css("display","block");
		});	
		$('.form_close').click(function(){
			$('.register').css("display","none");
		});
	});
	
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
<div class="container">
	<div id="position_top">
		<h2 class="container_title">직급 관리</h2>
		<button type="button" class="btn-1" id="insert">직급 등록</button>
	</div>
	
	<div class="register register-page form">
		<form action="<c:url value='/positionManagement/save'/>" method="post" class="register-form" onsubmit="return formCheck(this)">
			<h2>등록할 직급의 이름을 입력하세요.</h2><br><br>
			<span class="form_close">X</span>
	      	<label for="id">직급 이름</label>
		    <input class="input-field" type="text" name="name" >
	        <button>직급 등록</button>
      	</form>
	</div>
	
	<div>
		<table class="position_table">
			<thead>
				<tr>
					<th class="no" width="20%">No.</th>
					<th width="80%">직급명</th>
				</tr>
			</thead>
			<tbody>
			<c:set var ="no" value="${ph.getTotalCnt()-((ph.sc.getPage()-1) * ph.sc.getPageSize())}"/>
			<c:forEach var="position" items="${positionList}">
				<tr>
					<td class="no">${no}</td>
					<td><a href="<c:url value='/positionManagement/member?pno=${position.pno}&name=${position.name}'/>">${position.name }</a></td>
				</tr>
				<c:set var="no" value="${no-1}"/>
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:if test="${ph.showPrev}">
				<a href="<c:url value='/positionManagement${ph.sc.getQueryString(ph.beginPage-1)}'/>"><img src="resources/img/prev.png" /></a>
			</c:if>
			<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				<a href="<c:url value='/positionManagement${ph.sc.getQueryString(i)}'/>">${i}</a>
			</c:forEach>
			<c:if test="${ph.showNext}">
				<a href="<c:url value='/positionManagement${ph.sc.getQueryString(ph.endPage+1)}'/>"><img src="resources/img/next.png" /></a>
			</c:if>
		</div>
		<div class="search-container">
            <form action="<c:url value="/positionManagement"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option value="R" ${ph.sc.option=='R' || ph.sc.option=='' ? "selected" : ""}>직급명</option>
                </select>
                <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="image" class="search-button" src="${pageContext.request.contextPath}/resources/img/search.png" alt="검색">
            </form>
        </div>
	</div>
</div>