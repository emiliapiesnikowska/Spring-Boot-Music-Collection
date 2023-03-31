import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/albums/')

    const name = document.getElementById('name').value;
    const artist = document.getElementById('artist').value;
    const releaseYear = document.getElementById('releaseYear').value;
    const rate = parseFloat(document.getElementById("length").value);

    const request = {
        'name': name,
        'artist': artist,
        'releaseYear': releaseYear,
        'length': length
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}