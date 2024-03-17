/*SECTIONS; function that will show the selected section and hide the other sections*/
function showSection(sectionId) {

    console.log("Showing section:", sectionId);
    const sections = document.querySelectorAll('section');
    for (const section of sections) {
        console.log("Checking section:", section.id);
        if (section.id === sectionId) {
            console.log("Showing:", section.id);
            section.style.display = 'block';
        } else {
            console.log("Hiding:", section.id);
            section.style.display = 'none';
        }
    }
}

// showCategoryProducts function
function showCategoryProducts(categoryId) {
    document.querySelectorAll('.product-card').forEach(card => {
        card.style.display = card.getAttribute('data-category-id') === categoryId ? 'block' : 'none';
    });

    const categoryName = document.querySelector(`[data-category-id="${categoryId}"] p`).textContent;
    document.getElementById('productsHeader').textContent = categoryName;
}

function showProductsForCategory(categoryId) {
    showSection('products');
    showCategoryProducts(categoryId);
}


// edit category modal helper
$(document).ready(function () {
    $('#editCategoryModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var categoryId = button.data('category-id');
        var categoryName = button.data('category-name');
        var categoryDescription = button.data('category-description');

        var modal = $(this);
        modal.find('#editCategoryId').val(categoryId);
        modal.find('#editCategoryName').val(categoryName);
        modal.find('#editCategoryDescription').val(categoryDescription);
    });
});


// edit eyewear modal helper
$(document).ready(function () {
    $('#editEyewearModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var eyewearId = button.attr('data-eyewear-id');
        var eyewearName = button.attr('data-eyewear-name');
        var manufacturerId = button.attr('data-eyewear-manufacturer-id');
        var brandId = button.attr('data-eyewear-brand-id');
        var categoryId = button.attr('data-eyewear-category-id');
        var description = button.attr('data-eyewear-description');
        var price = button.attr('data-eyewear-price');
        var stockQuantity = button.attr('data-eyewear-stock-quantity');

        var modal = $(this);
        modal.find('#editEyewearId').val(eyewearId);
        modal.find('#editManufacturerId').val(manufacturerId);
        modal.find('#editEyewearName').val(eyewearName);
        modal.find('#editEyewearManufacturer').val(manufacturerId);
        modal.find('#editEyewearBrand').val(brandId);
        modal.find('#editEyewearCategory').val(categoryId);
        modal.find('#editEyewearDescription').val(description);
        modal.find('#editEyewearPrice').val(price);
        modal.find('#editEyewearStockQuantity').val(stockQuantity);
    });
});

// add new eyeware in the cart
function addEyewearToCart(eyewearId) {
    console.log("Adding eyewear to cart:", eyewearId);
    let quantity = 1;

    fetch(`/cart/add-to-cart/${eyewearId}?quantity=${quantity}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                console.log('Eyewear added to cart successfully.');
                updateCartCount(data.cartItemCount);
            } else {
                console.error('Failed to add eyewear to cart.');
            }
        })
        .catch(error => console.error('Error adding eyewear to cart:', error));
}

// remove item from cart
function removeItem(itemIndex) {
    console.log("Attempting to remove item at index:", itemIndex);
    performFetch(`/cart/remove-from-cart/${itemIndex}`, 'POST')
        .then(data => {
            console.log('Item removed successfully.');
            updateCartCount(data.cartItemCount);
            location.reload();
        })
        .catch(error => console.error('Error removing item from cart:', error));
}


// update quantity of eyewear in the cart
function updateQuantity(index, eyewearId) {
    const quantityInput = document.getElementById('qty-' + index);
    const newQuantity = parseInt(quantityInput.value);
    performFetch(`/cart/update-quantity/${eyewearId}`, 'POST', 'quantity=' + newQuantity)
        .then(data => {
            console.log('Quantity updated successfully.');
            document.getElementById('total-' + index).textContent = data.newTotal;
            updateCartCount(data.cartItemCount);
            location.reload();
        })
        .catch(error => console.error('Error updating quantity:', error));
}

// fetch function
function performFetch(url, method, body = null) {
    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
        'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value,
    };
    const fetchConfig = {
        method: method,
        headers: headers,
    };
    if (body) {
        fetchConfig.body = body;
    }

    return fetch(url, fetchConfig)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        });
}


// update cart count UI on index.html
function updateCartCount(newCount) {
    const cartCountElement = document.getElementById('cartItemCount');
    if (cartCountElement) {
        cartCountElement.textContent = newCount;
    } else {
        console.error("Cart item count element not found.");
    }
}






