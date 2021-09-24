
function loadCarBrands() {
    $.ajax({
        type: 'GET',
        url: 'car-brands',
        dataType: 'json'
    }).done(function (data) {
        let selectElement = document.getElementById('selectBrand')
        for(let carBrand of data) {
            selectElement.add(new Option(carBrand.name, carBrand.id))
        }
    })
}