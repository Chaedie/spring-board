const $writeForm = document.querySelector("#writeForm")
$writeForm.addEventListener('submit', (e) => {
  const nickname = e.target.nickname.value;
  const password = e.target.password.value;
  if (nickname.length < 2 || password.length < 2) {
    e.preventDefault();
    alert("닉네임, 패스워드는 필수입니다.")
    return;
  }
})
