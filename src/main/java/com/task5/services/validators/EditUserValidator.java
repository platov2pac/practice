package com.task5.services.validators;

import com.task5.dto.User;
import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.SQLException;

@Service
public class EditUserValidator implements Validator {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "dob.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "roles.empty");
        User editableUser = (User) o;
        try {

            if (userService.findByLogin(editableUser.getLogin()) != null) {
                errors.rejectValue("login", "login.exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
