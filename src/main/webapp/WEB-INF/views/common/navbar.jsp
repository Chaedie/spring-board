<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
%>
<div class="sb-sidenav accordion sb-sidenav-dark">

  <div class="sb-sidenav-logo"><a href="<%=contextPath%>/"
                                  style="text-decoration: none; color: white;">Spring 게시판</a>
  </div>
  <div class="sb-sidenav-menu">
    <div class="nav">
      <%--      <div class="sb-sidenav-menu-heading">마이페이지</div>--%>
      <%--      <a class="nav-link" href="<%=contextPath%>/user/1 ">--%>
      <%--        <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>--%>
      <%--        마이페이지--%>
      <%--      </a>--%>
      <a class="nav-link" href="<%=contextPath%>/boards/list?page=0&size=10&sort=postId,DESC">
        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
        게시판
      </a>

      <a class="nav-link" href="<%=contextPath%>/users/join">
        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
        회원가입
      </a>

    </div>
  </div>
