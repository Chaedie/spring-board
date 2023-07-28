<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>
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

  <div class="card-body">
    <form id="saveForm" class="d-flex flex-column gap-2" method="post"
          enctype="multipart/form-data">
      <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <div class="d-flex gap-2">
        <input class="p-2 form-control" name="nickname" id="nickname" type="text" placeholder="닉네임"
               minlength="2"
               maxlength="10">
        <input class="p-2 form-control" name="password" id="password" type="password" placeholder="비밀번호"
               minlength="2"
               maxlength="10">
      </div>
      <input class="p-2 form-control" name="postTitle" id="title" type="text" placeholder="제목">
      <textarea class="p-2 form-control" name="postContent" placeholder="내용" rows="20" cols="5"
                style="resize: none"></textarea>
      <input type="file" name="multipartFiles" multiple accept="image/*">
      <button type="submit" id="saveButton" class="mt-4 btn btn-outline-secondary">제출</button>

      <input type="hidden" name="teamName" value="${sessionScope.user.team.teamName}">
    </form>
  </div>
</div>
<script src="${request.contextPath}/resources/js/writePost.js">
  <%@ include file="../common/footer.jsp" %>
