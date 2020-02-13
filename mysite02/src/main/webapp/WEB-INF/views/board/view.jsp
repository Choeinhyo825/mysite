<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
							<c:choose>
								<c:when test='${vo.status eq "y"}'>
									<td>${vo.title }</td>
								</c:when>
								<c:otherwise>
									<td>삭제된 게시글입니다.</td>
								</c:otherwise>
							</c:choose>
					</tr>
					<tr>	
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								<c:choose>
									<c:when test='${vo.status eq "y"}'>
										<p>${fn:replace(vo.contents, newLine, "<br>")}<p>
									</c:when>
									<c:otherwise>
										<p>삭제된 게시글입니다.</p>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=list">글목록</a>
					<c:choose>
						<c:when test="${!empty loginUser && loginUser.no eq vo.userNo }">
							<c:if test='${status eq "y" }'>
								<a href="${pageContext.request.contextPath }/board?a=modifyForm&no=${vo.no}">글수정</a>
							</c:if>
						</c:when>
						<c:when test="${!empty loginUser && loginUser.no ne vo.userNo}">
							<form action="board" method="post" >
								<input type="hidden" value="writeForm" name=a>
								<input type="hidden" value="${vo.no }" name="boardNo">
								<input type="hidden" value="${vo.gno }" name="gno">
								<input type="hidden" value="${vo.ono }" name="ono">
								<input type="hidden" value="${vo.depth }" name="depth">
								<input type="submit" value="답글달기">
							</form>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>