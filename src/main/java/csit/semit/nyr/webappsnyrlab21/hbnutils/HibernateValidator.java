package csit.semit.nyr.webappsnyrlab21.hbnutils;

import csit.semit.nyr.webappsnyrlab21.entity.Client;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class HibernateValidator {

    public static String validateClient(Client client) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        if (violations.isEmpty()) {
            return "Client data is valid.";
        } else {
            StringBuilder errorMessages = new StringBuilder();
            for (ConstraintViolation<Client> violation : violations) {
                errorMessages.append(violation.getMessage()).append("\n");
            }
            return errorMessages.toString();
        }
    }
}
