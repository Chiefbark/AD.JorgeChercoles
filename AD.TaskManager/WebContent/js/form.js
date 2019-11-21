function showForm() {
  var formContainer = document.getElementById("formContainer");
  formContainer.classList.remove("d-none");
}

function hideForm() {
  var formContainer = document.getElementById("formContainer");
  formContainer.classList.add("d-none");
}

function showAlert(msg) {
  var alert = document.getElementById("alert");
  alert.innerHTML = msg;
  alert.classList.remove("d-none");
  setTimeout(() => {
    alert.classList.add("d-none");
  }, 5000);
}

window.addEventListener("click", e => {
  var formContainer = document.getElementById("formContainer");
  if (e.target == formContainer) hideForm();
});

window.addEventListener("keydown", e => {
	if(e.which === 27)
		hideForm();
});