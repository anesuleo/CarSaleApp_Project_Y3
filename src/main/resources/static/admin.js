document.getElementById("admin-login-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const correctUsername = "admin";
    const correctPassword = "admin123";

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const output = document.getElementById("output");

    if (username === correctUsername && password === correctPassword) {
        output.textContent = "Login successful!";
        output.style.color = "green";

        window.location.href = "adminPage.html";
    }
    else {
        output.textContent = "Incorrect username or password. Please try again.";
        output.style.color = "red";
    }
});