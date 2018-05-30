package io.cloudtrust.shadowgroupsimulator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "bad request")
public class BadRequestException extends RuntimeException {
}
