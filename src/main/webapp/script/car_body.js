
function loadCarBodies() {
    $.ajax({
        type: 'GET',
        url: 'car_bodies',
        dataType: 'json'
    }).done(function (data) {
        let selectElement = document.getElementById('selectBody')
        for(let carBody of data) {
            selectElement.add(new Option(carBody.name, carBody.id))
        }
    })
}