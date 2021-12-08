$(document).ready(function () {
        var latitude = '',
            longitude = '';

        $("#lat").change(function () {
            var validate = Validate("lat");
            $("#mes").html(validate);
            latitude = $(this).val();
            if (validate.length == 0 && $('#lon').val() != null) {

               getLatLong();
            }
        });

        $("#lon").change(function () {
            var validate = Validate("lon");
            $("#mes").html(validate);
            longitude = $(this).val();
            if (validate.length == 0 && $('#lat').val() != null) {

               getLatLong();
            }
        });

        var getLatLong = function(){
            const servletUrl = "/app/weather/api/v1/onecall";
            $.ajax({
                type: "GET",
                 url: servletUrl,
                 data: {lat: latitude, lon: longitude},
                dataType: "json",
                success: function (result, status, xhr) {
                    var humidity = result.current && result.current.humidity ? result.current.humidity : "Not Available";
                    var pressure = result.current && result.current.pressure ? result.current.pressure : "Not Available";
                    var sunrise = result.current && result.current.sunrise ? result.current.sunrise : "Not Available";
                    var sunset = result.current && result.current.sunset ? result.current.sunset : "Not Available";
                    var temp = result.current && result.current.temp ? result.current.temp : "Not Available";

                    $('#pressure').val("Pressure : "+ pressure);
                    $('#humiditylatlon').val("Humidity : "+ humidity);

                    $('#sunriselatlon').val("Sunrise  : " + sunrise);
                    $('#sunsetlatlon').val("Sun Set  : " + sunset);
                    $('#temp').val("Temperature in F : "+ temp);

                },
                error: function (xhr, status, error) {
                    console.log("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText)
                }
            });
        }

//function getState(countryId)
//{
//   var strURL="findState.php?country="+countryId;
//   var req = getXMLHTTP();
//   if (req)
//   {
//     req.onreadystatechange = function()
//     {
//      if (req.readyState == 4)
//      {
//     // only if "OK"
//     if (req.status == 200)
//         {
//        document.getElementById('statediv').innerHTML=req.responseText;
//     } else {
//       alert("There was a problem while using XMLHTTP:\n" + req.statusText);
//     }
//       }
//      }
//   req.open("GET", strURL, true);
//   req.send(null);
//   }
//}
    function Validate(type) {
        var errorMessage = "";
        if (type == "lat" && $("#lat").val() == null) {
            errorMessage = "Latitude is Incorrect";
        }
        if (type == "lon" && $("#lon").val() == null) {
            errorMessage = "Longitude is Incorrect";
        }
        return errorMessage;
    }
    });