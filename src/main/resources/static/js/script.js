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