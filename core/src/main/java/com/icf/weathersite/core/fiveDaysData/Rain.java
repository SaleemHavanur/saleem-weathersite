
package com.icf.weathersite.core.fiveDaysData;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "3h"
})
@Generated("jsonschema2pojo")
public class Rain {

    @JsonProperty("3h")
    private Double _3h;

    @JsonProperty("3h")
    public Double get3h() {
        return _3h;
    }

    @JsonProperty("3h")
    public void set3h(Double _3h) {
        this._3h = _3h;
    }

}
