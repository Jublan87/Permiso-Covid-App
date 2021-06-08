const myModal = new bootstrap.Modal(document.getElementById("myModal"), {});
const btnClose = document.querySelectorAll(".modalbtn");

document.onreadystatechange = function () {
  myModal.show();
};

btnClose.forEach(btn => {
  btn.addEventListener("click", () => {
    myModal.hide();
  })
})