package onlinejavafun.constraints;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class ValidationCustomLimitsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ValidationCustomLimitsApplication.class, args);
        Validator validator = context.getBean(Validator.class);
        Person person = new Person(
                null,
                "Górski",
                "83062674899",
                "lukasz@gorski.pl",
                "123",
                "https://toja.pl",
                LocalDate.of(2030, 2, 3)
        );
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        if (constraintViolations.isEmpty()) {
            System.out.println("Dane osoby są poprawne");
        } else {
            System.out.println("Obiekt nieprawidowy. Złamane ograniczenia:");
            for (ConstraintViolation<Person> violation : constraintViolations) {
                System.out.printf(" > %s %s (%s)\n",
                        violation.getPropertyPath(),
                        violation.getMessage(),
                        violation.getInvalidValue()
                        );
            }
        }
    }

}
