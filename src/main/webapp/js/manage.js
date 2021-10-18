mapboxgl.accessToken = 'pk.eyJ1IjoicGhhbXRydW5naGlldTZkIiwiYSI6ImNrdGJuZHduNzF4aTYyd3Bsa3RyMGxhY3IifQ.jzhAQ1H3SkdOoJi-BYKI8A';
const map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v11',
    center: [105.8433329795298, 21.010965695688427],
    zoom: 2
});

const marker = new mapboxgl.Marker({
    draggable: true,
    color: '#F84C4C'
})
    .setLngLat([ 105.8433329795298, 21.010965695688427])
    .addTo(map);

function onDragEnd() {
    const lngLat = marker.getLngLat();
    document.getElementById("lng").value = lngLat.lng;
    document.getElementById("lat").value = lngLat.lat;
}

marker.on('dragend', onDragEnd);