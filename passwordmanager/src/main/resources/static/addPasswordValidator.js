function validateAddPassword() {
    const password = document.getElementById("password").value;
    const verifyPassword = document.getElementById("confirmPassword").value;
    const matchIndicator = document.getElementById("validatePassword");
    const addPasswordButton = document.getElementById("addPasswordButton");

    matchIndicator.style.backgroundColor = "#ffffff";
    addPasswordButton.disabled = true;
    if (password !== verifyPassword) {
        matchIndicator.style.display = "block";
        matchIndicator.textContent = "Passwords do not match!";
        matchIndicator.style.backgroundColor = "#ff4d4d";
    } else {
        addPasswordButton.disabled = false;
    }
}

document.getElementById("password").addEventListener("input", validateAddPassword);
document.getElementById("confirmPassword").addEventListener("input", validateAddPassword);