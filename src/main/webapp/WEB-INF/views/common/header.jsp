<%@ page import="com.example.springbootboard.domain.users.dto.UserResponseDTO" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  UserResponseDTO userSession = Optional.ofNullable((UserResponseDTO) session.getAttribute("user")).orElseGet(UserResponseDTO::new);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <link rel="stylesheet" href="${request.contexPath}/resources/css/navbar.css">
  <link rel="stylesheet" href="${request.contexPath}/resources/css/base.css">
  <link rel="stylesheet" href="${request.contexPath}/resources/css/index.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <title>Admin Hana - index.jsp</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <div class="sb-sidenav accordion sb-sidenav-dark">
        <div class="sb-sidenav-logo"><a href="${request.contextPath}/"
                                        style="text-decoration: none; color: white;">Spring 게시판</a>
        </div>
        <div class="sb-sidenav-menu">
          <div class="nav">
            <%--      <div class="sb-sidenav-menu-heading">마이페이지</div>--%>
            <%--      <a class="nav-link" href="${request.contextPath}/user/1 ">--%>
            <%--        <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>--%>
            <%--        마이페이지--%>
            <%--      </a>--%>
            <a class="nav-link" href="${request.contextPath}/boards/list?teamName=all&page=0&size=10&sort=postId,DESC">
              <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
              전체 게시판
            </a>

            <div class="sb-sidenav-menu-heading">팀 게시판</div>
            <a class="nav-link"
               href="${request.contextPath}/boards/list?teamName=corebanking&page=0&size=10&sort=postId,DESC">
              <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
              코어 뱅킹 게시판
            </a>
            <a class="nav-link"
               href="${request.contextPath}/boards/list?teamName=devops&page=0&size=10&sort=postId,DESC">
              <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
              데브 옵스 게시판
            </a>

            <div class="sb-sidenav-menu-heading">회원 기능</div>
            <a class="nav-link" href="${request.contextPath}/users/join">
              <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
              회원가입
            </a>
            <c:if test="<%=userSession.getUsername() == null%>">
              <a class="nav-link" href="${request.contextPath}/users/login">
                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                로그인
              </a>
            </c:if>
            <c:if test="<%=userSession.getUsername() != null%>">
              <div class="sb-sidenav-footer">
                <div class="small">현재 상담 중인 손님:</div>
                <div class="d-flex justify-content-between"><span><%=userSession.getUsername()%>님</span>
                  <a class="btn btn-light btn-sm" style="--bs-btn-font-size: .50rem; display: inline-block"
                     href="${request.contextPath}/users/logout">로그아웃</a>
                </div>
              </div>
            </c:if>
          </div>
        </div>
      </div>

    </nav>

    <main>
