// Initialize and add the map
function initMap() {
    $(document).ready(function() {
        $.ajax({
            type: 'GET',
            url: 'sightings',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (sightings) {
                var sighting, contentString, infoWindow, sightingPosition, marker, sightingTitle;

                for (var i = 0; i < sightings.length; i++) {
                    sighting = sightings[i];
                    
                    contentString = '<div id="content">' + 
                            '<p><strong>Sighting at ' + 
                            sighting.location.name + '</strong></p>' + 
                            '<p><strong>Date:</strong> ' + 
                            sighting.dateTime.monthValue + '/' +
                            sighting.dateTime.dayOfMonth + '/' +
                            sighting.dateTime.year + '</p>' + 
                            '<p><a href="displaySightingDetails?sightingId=' + 
                            sighting.sightingId +
                            '">View details</a></p>' + 
                            '</div>';
                    
                    infoWindow = new google.maps.InfoWindow({
                        content: contentString
                    });
                    
                    sightingPosition = {
                        lat: sighting.location.latitude,
                        lng: sighting.location.longitude
                    };
                    
                    sightingTitle = sighting.location.name;
                    
                    marker = new google.maps.Marker({
                        position: sightingPosition, 
                        map: map,
                        title: sightingTitle
                    });
                    
                    google.maps.event.addListener(marker, 'click', (function(marker, infoWindow){ 
                        return function() {
                            infoWindow.open(map, marker);
                        };
                    })(marker, infoWindow));
                }
            },
            error: function () {
                alert('Error calling web service. Please try again later.');
            }
        });
    });
    
    // The location of New York City
    var nyc = {lat: 40.713, lng: -74.006};
    // The map, centered at New York City
    var map = new google.maps.Map(document.getElementById('map'), {zoom: 5, center: nyc});
}