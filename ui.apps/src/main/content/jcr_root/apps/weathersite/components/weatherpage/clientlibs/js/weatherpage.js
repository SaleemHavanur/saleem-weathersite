$(document).ready(function () {
        $("#citySelect").change(function () {
            var validate = Validate();
            $("#message").html(validate);
            if (validate.length == 0) {
                const servletUrl = "/app/weather/api/v1/currentweather";
                $.ajax({
                    type: "GET",
                     url: servletUrl,
                     data: {q: $(this).val()},
                    dataType: "json",
                    success: function (result, status, xhr) {


                        $('#currentTemp').val("Current Temperature : " + result["main"]["temp"]+"°C");
                        $('#maxTemp').val("Maximum Temperature : "+ result["main"]["temp_min"]+"°C");
                        $('#minTemp').val("Minimum Temperature : "+ result["main"]["temp_max"]+"°C");
                        $('#humidity').val("Humidity : "+ result["main"]["humidity"]);


                    },
                    error: function (xhr, status, error) {
                        console.log("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText)
                    }
                });
            }
        });

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

    function Validate() {
        var errorMessage = "";
        if ($("#citySelect").val() == "Select") {
            errorMessage += "Select City";
        }
        return errorMessage;
    }
    });

