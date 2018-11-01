package com.company.example.springbootseed.controllers.annotations;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ApiResponses({ @ApiResponse(code = 201, message = "Creation successful") })
public @interface MethodWith201 {
}
