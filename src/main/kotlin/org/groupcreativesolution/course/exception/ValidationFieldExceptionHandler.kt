package org.groupcreativesolution.course.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidationFieldExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerFieldValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "")
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }
}