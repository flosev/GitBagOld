package net.flosev.quiz.validator;

import net.flosev.quiz.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserValidatorImpl implements UserValidator {

    @Override
    public Map<String, String> validate(User user) {
        Map<String, String> errorMap = new HashMap<>();
        validateLogin(user.getLogin(), errorMap);
        validatePassword(user.getPassword(), errorMap);
        validateEmail(user.getEmail(), errorMap);
        return errorMap;
    }

    private void validateLogin(String login, Map<String, String> errorMap) {
        if (login == null) {
            errorMap.put("login", "login == null");
        } else if (login.length() < 3) {
            errorMap.put("login", "login.length() < 3");
        } else if (login.length() > 10) {
            errorMap.put("login", "login.length() > 10");
        }
    }

    private void validatePassword(String password, Map<String, String> errorMap) {
        if (password == null) {
            errorMap.put("password", "password == null");
        } else if (password.length() < 3) {
            errorMap.put("password", "password.length() < 3");
        } else if (password.length() > 10) {
            errorMap.put("password", "password.length() > 10");
        }
    }

    private void validateEmail(String email, Map<String, String> errorMap) {
        if (email == null) {
            errorMap.put("email", "email == null");
        } else if (email.length() < 3) {
            errorMap.put("email", "email.length() < 3");
        } else if (email.length() > 10) {
            errorMap.put("email", "email.length() > 10");
        }
    }
}
