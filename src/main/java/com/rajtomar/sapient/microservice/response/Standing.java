package com.rajtomar.sapient.microservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Standing extends League {

    String teamId;
    String teamName;
    @JsonProperty("overall_league_position")
    String position;

}
