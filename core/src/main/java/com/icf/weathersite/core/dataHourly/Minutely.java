
package com.icf.weathersite.core.dataHourly;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dt",
    "precipitation"
})
@Generated("jsonschema2pojo")
public class Minutely {

    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("precipitation")
    private Integer precipitation;

    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    @JsonProperty("precipitation")
    public Integer getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(Integer precipitation) {
        this.precipitation = precipitation;
    }

}
