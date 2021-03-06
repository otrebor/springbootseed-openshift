package com.company.example.springbootseed.core.errorhandling.utilities;

import com.company.example.springbootseed.core.errorhandling.domain.ApiError;

public interface IExceptionToApiErrorConverter<T extends Exception> {

    ApiError convert(T ex);
}
