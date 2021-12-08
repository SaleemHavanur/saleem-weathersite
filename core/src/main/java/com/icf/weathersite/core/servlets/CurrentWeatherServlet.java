/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.icf.weathersite.core.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
import com.icf.weathersite.core.data.ApiError;
import com.icf.weathersite.core.data.ApiResponse;
import com.icf.weathersite.core.service.WeatherService;
import org.apache.http.HttpStatus;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=/app/weather/api/v1/currentweather"
})
@ServiceDescription("Servlet for getting the current Weather for a city by name")
public class CurrentWeatherServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(CurrentWeatherServlet.class.getName());

    @Reference
    WeatherService weatherService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
        String cityName = req.getParameter("q");


//        ApiResponse apiResponse= weatherService.getCurrentWeather(cityName);
        ObjectMapper mapper = new ObjectMapper();
        logger.info("calling weatherservice for current weather for city : {}", cityName);
        ApiResponse apiResponse = weatherService.getCurrentWeather(cityName);
        logger.debug("got api response for current weather for city : {} \n {}", cityName, apiResponse);
        if(apiResponse != null){
            logger.info("writing response to api call for current weather for city : {}", cityName);
            resp.getWriter().write(mapper.writeValueAsString(apiResponse));
//            resp.getWriter().write(new Gson().toJson(apiResponse));
        }else{
            logger.error("received null for current weather for city : {}", cityName);
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


