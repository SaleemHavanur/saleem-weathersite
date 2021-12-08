package com.icf.weathersite.core.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icf.weathersite.core.data.ApiError;
import com.icf.weathersite.core.fiveDaysData.ApiResponseFiveDays;
import com.icf.weathersite.core.service.WeatherService;
import org.apache.http.HttpStatus;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=/bin/weather/api/v1/weatherfivedays"
})
@ServiceDescription("Servlet for getting the current Weather for a city by name")
public class ForecastFor5DaysServlet extends SlingSafeMethodsServlet{
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(CurrentWeatherServlet.class.getName());

    @Reference
    WeatherService weatherService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
        String name = req.getParameter("q");

        ObjectMapper mapper = new ObjectMapper();
        logger.info("calling weatherservice for current weather for city : {}", name);
        ApiResponseFiveDays apiResponseFiveDays = weatherService.getDailyForecastFor5Days(name);
        logger.debug("got api response for current weather for city : {} \n {}", name, apiResponseFiveDays);
        if(apiResponseFiveDays != null){
            logger.info("writing response to api call for current weather for city : {}", name);
            resp.getWriter().write(mapper.writeValueAsString(apiResponseFiveDays));
        }else{
            logger.error("received null for current weather for city : {}", name);
            //set the response code
            resp.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            //construct ApiError object
            ApiError apiError = new ApiError(HttpStatus.SC_INTERNAL_SERVER_ERROR, "There was an internal server error");
            //write the error response to the request
            resp.getWriter().write(mapper.writeValueAsString(apiError));
        }
    }
}