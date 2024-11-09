
function selectRole(role) {
    document.getElementById('login-screen').style.display = 'none';

    if (role === 'customer') {
        document.getElementById('customer-section').style.display = 'block';
    } else if (role === 'admin') {
        document.getElementById('admin-section').style.display = 'block';
    }
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
function goBackToHome() {
    // Hide both Admin and Customer sections
    document.getElementById('customer-section').style.display = 'none';
    document.getElementById('admin-section').style.display = 'none';
    // Show the login screen
    document.getElementById('login-screen').style.display = 'block';
    // Clear any displayed data
    document.getElementById('output').innerHTML = '';
}