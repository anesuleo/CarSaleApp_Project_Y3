
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

document.addEventListener('DOMContentLoaded', function (){
    fetchDisplayData();
    fetchCartData();
});

function fetchDisplayData(){
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
                <tbody>`;
            data.forEach(car => {
                output += `
                    <tr>
                        <td>${car.car_id}</td>
                        <td>${car.make}</td>
                        <td>${car.model}</td>
                        <td>${car.year}</td>
                        <td>$${car.cost}</td>
                        <td><button class="add-to-cart" data-car-id="${car.car_id}" data-make="${car.make}" data-model="${car.model}" data-year="${car.year}" data-cost="${car.cost}">Add to Cart</button></td>
                        
                    </tr>`;
            });
            output += `</tbody></table>`;

            const outputElement = document.getElementById('output');
            if(outputElement){
                outputElement.innerHTML = output;
            } else{
                console.error('Output element not found');
            }

            const addButtons = document.querySelectorAll('.add-to-cart');
            addButtons.forEach(button => {
                button.addEventListener('click', addToCart);
            });
        })
        .catch(error => console.error('Error:', error));
}

function addToCart(event){
    const button = event.target;
    const car = {
        car_id: button.getAttribute('data-car-id'),
        make: button.getAttribute('data-make'),
        model: button.getAttribute('data-model'),
        year: button.getAttribute('data-year'),
        cost: parseFloat(button.getAttribute('data-cost')),
    };

    console.log('Adding car to cart:', car);

    if (!car.car_id || !car.make || !car.model || !car.year || isNaN(car.cost)) {
        console.error('Invalid car data:', car);
        alert('Failed to add car to cart due to missing data.');
        return;
    }

    fetch(`http://localhost:8081/cart/add/${car.car_id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(car),
    })
        .then(response => response.json())
        .then(cart => {
            console.log('Cart updated:', cart);
            alert(`Car with ID ${car.car_id} has been added to your cart`);
            fetchCartData();
        })
        .catch(error => {
            console.error('Error adding car to cart:', error);
        });
}

function fetchCartData(){
    fetch('http://localhost:8081/cart/allcart')
        .then(response => response.json())
        .then(cart => {
            //console.log('Cart data:', cart);
            if(cart && cart.items){
                displayCart(cart.items);
            } else {
                console.log('No cart items found:', cart);
            }
        })
        .catch(error => {
            console.error('Error fetching cart data:', error);
        });
}

function displayCart(cartItems){
    const cartTableBody = document.querySelector('#cart-table tbody');
    const totalPriceSpan = document.getElementById('total-price');

    console.log('Displaying cart items:', cartItems);

    if (!cartTableBody) {
        console.error('Cart table body not found in DOM.');
        return;
    }
    if (!totalPriceSpan) {
        console.error('Total price span not found in DOM.');
        return;
    }

    cartTableBody.innerHTML = '';
    let totalPrice = 0;
    cartItems.forEach((item) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.car_id}</td>
            <td>${item.make}</td>
            <td>${item.model}</td>
            <td>${item.year}</td>
            <td>${item.cost}</td>
            <td><button class="remove-from-cart" data-car-id="${item.car_id}">Remove</button></td>
        `;
        cartTableBody.appendChild(row);
        totalPrice += item.cost;
    });

    totalPriceSpan.textContent = totalPrice.toFixed(2);

    const removeButtons = document.querySelectorAll('.remove-from-cart');
    removeButtons.forEach(button => {
        button.addEventListener('click', removeFromCart);
    });
}

function removeFromCart(event){
    const car_id = event.target.getAttribute('data-car-id');

    fetch(`http://localhost:8081/cart/${car_id}`, {
        method: 'DELETE',
    })
        .then(response => response.json())
        .then(data => {
            alert(`Car with ${car_id} has been removed from your cart.`);
            fetchCartData();
        })
        .catch(error => {
            console.error('Error removing car from cart:', error);
            alert('Failed to remove car from cart.');
        });

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