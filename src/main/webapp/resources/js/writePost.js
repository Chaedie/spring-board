const $saveForm = document.querySelector("#saveForm");

// const nickPwForm = document.querySelector("#nickPwForm")
// nickPwForm.addEventListener('submit', validateSavePost)

const validateSavePost = (e) => {
  e.preventDefault();
  const nickname = e.target.nickname.value;
  const password = e.target.password.value;
  if (nickname.length < 2 || password.length < 2) {
    alert("닉네임, 패스워드는 필수입니다.")
    return;
  }
  savePost();
}

// const $saveButton = document.querySelector("#saveButton");
// $saveButton.addEventListener('click', savePost);
const savePost = () => {
  const formData = new FormData($saveForm);
  fetch("/api/v1/boards/write/api", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "X-CSRF-Token": document.querySelector("#csrf").value,
    },
    body: formData,
  })
      .then(res => {
        if (!res.ok) {
          throw new Error();
        }
        console.log(res);
        return res.json()
      })
      .then(data => {

        alert("글 등록 성공");
        // window.location.href
        console.log("성공", data)
      })
      .catch(error => {
        alert("글 등록 실패");
      })
}


$saveForm.addEventListener('submit', validateSavePost);
