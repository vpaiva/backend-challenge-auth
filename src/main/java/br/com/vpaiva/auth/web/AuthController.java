package br.com.vpaiva.auth.web;

import br.com.vpaiva.auth.dto.AuthRequestDto;
import br.com.vpaiva.auth.dto.AuthResponseDto;
import br.com.vpaiva.auth.service.ValidatePasswordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class AuthController {

  private final ValidatePasswordService validatePasswordService;

  @Autowired
  public AuthController(ValidatePasswordService validatePasswordService) {
    this.validatePasswordService = validatePasswordService;
  }

  @ApiOperation(value = "Verificar se senha é válida")
  @PostMapping("/auth")
  public ResponseEntity<AuthResponseDto> auth(
      @RequestBody AuthRequestDto authRequestDto) {
    final Boolean passwordValid =
        this.validatePasswordService.isValid(authRequestDto.getPassword());
    return ResponseEntity.ok(new AuthResponseDto(passwordValid));
  }
}
