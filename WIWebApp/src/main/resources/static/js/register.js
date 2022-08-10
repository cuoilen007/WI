/* Query Selector Password */
const register_password = document.querySelector("#register-password");
const repeat_password = document.querySelector("#repeat-password");

/* Query Selector Eyes */
const register_eyes = document.querySelector("#show-pass-register");
const repeat_eyes = document.querySelector("#show-pass-repeat");

register_eyes.addEventListener("click", function(e) {
    const typeRegisterPass = register_password.getAttribute("type") === "password" ? "text" : "password";
    register_password.setAttribute("type", typeRegisterPass);
    this.classList.toggle("fa-eye-slash");
});

repeat_eyes.addEventListener("click", function(e) {
    const typeRepeatPass = repeat_password.getAttribute("type") === "password" ? "text" : "password";
    repeat_password.setAttribute("type", typeRepeatPass);
    this.classList.toggle("fa-eye-slash");
});