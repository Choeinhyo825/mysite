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
				<form id="search_form" action="board?a=list" method="post">
					<input type="text" id="kwd" name="kwd" placeholder="제목으로 검색하세요" required>
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
							<td style="text-align: left; padding-left: ${30*vo.depth}px">
								<c:if test="${vo.depth > 0 }">
									<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
								</c:if>
								<c:choose>
									<c:when test='${vo.status eq "y"}'>
										<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a>
									</c:when>
									<c:otherwise>
										<a style="color: gray;">삭제된 게시글 입니다.</a>
									</c:otherwise>							
								</c:choose>
							</td>
							<td>${vo.name}</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:if test="${authUser.no eq vo.userNo }">
									<c:if test='${vo.status eq "y" }'>
										<a href="${pageContext.servletContext.contextPath }/board?a=deleteForm&no=${vo.no}" class="del">
											<img alt="" src="${pageContext.servletContext.contextPath }/assets/images/recycle.png">
										</a>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
						<c:set var="blockStartNum" value ="${pi.blockStartNum }"/>
						<c:set var="blockLastNum" value ="${pi.blockLastNum }"/>
					<ul>
						<c:choose>
							<c:when test="${pi.currentPage == 1}">
								<li>◀</li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${!empty kwd}">
										<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=${pi.currentPage -1}&bsn=${blockStartNum }&bln=${blockLastNum}&kwd=${kwd}">◀</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=${pi.currentPage -1}&bsn=${blockStartNum }&bln=${blockLastNum}">◀</a></li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						
						<c:forEach varStatus="vs" begin="${blockStartNum }" end="${blockLastNum}" step="1">
							<c:choose>
								<c:when test="${vs.index eq pi.currentPage}">
									<c:choose>
										<c:when test="${pi.listCount >= 1 }">
											<li class="selected">${vs.index }</li>
										</c:when>
										<c:otherwise>
											<li>${vs.index }</li>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>	
									<c:choose>
										<c:when test="${pi.maxPage >= vs.index}">
											<c:choose>
												<c:when test="${!empty kwd}">
													<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=${vs.index }&bsn=${blockStartNum }&bln=${blockLastNum}&kwd=${kwd}">${vs.index }</a></li>
												</c:when>
												<c:otherwise>
													<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=${vs.index }&bsn=${blockStartNum }&bln=${blockLastNum}">${vs.index }</a></li>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>	
											<li>${vs.index }</li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pi.maxPage eq pi.currentPage || pi.maxPage eq 0 }">
								<li>▶</li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${!empty kwd}">
										<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=${pi.currentPage +1}&bsn=${blockStartNum }&bln=${blockLastNum}&kwd=${kwd}">▶</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath }/board?a=list&currentPage=${pi.currentPage +1}&bsn=${blockStartNum }&bln=${blockLastNum}">▶</a></li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>					
				<!-- pager 추가 -->
				<c:if test="${!empty authUser }">
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