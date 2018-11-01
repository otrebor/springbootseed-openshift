package com.company.example.springbootseed.errorhandling.utilities;

import org.springframework.web.bind.MethodArgumentNotValidException;

public interface IMethodArgumentNotValidExceptionToApiErrorConverter extends IExceptionToApiErrorConverter<MethodArgumentNotValidException>{
}
