package br.com.vpaiva.auth.service;

import java.util.Objects;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("application")
public class ValidatePasswordServiceImpl implements ValidatePasswordService {

  private static final String SPECIAL_CHARACTER_REGEX = "[!@#$%^&*()\\-+]+";
  private static final String LOWERCASE_REGEX = "[a-z]+";
  private static final String UPPERCASE_REGEX = "[A-Z]+";
  private static final String DIGIT_REGEX = "[\\d]+";

  private final Integer minSizeCharacters;

  @Autowired
  public ValidatePasswordServiceImpl(
      @Value("${auth.minSizeCharacters}") Integer minSizeCharacters) {
    this.minSizeCharacters = minSizeCharacters;
  }

  @Override
  public Boolean isValid(String password) {
    return !this.isNull(password) && this.isMinSizeCharacters(password)
        && this.containsDigit(password)
        && this.containsUpperCaseLetter(password)
        && this.containsLowerCaseLetter(password)
        && this.containsSpecialCharacter(password)
        && !this.containsSpaceCharacter(password)
        && !this.containsDuplicateCharacter(password);
  }

  public boolean isNull(String value) {
    return Objects.isNull(value);
  }

  public boolean isMinSizeCharacters(String value) {
    return value.length() >= this.minSizeCharacters;
  }

  public boolean containsDigit(String value) {
    return this.findMatcher(ValidatePasswordServiceImpl.DIGIT_REGEX, value);
  }

  public boolean containsUpperCaseLetter(String value) {
    return this.findMatcher(ValidatePasswordServiceImpl.UPPERCASE_REGEX, value);
  }

  public boolean containsLowerCaseLetter(String value) {
    return this.findMatcher(ValidatePasswordServiceImpl.LOWERCASE_REGEX, value);
  }

  public boolean containsSpecialCharacter(String value) {
    return this.findMatcher(ValidatePasswordServiceImpl.SPECIAL_CHARACTER_REGEX,
        value);
  }

  public boolean containsSpaceCharacter(String password) {
    return this.findMatcher("[\\s]+", password);
  }

  public boolean containsDuplicateCharacter(String password) {
    return password.chars().distinct().count() != password.length();
  }

  public boolean findMatcher(String matcher, String value) {
    final Pattern pattern = Pattern.compile(matcher);
    return pattern.matcher(value).find();
  }

}
