$(document).ready(function () {
        $("#city").change(function () {
            var validate = Validate();
            $("#caste").html(validate);
            if (validate.length == 0) {
                const servletUrl = "/bin/weather/api/v1/weatherfivedays";
                $.ajax({
                    type: "GET",
                     url: servletUrl,
                     data: {q: $(this).val()},
                    dataType: "json",
                    success: function (result, status, xhr) {


////                        $('#country').val("country: " + result["city"]["country"]);
//                        $('#population').val("population : "+ result["city"]["population"]);
//                        $('#timezone').val("timezone : "+ result["city"]["timezone"]);
//                        $('#sunrise').val("sunrise : "+ result["city"]["sunrise"]);
//                        $('#sunset').val("sunset : "+ result["city"]["sunset"]);


                        $('#sunrise').val("Sunrise for day 1: " + result.city.sunrise);
                        $('#sunset').val("Sunset  for day 1: " + result.city.sunset);
                        $('#tempmin').val("Min Temperature  : " + result.list[0].main.temp_min+"°C");
                        $('#tempmax').val("Max Temperature  : " + result.list[0].main.temp_max+"°C");



                        $('#sunrise2').val("Sunrise for day 2: " + result.city.sunrise);
                        $('#sunset2').val("Sunset  for day 2: " + result.city.sunset);
                        $('#tempmin2').val("Min Temperature  : " + result.list[0].main.temp_min+"°C");
                        $('#tempmax2').val("Max Temperature  : " + result.list[0].main.temp_max+"°C");


                       $('#sunrise3').val("Sunrise for day 3: " + result.city.sunrise);
                       $('#sunset3').val("Sunset  for day 3: " + result.city.sunset);
                       $('#tempmin3').val("Min Temperature  : " + result.list[0].main.temp_min+"°C");
                       $('#tempmax3').val("Max Temperature  : " + result.list[0].main.temp_max+"°C");


                        $('#sunrise4').val("Sunrise for day 4: " + result.city.sunrise);
                        $('#sunset4').val("Sunset  for day 4: " + result.city.sunset);
                        $('#tempmin4').val("Min Temperature  : " + result.list[0].main.temp_min+"°C");
                        $('#tempmax4').val("Max Temperature  : " + result.list[0].main.temp_max+"°C");


                        $('#sunrise5').val("Sunrise for day 5: " + result.city.sunrise);
                        $('#sunset5').val("Sunset  for day 5: " + result.city.sunset);
                        $('#tempmin5').val("Min Temperature  : " + result.list[0].main.temp_min+"°C");
                        $('#tempmax5').val("Max Temperature  : " + result.list[0].main.temp_max+"°C");

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
        if ($("#city").val() == "Select") {
            errorMessage += "Select City";
        }
        return errorMessage;
    }
    });

