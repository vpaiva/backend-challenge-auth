package br.com.vpaiva.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

public class AuthResponseDto implements Serializable {

  private static final long serialVersionUID = 4039832438166571408L;

  @Schema(required = true, description = "Password valida.")
  private final Boolean valid;

  public AuthResponseDto(Boolean valid) {
    this.valid = valid;
  }

  public Boolean getValid() {
    return this.valid;
  }

}
