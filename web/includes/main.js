
function validateForm(){
    // Declare variables and assign them with id
    // Variables for inputs
    var fullName = document.getElementById("fullName");
    var userName = document.getElementById("userName");
    var email = document.getElementById("email");
    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("confirmPassword");
    var answer = document.getElementById("answer");
    
    // Variables for span error
    var fullName_error = document.getElementById("fullName_error");
    var userName_error = document.getElementById("userName_error");
    var email_error = document.getElementById("email_error");
    var password_error = document.getElementById("password_error");
    var confirmPassword_error = document.getElementById("confirmPassword_error");
    var answer_error = document.getElementById("answer_error");
    
    var valid = true;
    
    /* Check 1
    // Check if password and confirmPassword matches
    // If they don't match
    if (password.value !== confirmPassword.value){
        // Background of password and confirm password inputs change to yellow
        confirmPassword.style.backgroundColor = "yellow";
        password.style.backgroundColor = "yellow";
        
        password_error.innerHTML = "Password does not match with confirm password.\n";
        confirmPassword_error.innerHTML = "Confirm password does not match with password.\n";
        
        // Show password and confirm password error span '*'
        password_error.style.display = "inline";
        confirmPassword_error.style.display = "inline";
        
        // Change their class to isVisible
        password_error.class = "isVisible";
        confirmPassword_error.class = "isVisible"
        
        // Show error message in errorDiv
        errorDiv.class = "isVisible";
        errorDiv.style.display = "inline";
        
        return false;
    }
    // If they match
    else
    {
        // Password and confirm password error span become invisible
        password_error.style.display = "none";
        confirmPassword_error.style.display = "none";
        
        // Password and confirm pasword error span background become white
        password_error.style.display = "white";
        confirmPassword.style.backgroundColor = "white";
        
        // The errorDiv become invisible
        errorDiv.class = "notVisible";
        errorDiv.style.display = "none";
    }
    */
    
    // Check 2
    // If full name is not only one word
    if (fullName.value.indexOf(' ') >= 0){
        fullName.style.backgroundColor = "white";
        fullName_error.style.display = "none";
    }
    // If full name is one word
    else{
        // Full name background become yellow
        fullName.style.backgroundColor = "yellow";
        
        // Display the error message
        fullName_error.innerHTML = "Full name is not valid.\n";
        
        // Show full name error span '*'
        fullName_error.style.display = "inline";
        
        return false;
    }
    
    
    // Check 3
    // Check if inputs has single quotation mark in them
    if (fullName.value.indexOf('\'') >= 0){
        fullName_error.innerHTML = "Invalid input character.\n";
        fullName_error.style.display = "inline";
        fullName.style.backgroundColor = "yellow";
        return false;
    }
    else
    {
        fullName.style.backgroundColor = "white";
        fullName_error.style.display = "none";
    }
    
    if (userName.value.indexOf('\'') >= 0){
        userName_error.innerHTML = "Invalid input character.\n";
        userName_error.style.display = "inline";
        userName.style.backgroundColor = "yellow";
        return false;
    }
    else
    {
        userName.style.backgroundColor = "white";
        userName_error.style.display = "none";
    }
    
    if (email.value.indexOf('\'') >= 0){
        email_error.innerHTML = "Invalid input character.\n";
        email_error.style.display = "inline";
        email.style.backgroundColor = "yellow";
        return false;
    }
    else
    {
        email.style.backgroundColor = "white";
        email_error.style.display = "none";
    }
    
    if (password.value.indexOf('\'') >= 0){
        password_error.innerHTML = "Invalid input character.\n";
        password_error.style.display = "inline";
        password.style.backgroundColor = "yellow";
        return false;
    }
    else
    {
        password.style.backgroundColor = "white";
        password_error.style.display = "none";
    }
    
    if (confirmPassword.value.indexOf('\'') >= 0){
        confirmPassword_error.innerHTML = "Invalid input character.\n";
        confirmPassword_error.style.display = "inline";
        confirmPassword.style.backgroundColor = "yellow";
        return false;
    }
    else
    {
        confirmPassword.style.backgroundColor = "white";
        confirmPassword_error.style.display = "none";
    }
    
    if (answer.value.indexOf('\'') >= 0){
        answer_error.innerHTML = "Invalid input character.\n";
        answer_error.style.display = "inline";
        answer.style.backgroundColor = "yellow";
        return false;
    }
    else
    {
        answer.style.backgroundColor = "white";   
        answer_error.style.display = "none";
    }
    
    // Check 4
    // Make sure password contains a small letter, a capital letter, and a number
    var letters = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{0,}/;
    if (!(password.value.match(letters))){
        valid = false;
        password.style.backgroundColor = "yellow";
        password_error.innerHTML = "Password must contains a small letter, a capital letter, and a number.";
        password_error.style.display = "inline";
    }
    else
    {
        password.style.backgroundColor = "white";
        password_error.style.display = "none";
    }
    
    return valid;
}

function showAnswer() {
    var answerShow = document.getElementById("answer");
    answerShow.style.display = "inline";
    }
    
function showAnswer2() {
    var answerShow = document.getElementById("fpAnswer");
    answerShow.style.display = "inline";
}

function showSignOut() {
    document.getElementById("signout").className='isVisible';
}
    
function showSignIn() {
    document.getElementById("signout").className='isVisible';
    document.getElementById("notification").className='isVisible';
    document.getElementById("profile").className='isVisible';
    document.getElementById("login").className='notVisible';
    document.getElementById("signup").className='notVisible';
}