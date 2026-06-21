package com.basavaLearing.jornalApp.Pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WhetherReponseDTO {
	
    private Current current;
    @Getter
    @Setter
    public class Current{
    	@JsonProperty("observation_time") // this used to convert json element data especially (snake case ) to poja element ( camel case)
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("wind_degree")
        private int windDegree;
        @JsonProperty("wind_dir")
        private String windDir;
        private int pressure;
        @JsonProperty("is_day")
        private String isDay;
        private int feelslike;
    }
    

}


