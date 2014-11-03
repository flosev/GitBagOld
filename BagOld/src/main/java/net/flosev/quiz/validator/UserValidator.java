package net.flosev.quiz.validator;

import net.flosev.quiz.entity.User;

import java.util.Map;

public interface UserValidator extends Validator<User> {
    @Override
    public Map<String, String> validate(User entity);
}
