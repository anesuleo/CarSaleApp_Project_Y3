//customer login form for registered users
let loginAttempts=0;
function showCustomerLogin(){
    document.getElementById('customer-login-options').style.display='none';
    document.getElementById('customer-login-section').style.display='block';
}
// customer.js

// Display the login form for registered users
function showCustomerLogin() {
    document.getElementById('Customer-login').style.display = 'none';
    document.getElementById('customer-login-section').style.display = 'block';
}

// Hide other buttons except the clicked one
function hide(clickedButton) {
    const buttons = document.querySelectorAll('#Customer-login button');
    buttons.forEach(button => {
        if (button !== clickedButton) {
            button.style.display = 'none';
        }
    });
}

// Handle customer login form submission
function loginCustomer(event) {
    event.preventDefault();
    const email = document.getElementById('customer-email').value;
    const password = document.getElementById('customer-password').value;

    fetch('http://localhost:8080/customer/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                fetchData(); // Load car data on successful login
            } else {
                loginAttempts++;
                document.getElementById('login-error').style.display = 'block';
                if (loginAttempts >= 3) {
                    alert("3 attempts exceeded. Please reset your password.");
                }
            }
        })
        .catch(error => console.error('Error:', error));
}

// Show the registration form
function showRegisterSection() {
    document.getElementById('customer-login-section').style.display = 'none';
    document.getElementById('register-section').style.display = 'block';
}
