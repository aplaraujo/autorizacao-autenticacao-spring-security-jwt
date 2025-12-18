package io.github.aplaraujo.services.models.validators;

import io.github.aplaraujo.entities.User;
import io.github.aplaraujo.services.models.dto.ResponseDTO;

public class UserValidator {
    public ResponseDTO validate(User user) {
        ResponseDTO response = new ResponseDTO();
        response.setNumOfErrors(0);

        if (user.getFirstName() == null || user.getFirstName().length() < 3 || user.getFirstName().length() > 15) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("This field should not be null, it should have from 3 to 15 characters");
        }

        if (user.getLastName() == null || user.getLastName().length() < 3 || user.getLastName().length() > 30) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("This field should not be null, it should have from 3 to 30 characters");
        }

        if (user.getEmail() == null || !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("This field is not valid");
        }

        if (user.getPassword() == null || !user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$")) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("The password should have from 8 to 16 characters, at least one number, a capital letter and a lowercase");
        }

        return response;
    }
}
