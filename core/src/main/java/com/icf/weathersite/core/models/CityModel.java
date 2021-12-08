package com.icf.weathersite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import java.util.List;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CityModel {

    @ChildResource
    private List<CityComponent> undergroundwaterlevel;
    public List<CityComponent> getUndergroundwaterlevel() {
        return undergroundwaterlevel;
    }
}