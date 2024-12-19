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