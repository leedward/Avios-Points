package com.iagl.aviospoints.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

// The @Data annotation is a convenience shortcut annotation
// that bundles the features of @ToString, @EqualsAndHashCode,
// @Getter / @Setter and @RequiredArgsConstructor together.
@Data
public class AviosRequest {

    // The @ApiModelProperty annotation is used in the Model property to add some description to the Swagger output for that model attribute.
    // It also provides additional options like position, allowableValues, access, notes, etc.
    @ApiModelProperty(value = "Departure airport code", required = true)
    // The @NotBlank annotation validates that the annotated string is not null and the trimmed length is greater than zero.
    @NotBlank(message = "airport code cannot be empty")
    private String departureAirportCode;

    @ApiModelProperty(value = "Arrival airport code", required = true)
    @NotBlank(message = "airport code cannot be empty")
    private String arrivalAirportCode;

    // This property is not mandatory in a request, as defined by the optional constraint
    @ApiModelProperty(value = "Cabin code", allowableValues = "M, W, J, F")
    private String cabinCode;

}
