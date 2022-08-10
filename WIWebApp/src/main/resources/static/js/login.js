/* Query Selector Password */
const login_password = document.querySelector("#password");

/* Query Selector Eyes */
const login_eyes = document.querySelector("#show-pass-login");

login_eyes.addEventListener("click", function(e) {
    const typeLoginPass = login_password.getAttribute("type") === "password" ? "text" : "password";
    login_password.setAttribute("type", typeLoginPass);
    this.classList.toggle("fa-eye-slash");
});