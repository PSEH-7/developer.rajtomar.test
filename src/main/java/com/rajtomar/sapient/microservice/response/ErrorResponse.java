package com.rajtomar.sapient.microservice.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

    String status;
    String message;
    int statusValue;

}
