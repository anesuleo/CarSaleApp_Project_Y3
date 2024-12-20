let loginAttempts = 0;

// Show the customer login form
function showCustomerLogin() {
    document.getElementById('Customer-login').style.display = 'none';
    document.getElementById('customer-login-section').style.display = 'block';
}

// Show the password reset form
function showResetPasswordForm() {
    document.getElementById('customer-login-section').style.display = 'none';
    document.getElementById('reset-password-section').style.display = 'block';
}

// Show the registration form
function showRegisterSection() {
    document.getElementById('customer-login-section').style.display = 'none';
    document.getElementById('register-section').style.display = 'block';
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
    const email = document.getElementById('customer-email').value.trim();
    const password = document.getElementById('customer-password').value.trim();

    fetch('http://localhost:8080/cars/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Login successful!");
                fetchData(); // Load car data on successful login
                document.getElementById('customer-login-section').style.display = 'none';
                document.getElementById('Customer-login').style.display = 'none';  // Hide the initial buttons after login
                loginAttempts = 0; // Reset attempts on success
            } else {
                document.getElementById('login-error').textContent = data.message || "Incorrect login. Try again.";
                document.getElementById('login-error').style.display = 'block';
                loginAttempts++;
                if (loginAttempts >= 3) {
                    alert("3 attempts exceeded. Please reset your password.");
                    showResetPasswordForm(); // Show reset password form
                }
            }
        })
        .catch(error => {
            console.error('Login error:', error);
            alert('An error occurred while logging in. Please try again later.');
        });
}


// Handle password reset form submission
function resetCustomerPassword(event) {
    event.preventDefault();
    const email = document.getElementById('reset-email').value.trim();
    const newPassword = document.getElementById('new-password').value.trim();

    console.log("Reset Password Attempt", { email, newPassword });

    if (!email || !newPassword) {
        alert('Please fill in all fields.');
        return;
    }

    fetch(`http://localhost:8080/cars/updatePassword/${email}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ password: newPassword }),
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => {
                    throw new Error(err.message || 'Failed to reset password');
                });
            }
            return response.text();
        })
        .then(message => {
            alert(message || 'Password updated successfully!');
            document.getElementById('reset-password-section').style.display = 'none';
            showCustomerLogin();
        })
        .catch(error => {
            alert(error.message);
        });
}

// Handle customer registration form submission
function registerCustomer(event) {
    event.preventDefault();

    const firstName = document.getElementById('register-first-name').value.trim();
    const lastName = document.getElementById('register-last-name').value.trim();
    const phoneNo = document.getElementById('register-phone').value.trim();
    const email = document.getElementById('register-email').value.trim();
    const password = document.getElementById('register-password').value.trim();

    // Validate input
    if (!firstName || !lastName || !phoneNo || !email || !password) {
        alert('All fields are required.');
        return;
    }
    if (!/^\d{10}$/.test(phoneNo)) {
        alert('Phone number must be exactly 10 digits.');
        return;
    }

    const customerData = { firstName, lastName, phoneNo, email, password };

    fetch('http://localhost:8080/cars/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(customerData),
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error('Failed to register. Server responded with an error.');
            }
            return response.json();
        })
        .then((data) => {
            if (data.success) {
                alert('Registration successful! Please log in.');
                document.getElementById('register-section').style.display = 'none';  // Hide the register section
                showCustomerLogin();
            } else if (data.message) {
                alert(`Error: ${data.message}`);
            }
        })
        .catch((error) => {
            console.error('Registration error:', error);
            alert('Registration successful! Please log in.');
            document.getElementById('register-section').style.display = 'none';  // Hide the register section
            showCustomerLogin();
        });
}

// Fetch car data (guest or logged-in user)
function fetchData() {
    fetch('http://localhost:8080/cars/allcars')
        .then(response => response.json())
        .then(data => {
            let output = `
        <table border="1">
        <tr>
        <th>ID</th>
        <th>Make</th>
        <th>Model</th>
        <th>Year</th>
        <th>Cost</th>
        </tr>`;
            data.forEach(car => {
                output += `
            <tr>
            <td>${car.car_id}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.year}</td>
            <td>$${car.cost}</td>
            </tr>`;
            });
            output += '</table>';
            document.getElementById('output').innerHTML = output;
        })
        .catch(error => console.error('Error fetching cars:', error));
}
