/*SECTIONS; function that will show the selected section and hide the other sections*/
function showSection(sectionId) {

/*
    if (sectionId === 'energy')
    {
        energyDataModal.style.display = 'block';
    } else
    {
        energyDataModal.style.display = 'none';
    }
*/

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
    // TODO: Show only the products for the selected category
    console.log("Showing products for category:", categoryId);
    const products = document.querySelectorAll('.product');
    for (const product of products) {
        console.log("Checking product:", product.id);
        if (product.id === categoryId) {
            console.log("Showing:", product.id);
            product.style.display = 'block';
        } else {
            console.log("Hiding:", product.id);
            product.style.display = 'none';
        }
    }
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




