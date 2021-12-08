package com.icf.weathersite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import javax.inject.Inject;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CityComponent {

    @Inject
    private String cityname;
    @Inject
    private String minwaterlevel;
    @Inject
    private String maxwaterlevel;
    public String getCityname() {
        return cityname;
    }

    public String getMinwaterlevel() {
        return minwaterlevel;
    }

    public String getMaxwaterlevel() {
        return maxwaterlevel;
    }
}