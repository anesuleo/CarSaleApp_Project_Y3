document.addEventListener('DOMContentLoaded', function () {

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

    function fetchDisplayData() {
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
                if (outputElement) {
                    outputElement.innerHTML = output;
                } else {
                    console.error('Output element not found');
                }

                const addButtons = document.querySelectorAll('.add-to-cart');
                addButtons.forEach(button => {
                    button.addEventListener('click', addToCart);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    function addToCart(event) {
        const button = event.target;
        const car = {
            cart_id: button.getAttribute('data-cart-id'),
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


    function fetchCartData() {
        fetch('http://localhost:8081/cart/allcart')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(cart => {
                console.log('Cart data:', cart);
                if (Array.isArray(cart) && cart.length > 0) {
                    displayCart(cart); // Pass the array directly to displayCart
                } else {
                    console.log('No cart items found:', cart);
                }
            })
            .catch(error => {
                console.error('Error fetching cart data:', error);
            });
    }


    function displayCart(cartItems) {
        const cartTableBody = document.getElementById('cart-table-body');
        const totalPriceSpan = document.getElementById('total-cost');
        let totalCost = 0;

        if (!cartTableBody) {
            console.error('Cart table body not found in DOM.');
            return;
        }
        if (!totalPriceSpan) {
            console.error('Total price span not found in DOM.');
            return;
        }

        cartTableBody.innerHTML = '';
        cartItems.forEach((cartItems) => {

            console.log('Item data:', cartItems);

            const cart_id = cartItems.cart_id || 'N/A'; // Fallback if property doesn't exist
            const car_id = cartItems.car_id || 'N/A'; // Fallback if property doesn't exist
            const make = cartItems.make || 'N/A';
            const model = cartItems.model || 'N/A';
            const year = cartItems.year || 'N/A';
            const cost = cartItems.cost || 0;

            totalCost += parseFloat(cost) || 0;

            const row = document.createElement('tr');
            row.innerHTML = `
            <td>${cart_id}</td>
            <td>${car_id}</td>
            <td>${make}</td>
            <td>${model}</td>
            <td>${year}</td>
            <td>${cost}</td>
            <td><button class="remove-btn" data-car-id="${car_id}">Remove</button></td>
        `;
            cartTableBody.appendChild(row);

            const removeButton = row.querySelector('.remove-btn');
            removeButton.addEventListener('click', () => {
                removeFromCart(cart_id, row, cost, totalPriceSpan);
            });
        });

        totalPriceSpan.textContent =`Total: $${totalCost.toFixed(2)}`;

}

function removeFromCart(cart_id, row, cost, totalPriceSpan) {
    //const car_id = event.target.getAttribute('data-car-id');

    if (!cart_id) {
        alert('Cart ID is missing. Cannot remove item.');
        return;
    }

    fetch(`http://localhost:8081/cart/${cart_id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            alert(`Car with cart ID ${cart_id} has been removed from your cart.`);
            row.remove();

            let currentTotal = parseFloat(totalPriceSpan.textContent.replace('Total: $', '')) || 0;
            currentTotal -= parseFloat(cost) || 0;
            totalPriceSpan.textContent = `Total: $${currentTotal.toFixed(2)}`;
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

    fetchDisplayData();
    fetchCartData();
});