package br.com.vpaiva.auth.web;

import br.com.vpaiva.auth.dto.AuthRequestDto;
import br.com.vpaiva.auth.dto.AuthResponseDto;
import br.com.vpaiva.auth.service.ValidatePasswordService;
import br.com.vpaiva.auth.service.ValidatePasswordServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

  @Spy
  ValidatePasswordService validatePasswordService =
      new ValidatePasswordServiceImpl(9);

  @Spy
  AuthController authController =
      new AuthController(this.validatePasswordService);

  @Test
  void testIsValid() {
    ResponseEntity<AuthResponseDto> responseEntity =
        this.authController.auth(new AuthRequestDto("AbTp9!fok"));
    Assertions.assertTrue(responseEntity.getBody().getValid());

    responseEntity = this.authController.auth(new AuthRequestDto());
    Assertions.assertFalse(responseEntity.getBody().getValid());

    final AuthRequestDto authRequestDto = new AuthRequestDto();
    authRequestDto.setPassword("AbTp9!fok");
    responseEntity = this.authController.auth(authRequestDto);
    Assertions.assertTrue(responseEntity.getBody().getValid());
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "aa", "ab", "AAAbbbCc", "AbTp9!foo", "AbTp9!foA",
      "AbTp9 fok"})
  void testIsValidFalse(String password) {
    final ResponseEntity<AuthResponseDto> responseEntity =
        this.authController.auth(new AuthRequestDto(password));
    Assertions.assertFalse(responseEntity.getBody().getValid());
  }
}
