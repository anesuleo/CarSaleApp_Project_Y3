let loginAttempts = 0;
// Show Customer Login Form
function showCustomerLogin() {
    document.getElementById('Customer-login').style.display = 'none';
    document.getElementById('customer-login-section').style.display = 'block';
}
// Show Registration Form
function showRegisterSection() {
    document.getElementById('Customer-login').style.display = 'none';
    document.getElementById('register-section').style.display = 'block';
}
// Hide Buttons After Click
function hide(clickedButton) {
    const buttons = document.querySelectorAll('#Customer-login button');
    buttons.forEach(button => {
        if (button !== clickedButton) {
            button.style.display = 'none';
        }
    });
}
// Handle Login for Registered Users
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
                showAvailableCars();
            } else {
                handleLoginError(data.message);
            }
        })
        .catch(error => console.error('Login error:', error));
}
// Handle Guest Login
function handleGuestLogin() {
    showAvailableCars();
}

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
                document.getElementById('register-section').style.display = 'none'; // Hide the register section
                showCustomerLogin();
            } else if (data.message) {
                alert(`Error: ${data.message}`);
            }
        })
        .catch((error) => {
            console.error('Registration error:', error);
            alert('Registration successful! Please log in.');
            document.getElementById('register-section').style.display = 'none'; // Hide the register section
            showCustomerLogin();
        });
}

// Display Available Cars with Cart Logic
function showAvailableCars() {
    document.getElementById('car-table-section').style.display = 'block';
    fetch('http://localhost:8080/cars/allcars')
        .then(response => response.json())
        .then(data => {
            let output = `
<table border="1">
<thead>
<tr>
<th>ID</th>
<th>Make</th>
<th>Model</th>
<th>Year</th>
<th>Cost</th>
<th>Add to Cart</th>
</tr>
</thead>
<tbody>
           `;
            data.forEach(car => {
                output += `
<tr>
<td>${car.car_id}</td>
<td>${car.make}</td>
<td>${car.model}</td>
<td>${car.year}</td>
<td>$${car.cost}</td>
<td><button class="add-to-cart" data-car-id="${car.car_id}">Add to Cart</button></td>
</tr>
               `;
            });
            output += '</tbody></table>';
            document.getElementById('output').innerHTML = output;
            document.querySelectorAll('.add-to-cart').forEach(button => {
                button.addEventListener('click', addToCart);
            });
        })
        .catch(error => console.error('Error:', error));
}
// Add Car to Cart
function addToCart(event) {
    const carId = event.target.getAttribute('data-car-id');
    fetch(`http://localhost:8081/cart/add/${carId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
    })
        .then(response => response.json())
        .then(() => alert(`Car with ID ${carId} added to cart`))
        .catch(error => console.error('Error adding car to cart:', error));
}

function goToCart() {
    window.location.href = 'shoppingCart.html';
}