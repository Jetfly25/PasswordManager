function checkNewPasswordStrength() {
    const newPassword = document.getElementById("newPassword").value;
    const strengthIndicator = document.getElementById("passwordStrength");
    const containsWhitespace = (str) => /\s/.test(str);

    let isValidPasswordLength = false;
    let isValidLetter = false;
    let isValidNumber = false;
    let isValidSpecialChar = false;
    let hasWhiteSpace = false;
    
    if (newPassword.length >= 8) {
        isValidPasswordLength = true;
    }

    if (/[A-Za-z]/.test(newPassword)) {
        isValidLetter = true;
    }

    if (/[0-9]/.test(newPassword)) {
        isValidNumber = true
    }

    if (/[!@#$%^&*(),.?":{}|<>]/.test(newPassword)) {
        isValidSpecialChar = true
    }

    if (containsWhitespace(newPassword)){
        hasWhiteSpace = true;
    }
    strengthIndicator.style.backgroundColor = "#ff4d4d";
    if (!isValidPasswordLength) {
        strengthIndicator.textContent = "Password must be at least 8 characters!";
    } else if (!isValidLetter) {
        strengthIndicator.textContent = "Password must contain at least one letter! (A-Z)";
    } else if (!isValidNumber) {
        strengthIndicator.textContent = "Password must contain at least one letter! (0-9)";
    } else if (!isValidSpecialChar) {
        strengthIndicator.textContent = "Password must contain at least one special character! (!, ?, @, etc.)";
    } else if (hasWhiteSpace){
        strengthIndicator.textContent = "Password must not have spaces!";
    } else if (isValidPasswordLength && isValidLetter && isValidSpecialChar && isValidNumber && !hasWhiteSpace){
        strengthIndicator.textContent = "Good!";
        strengthIndicator.style.backgroundColor = "#4caf50";
    }
}

function validateNewPassword() {
    const password = document.getElementById("newPassword").value;
    const verifyPassword = document.getElementById("confirmPassword").value;
    const matchIndicator = document.getElementById("validatePassword");
    const changeButton = document.getElementById("changeButton");

    matchIndicator.style.display = "none";
    changeButton.disabled = true;
    if (password !== verifyPassword) {
        matchIndicator.style.display = "block";
        matchIndicator.textContent = "Passwords do not match!";
        matchIndicator.style.backgroundColor = "#ff4d4d";
    } else {
        changeButton.disabled = false;
    }
}

function checkPasswordsSame(){
    const oldPassword = document.getElementById("oldPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const checkPasswordIndicator = document.getElementById("checkPasswordIndicator");
    const strengthIndicator = document.getElementById("passwordStrength");

    let isPasswordsSame = oldPassword === newPassword;
    if (isPasswordsSame && oldPassword !== "") {
        checkPasswordIndicator.style.backgroundColor = "#ff4d4d";
        checkPasswordIndicator.style.display = "block";
        checkPasswordIndicator.textContent = "The old password and new password cannot be the same!";
        strengthIndicator.style.backgroundColor = "#ffffff";
    } else {
        checkPasswordIndicator.style.backgroundColor = "#ffffff";

    }
}
document.getElementById("newPassword").addEventListener("input", checkPasswordsSame);
document.getElementById("oldPassword").addEventListener("input", checkPasswordsSame);
document.getElementById("confirmPassword").addEventListener("input", validateNewPassword);
document.getElementById("newPassword").addEventListener("input", validateNewPassword);