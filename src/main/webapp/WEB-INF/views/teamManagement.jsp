<%@ page contentType="text/html;charset=utf-8"%>
<%@include file ="sub_header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/searchBar.css">
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
	#team_top{
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
	
	.team_register{
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
	<div id="team_top">
		<h2 class="container_title">부서 관리</h2>
		<button type="button" class="btn-1" id="teamInsert">부서 등록</button>
	</div>
	
	<div class="team_register register-page form">
		<form action="<c:url value='/teamManagement/save'/>" method="post" class="register-form" onsubmit="return formCheck(this)">
			<h2>등록할 부서의 이름을 입력하세요.</h2><br><br>
			<span class="form_close">X</span>
	      	<label for="id">부서 이름</label>
		    <input class="input-field" type="text" name="name" >
	        <button>부서 등록</button>
      	</form>
	</div>
	
	<div>
		<table class="team_table">
			<thead>
				<tr>
					<th class="no" width="20%">No.</th>
					<th width="80%">부서명</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="team" items="${teamList}">
				<tr>
					<td class="no">${team.tno}</td>
					<td><a href="<c:url value='/teamManagement/member?tno=${team.tno}&name=${team.name}'/>">${team.name }</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:if test="${ph.showPrev}">
				<a href="<c:url value='/teamManagement${ph.sc.getQueryString(ph.beginPage-1)}'/>"><img src="resources/img/prev.png" /></a>
			</c:if>
			<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				<a href="<c:url value='/teamManagement${ph.sc.getQueryString(i)}'/>">${i}</a>
			</c:forEach>
			<c:if test="${ph.showNext}">
				<a href="<c:url value='/teamManagement${ph.sc.getQueryString(ph.endPage+1)}'/>"><img src="resources/img/next.png" /></a>
			</c:if>
		</div>
		<div class="search-container">
            <form action="<c:url value="/teamManagement"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option value="T" ${ph.sc.option=='T' || ph.sc.option=='' ? "selected" : ""}>부서명</option>
                </select>
                <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="image" class="search-button" src="${pageContext.request.contextPath}/resources/img/search.png" alt="검색">
            </form>
        </div>
	</div>
</div>