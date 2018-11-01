package com.company.example.springbootseed.errorhandling.utilities;

import com.company.example.springbootseed.errorhandling.domain.ApiError;

public interface IExceptionToApiErrorConverter<T extends Exception> {

    ApiError convert(T ex);
}
