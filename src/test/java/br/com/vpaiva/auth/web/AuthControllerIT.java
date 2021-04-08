package br.com.vpaiva.auth.web;

import br.com.vpaiva.auth.dto.AuthRequestDto;
import br.com.vpaiva.auth.service.ValidatePasswordServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = AuthController.class,
    properties = "auth.minSizeCharacters=9")
@Import(ValidatePasswordServiceImpl.class)
class AuthControllerIT {

  @Autowired
  private MockMvc mvc;

  @Test
  void testAuth() throws Exception {
    this.mvc
        .perform(MockMvcRequestBuilders.post("/api/v1/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                AuthControllerIT.toJsonMapper(new AuthRequestDto("AbTp9!fok")))
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.valid",
            CoreMatchers.equalTo(true)));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "aa", "ab", "AAAbbbCc", "AbTp9!foo", "AbTp9!foA",
      "AbTp9 fok"})
  void testAuthFalse(String password) throws Exception {
    this.mvc
        .perform(MockMvcRequestBuilders.post("/api/v1/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                AuthControllerIT.toJsonMapper(new AuthRequestDto(password)))
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.valid",
            CoreMatchers.equalTo(false)));
  }

  public static String toJsonMapper(Object obj) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(obj);
  }
}
