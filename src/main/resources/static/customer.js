let loginAttempts = 0;

// Show the customer login form

function showCustomerLogin() {
    document.getElementById('Customer-login').style.display = 'none';
    document.getElementById('customer-login-section').style.display = 'block';
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
    const email = document.getElementById('customer-email').value;
    const password = document.getElementById('customer-password').value;
    fetch('http://localhost:8080/cars/login', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({email, password})
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Login successful!");
                fetchData(); // Load car data on successful login
            } else {
                document.getElementById('login-error').textContent = data.message || "Incorrect login. Try again.";
                document.getElementById('login-error').style.display = 'block';
                loginAttempts++;
                if (loginAttempts >= 3) {
                    alert("3 attempts exceeded. Please reset your password.");
                }
            }
        })
        .catch(error => console.error('Login error:', error));
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
            // Check HTTP status code
            if (!response.ok) {
                throw new Error('Failed to register. Server responded with an error.');
            }
            return response.json();
        })
        .then((data) => {
            console.log(data);

            if (data.success) {
                alert('Registration successful! Please log in.');
                showCustomerLogin();
            } else if (data.message) {
                // Display the actual backend error message
                alert(`Error: ${data.message}`);
            } else {
                // Catch any unexpected backend behavior
                alert('Registration successful! Please log in.');
            }
        })
        .catch((error) => {
            console.error('Registration error:', error);
            alert('An unexpected error occurred. Please try again later.');
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