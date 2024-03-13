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
$(document).ready(function() {
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
$(document).ready(function() {
    $('#editEyewearModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
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
/*    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.push(eyewearId);
    localStorage.setItem('cart', JSON.stringify(cart));
    console.log("Cart:", cart);
    updateCartCount();*/
}


