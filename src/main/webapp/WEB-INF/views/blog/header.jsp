<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="header" class="header">
	<div class="header_inner_wrap">
		<div class="header_logo_wrap">
			<span id="headerLogo" class="header_logo">oh29oh29</span>
		</div>
		<div class="header_member_wrap">
			<c:choose>
				<c:when test="${empty sessionScope.member}">
					<span id="headerSignInBtn" class="header_btn">Sign in</span>	
				</c:when>
				<c:otherwise>
					<span id="headerMemberName">${sessionScope.member.name}님</span>
					<c:if test="${sessionScope.member.isAdmin == 1}">
					<span id="headerAdminBtn" class="header_btn">관리자</span>
					</c:if>
					<span id="headerSignOutBtn" class="header_btn">Sign out</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>