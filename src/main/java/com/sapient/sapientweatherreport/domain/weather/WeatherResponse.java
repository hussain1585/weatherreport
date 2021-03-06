package com.sapient.sapientweatherreport.domain.weather;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hussain Akhtar Wahid - Sept-23-2020
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cod",
        "message",
        "cnt",
        "list",
        "city"
})
public class WeatherResponse
{

    @JsonProperty("cod")
    private String cod;
    @JsonProperty("message")
    private Double message;
    @JsonProperty("cnt")
    private Integer cnt;
    @JsonProperty("list")
    private java.util.List<List> list = null;
    @JsonProperty("city")
    private City city;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cod")
    public String getCod()
    {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(String cod)
    {
        this.cod = cod;
    }

    @JsonProperty("message")
    public Double getMessage()
    {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Double message)
    {
        this.message = message;
    }

    @JsonProperty("cnt")
    public Integer getCnt()
    {
        return cnt;
    }

    @JsonProperty("cnt")
    public void setCnt(Integer cnt)
    {
        this.cnt = cnt;
    }

    @JsonProperty("list")
    public java.util.List<List> getList()
    {
        return list;
    }

    @JsonProperty("list")
    public void setList(java.util.List<List> list)
    {
        this.list = list;
    }

    @JsonProperty("city")
    public City getCity()
    {
        return city;
    }

    @JsonProperty("city")
    public void setCity(City city)
    {
        this.city = city;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }

}