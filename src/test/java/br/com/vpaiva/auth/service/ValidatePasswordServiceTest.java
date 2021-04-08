package br.com.vpaiva.auth.service;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidatePasswordServiceTest {

  private static final int MIN_SIZE_CHARACTERS_VALID = 9;

  @Spy
  ValidatePasswordServiceImpl validatePasswordService =
      new ValidatePasswordServiceImpl(
          ValidatePasswordServiceTest.MIN_SIZE_CHARACTERS_VALID);


  @Test
  void testIsnull() {
    Assertions.assertFalse(this.validatePasswordService.isNull("aa"));
    Assertions.assertTrue(this.validatePasswordService.isNull(null));
  }

  @Test
  void testIsMinSizeCharactersValid() {
    Assertions
        .assertFalse(this.validatePasswordService.isMinSizeCharacters("aa"));
    Assertions.assertTrue(
        this.validatePasswordService.isMinSizeCharacters("123456789"));
  }

  @Test
  void testContainsDigit() {
    Assertions.assertFalse(this.validatePasswordService.containsDigit("a"));
    Assertions.assertTrue(this.validatePasswordService.containsDigit("1"));
  }

  @Test
  void testContainsUpperCaseLetter() {
    Assertions
        .assertFalse(this.validatePasswordService.containsUpperCaseLetter("a"));
    Assertions
        .assertTrue(this.validatePasswordService.containsUpperCaseLetter("A"));
  }

  @Test
  void testContainsLowerCaseLetter() {
    Assertions
        .assertFalse(this.validatePasswordService.containsLowerCaseLetter("A"));
    Assertions
        .assertTrue(this.validatePasswordService.containsLowerCaseLetter("a"));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "+"})
  void testContainsSpecialCharacter(String value) {
    Assertions.assertTrue(
        this.validatePasswordService.containsSpecialCharacter(value));
  }

  @Test
  void testContainsSpecialCharacterFalse() {
    Assertions.assertFalse(
        this.validatePasswordService.containsSpecialCharacter("a"));
  }

  @Test
  void testContainsSpaceCharacter() {
    Assertions
        .assertFalse(this.validatePasswordService.containsSpaceCharacter("a"));
    Assertions
        .assertTrue(this.validatePasswordService.containsSpaceCharacter(" "));
  }

  @Test
  void testContainsDuplicateCharacter() {
    Assertions.assertFalse(
        this.validatePasswordService.containsDuplicateCharacter("abc"));
    Assertions.assertTrue(
        this.validatePasswordService.containsDuplicateCharacter("aabc"));
  }

  @Test
  void testIsValid() {
    Assertions.assertTrue(this.validatePasswordService.isValid("AbTp9!fok"));
  }

  @ParameterizedTest
  @MethodSource("invalidPasswordStrings")
  void testIsValidFalse(String password) {
    Assertions.assertFalse(this.validatePasswordService.isValid(password));
  }

  static Stream<String> invalidPasswordStrings() {
    return Stream.of(null, "", "aa", "ab", "AAAbbbCc", "AbTp9!foo",
        "8AbTp9!foo", "AbTp9!foA", "AbTp9 fok");
  }

}
