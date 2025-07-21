function checkPasswordStrength() {
    const password = document.getElementById("password").value;
    const strengthIndicator = document.getElementById("passwordStrength");
    const containsWhitespace = (str) => /\s/.test(str);

    let isValidPasswordLength = false;
    let isValidLetter = false;
    let isValidNumber = false;
    let isValidSpecialChar = false;
    let hasWhiteSpace = false;

    if (password.length >= 8) {
        isValidPasswordLength = true;
    }

    if (/[A-Za-z]/.test(password)) {
        isValidLetter = true;
    }

    if (/[0-9]/.test(password)) {
        isValidNumber = true
    }

    if (/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
        isValidSpecialChar = true
    }

    if (containsWhitespace(password)){
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

function validatePassword() {
    const password = document.getElementById("password").value;
    const verifyPassword = document.getElementById("confirmPassword").value;
    const matchIndicator = document.getElementById("validatePassword");
    const registerButton = document.getElementById("registerButton");

    matchIndicator.style.display = "none";
    registerButton.disabled = true;
    if (password !== verifyPassword) {
        matchIndicator.style.display = "block";
        matchIndicator.textContent = "Passwords do not match!";
        matchIndicator.style.backgroundColor = "#ff4d4d";
    } else {
        registerButton.disabled = false;
    }
}

document.getElementById("password").addEventListener("input", validatePassword);
document.getElementById("confirmPassword").addEventListener("input", validatePassword);