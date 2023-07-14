<%--
  Created by IntelliJ IDEA.
  User: chaedongim
  Date: 2023/07/14
  Time: 2:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  Long postId = (Long) request.getAttribute("postId");
  System.out.println("postId = " + postId);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/navbar.css">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/base.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <title>Admin Hana - deletePage.jsp</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="../common/navbar.jsp" %>
    </nav>

    <main>
      <div class="card m-4">
        <div class="card-header">
          <svg height="20px" width="20px" class="svg-inline--fa fa-table me-1" aria-hidden="true" focusable="false"
               data-prefix="fas"
               data-icon="table" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg="">
            <path fill="currentColor"
                  d="M64 256V160H224v96H64zm0 64H224v96H64V320zm224 96V320H448v96H288zM448 256H288V160H448v96zM64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64z"></path>
          </svg>
          글 삭제
        </div>
        <div class="card-body w-100">
          <form class="" action="delete" method="post">
            <input class="p-2 form-control" name="nickname" id="nickname" type="text" placeholder="닉네임"
                   minlength="2"
                   maxlength="10">
            <input class="p-2 form-control" name="password" id="password" type="password" placeholder="비밀번호"
                   minlength="2"
                   maxlength="10">
            <input type="hidden" name="postId" value="<%=postId%>">
            <button type="submit" class="mt-4 btn btn-outline-danger">글 삭제</button>
          </form>
        </div>
      </div>
      <%-- Code Here! --%>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
</body>
</html>
