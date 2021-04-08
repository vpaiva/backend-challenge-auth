package br.com.vpaiva.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

public class AuthRequestDto implements Serializable {

  private static final long serialVersionUID = -5202182840183762849L;

  @Schema(required = true, description = "\n" + "    Nove ou mais caracteres\n"
      + "    Ao menos 1 dígito\n" + "    Ao menos 1 letra minúscula\n"
      + "    Ao menos 1 letra maiúscula\n"
      + "    Ao menos 1 caractere especial\n"
      + "        Considere como especial os seguintes caracteres: !@#$%^&*()-+\n"
      + "    Não possuir caracteres repetidos dentro do conjunto\n" + "")
  private String password;

  public AuthRequestDto() {}

  public AuthRequestDto(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
