package com.rajtomar.sapient.microservice.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Country {

    String countryId;
    String countryName;

}
