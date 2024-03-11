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