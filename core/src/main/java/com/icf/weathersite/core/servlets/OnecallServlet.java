package com.icf.weathersite.core.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icf.weathersite.core.data.ApiError;
import com.icf.weathersite.core.dataHourly.ApiHourlyResponse;
import com.icf.weathersite.core.service.WeatherService;
import org.apache.http.HttpStatus;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=/app/weather/api/v1/onecall"
})
public class OnecallServlet extends SlingSafeMethodsServlet  {




        private static final long serialVersionUID = 1L;
        private Logger logger = LoggerFactory.getLogger(CurrentWeatherServlet.class.getName());

        @Reference
        WeatherService weatherService;

        @Override
        protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
//            String cityName = req.getParameter("q");
               double lat= Double.parseDouble(req.getParameter("lat"));
               double lon= Double.parseDouble(req.getParameter("lon"));
               String exclude=req.getParameter("exclude");


//        ApiResponse apiResponse= weatherService.getCurrentWeather(cityName);
            ObjectMapper mapper = new ObjectMapper();
            logger.info("calling weatherservice for current weather for Latitude : {}", lat);
            ApiHourlyResponse apiResponse = weatherService.getOneCallApi(lat,lon,exclude);
            logger.debug("got api response for current weather for city : {} \n {}", lat,lon,exclude, apiResponse);
            if(apiResponse != null){
                logger.info("writing response to api call for current weather for city : {}", lat,lon,exclude);
                resp.getWriter().write(mapper.writeValueAsString(apiResponse));
//            resp.getWriter().write(new Gson().toJson(apiResponse));
            }else{
                logger.error("received null for current weather for city : {}", lat,lon,exclude);
                //set the response code
                resp.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                //construct ApiError object
                ApiError apiError = new ApiError(HttpStatus.SC_INTERNAL_SERVER_ERROR, "There was an internal server error");
                //write the error response to the request
                resp.getWriter().write(mapper.writeValueAsString(apiError));
//            resp.getWriter().write(new Gson().toJson(apiResponse));
            }
        }
}
