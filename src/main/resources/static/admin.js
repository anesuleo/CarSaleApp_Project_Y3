

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
function navigateTo(url) {
    window.location.href = url;
}

function fetchData(){
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
        .catch(error => console.error('Error:', error));
}

function addCar() {
    // Get values from the input fields
    const carData = {
        make: document.getElementById('make').value,
        model: document.getElementById('model').value,
        year: document.getElementById('year').value,
        cost: document.getElementById('cost').value
    };

    // Send a POST request to add the car
    fetch('http://localhost:8080/cars/addCar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(carData) // Convert carData object to JSON
    })
        .then(response => response.json()) // Assuming server responds with JSON
        .then(data => {
            if (data.success) {
                alert('Car added successfully!');
            } else {
                alert('Failed to add car.');
            }
        })
        .catch(error => console.error('Error:', error));
}

document.getElementById('submitButton').addEventListener('click', async () => {
    // Get form values
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;
    const phoneNumber = document.getElementById('phoneNo').value;

    // Create the payload
    const customerData = {
        firstName,
        lastName,
        email,
        phoneNumber
    };

    try {
        // Send POST request using fetch API
        const response = await fetch('http://localhost:8080/cars/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(customerData)
        });

        // Handle response
        const result = await response.json();
        if (response.ok) {
            document.getElementById('message').textContent = 'Customer added successfully!';
            document.getElementById('message').style.color = 'green';
        } else {
            document.getElementById('message').textContent = `Error: ${result.message || 'Failed to add customer.'}`;
            document.getElementById('message').style.color = 'red';
        }
    } catch (error) {
        document.getElementById('message').textContent = `Error: ${error.message}`;
        document.getElementById('message').style.color = 'red';
    }
});

function deleteCar() {
    // Get the car ID from the input field
    const car_id = document.getElementById('car-id').value;

    console.log('Car ID to delete:', car_id);

    // Validate if carId is provided
    if (!car_id) {
        alert('Please provide a Car ID to delete.');
        return;
    }

    // Send a DELETE request to delete the car
    fetch(`http://localhost:8080/cars/delete/${car_id}`, {
        method: 'DELETE',
    })
        .then(response => {
            console.log('Response status:', response.status); // Check the status
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.text(); // Assuming server responds with JSON
        })
        .then(data => {
            console.log('Response data:', data); // Log the string response
            alert(`Car with ID ${car_id} has been deleted successfully.`);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while deleting the car.');
        });
}
