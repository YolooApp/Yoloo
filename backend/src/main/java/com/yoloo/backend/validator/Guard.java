package com.yoloo.backend.validator;

import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.api.server.spi.response.NotFoundException;
import javax.annotation.Nullable;

public final class Guard {

  public static <T> T checkNotFound(T reference, @Nullable Object errorMessage)
      throws NotFoundException {
    if (reference == null) {
      throw new NotFoundException(String.valueOf(errorMessage));
    }
    return reference;
  }

  public static <T> T checkBadRequest(T reference, @Nullable Object errorMessage)
      throws BadRequestException {
    if (reference == null) {
      throw new BadRequestException(String.valueOf(errorMessage));
    }
    return reference;
  }

  public static void checkForbiddenRequest(boolean expression, @Nullable Object errorMessage)
      throws ForbiddenException {
    if (!expression) {
      throw new ForbiddenException(String.valueOf(errorMessage));
    }
  }
}