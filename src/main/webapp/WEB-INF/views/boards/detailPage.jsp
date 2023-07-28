<%@ page import="com.example.springbootboard.domain.posts.dto.PostResponseDTO" %>
<%@ page import="com.example.springbootboard.domain.uploadfiles.UploadFile" %>
<%@ page import="com.example.springbootboard.domain.comments.Comment" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.example.springbootboard.domain.users.dto.UserResponseDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  UserResponseDTO userSession = Optional.ofNullable((UserResponseDTO) session.getAttribute("user")).orElseGet(UserResponseDTO::new);
  PostResponseDTO postResponseDTO = (PostResponseDTO) request.getAttribute("postResponseDTO");
  String createdAt = postResponseDTO.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss"));
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <link rel="stylesheet" href="${request.contextPath}/resources/css/navbar.css">
  <link rel="stylesheet" href="${request.contextPath}/resources/css/base.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <title>Admin Hana - index.jsp</title>
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
          게시판
        </div>

        <div class="card-body w-100">
          <div class="postDetailContainer">
            <div class="postTitle">
              <p class="fw-bold m-0"><%=postResponseDTO.getPostTitle(
              )%>
              </p>
              <p class="">
                <span><%=postResponseDTO.getNickname()%></span>
                <span class="mx-4" style="border-left:1px solid #bbbbbb;"></span>
                <span><%=createdAt%></span>
                <span class="mx-4" style="border-left:1px solid #bbbbbb;"></span>
                <span>조회수: <%=postResponseDTO.getView()%></span>
              </p>
            </div>
            <hr>
            <p class=" postContent" style="min-height: 10rem;">
              <%=postResponseDTO.getPostContent()%>
            </p>
            <hr>

            <%--사진 영역--%>
            <%
              for (UploadFile uploadFile : postResponseDTO.getUploadFiles()) {
            %>
            <img src="<%=uploadFile.getFileUrl()%>">
            <%
              }
            %>

            <%-- 버튼 영역            --%>
            <div class="mb-5">
              <a href="${request.contextPath}/boards/list?teamName=<%=postResponseDTO.getTeamName()%>&page=0&size=10&sort=postId,DESC">
                <button type="button" class="mt-4 btn btn-outline-secondary">글 목록</button>
              </a>
              <a href="${request.contextPath}/boards/update?teamName=<%=postResponseDTO.getTeamName()%>&postId=<%=postResponseDTO.getPostId()%>">
                <button type="button" class="mt-4 btn btn-outline-secondary">글 수정</button>
              </a>
              <form action="delete?teamName=<%=postResponseDTO.getTeamName()%>&postId=<%=postResponseDTO.getPostId()%>"
                    method="get" class="d-inline-block">
                <input type="hidden" name="postId" value="<%=postResponseDTO.getPostId()%>">
                <input type="hidden" name="teamName" value="<%=postResponseDTO.getTeamName()%>">
                <button type="submit" class="mt-4 btn btn-outline-danger">글 삭제</button>
              </form>
            </div>

            <%-- 댓글 영역           --%>
            <section class="commentsContainer mb-5">
              <div class="card bg-light">
                <div class="card-body">
                  <!-- 댓글 작성 폼 -->
                  <form id="nickPwForm" class="mb-4 d-flex gap-2" method="post"
                        action="detail/comments?teamName=<%=postResponseDTO.getTeamName()%>&postId=<%=postResponseDTO.getPostId()%>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div>
                      <input class="p-2 form-control" name="nickname" id="nickname" type="text" placeholder="닉네임"
                             minlength="2"
                             maxlength="10">
                      <input class="p-2 form-control" name="password" id="password" type="password" placeholder="비밀번호"
                             minlength="2"
                             maxlength="10">
                    </div>
                    <textarea name="commentContent" class="form-control" rows="3"
                              placeholder="Join the discussion and leave a comment!"></textarea>
                    <%--                    <input type="hidden" name="userId" value="<%=postResponseDTO.getUserId()%>"/>--%>
                    <input type="submit" class="btn btn-outline-secondary">
                  </form>
                  <!-- Single comment-->
                  <%
                    for (Comment comment : postResponseDTO.getComments()) {
                  %>
                  <div class="d-flex mb-4">
                    <div class="flex-shrink-0">
                      <img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="...">
                    </div>
                    <div class="ms-3 w-100">
                      <div class="d-flex justify-content-between">
                        <%--                        <span class="fw-bold"><%=comment.getUserId()%></span>--%>
                        <span class="fw-bold"><%=comment.getNickname()%></span>
                        <span><%=comment.getCreatedAt().format(DateTimeFormatter.ISO_DATE)%></span>
                      </div>
                      <%=comment.getCommentContent()%>
                    </div>
                  </div>
                  <%
                    }
                  %>
                  <!-- Comment with nested comments-->
                  <%--                  <div class="d-flex mb-4">--%>
                  <%--                    <!-- Parent comment-->--%>
                  <%--                    <div class="flex-shrink-0"><img class="rounded-circle"--%>
                  <%--                                                    src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="...">--%>
                  <%--                    </div>--%>
                  <%--                    <div class="ms-3">--%>
                  <%--                      <div class="fw-bold">Commenter Name</div>--%>
                  <%--                      If you're going to lead a space frontier, it has to be government; it'll never be private--%>
                  <%--                      enterprise. Because the space frontier is dangerous, and it's expensive, and it has unquantified--%>
                  <%--                      risks.--%>
                  <%--                      <!-- Child comment 1-->--%>
                  <%--                      <div class="d-flex mt-4">--%>
                  <%--                        <div class="flex-shrink-0"><img class="rounded-circle"--%>
                  <%--                                                        src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="...">--%>
                  <%--                        </div>--%>
                  <%--                        <div class="ms-3">--%>
                  <%--                          <div class="fw-bold">Commenter Name</div>--%>
                  <%--                          And under those conditions, you cannot establish a capital-market evaluation of that--%>
                  <%--                          enterprise. You can't get investors.--%>
                  <%--                        </div>--%>
                  <%--                      </div>--%>
                  <%--                      <!-- Child comment 2-->--%>
                  <%--                      <div class="d-flex mt-4">--%>
                  <%--                        <div class="flex-shrink-0"><img class="rounded-circle"--%>
                  <%--                                                        src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="...">--%>
                  <%--                        </div>--%>
                  <%--                        <div class="ms-3">--%>
                  <%--                          <div class="fw-bold">Commenter Name</div>--%>
                  <%--                          When you put money directly to a problem, it makes a good headline.--%>
                  <%--                        </div>--%>
                  <%--                      </div>--%>
                  <%--                    </div>--%>
                  <%--                  </div>--%>

                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <script src="${request.contextPath}/resources/js/writePost.js">
  </script>
</body>
</html>
