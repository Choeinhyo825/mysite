<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>	
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${pi.listCount-vo.rnum+1 }</td>
							<td style="text-align: left; padding-left: ${30*vo.depth}px"><c:if test="${vo.depth >0 }"><img src="${pageContext.servletContext.contextPath }/assets/images/reply.png"></c:if><a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a></td>
							<td>${vo.name}</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:if test="${loginUser.no eq vo.userNo }">
									<a href="${pageContext.servletContext.contextPath }/board?a=deleteForm&no=${vo.no}" class="del">
										<img alt="" src="${pageContext.servletContext.contextPath }/assets/images/recycle.png">
									</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
				<%-- <c:if test="${pi.maxPage-5 >= pi.currentPage }">
				</c:if> --%>
						<%-- <c:set var="a" value ="${pi.currentPage/5 }"></c:set> --%>
					<ul>
						<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=1">◀</a></li>
						
						<c:forEach varStatus="vs" begin="1" end="${pi.maxPage }" step="1">
						
							<c:choose>
								<c:when test="${vs.count eq pi.currentPage}">
									<li class="selected">${vs.count }</li>
								</c:when>
								<c:otherwise>	
									<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=${vs.count }">${vs.count }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=${pi.maxPage}">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				<c:if test="${!empty loginUser }">
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board?a=writeForm" id="new-book">글쓰기</a>
					</div>	
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>