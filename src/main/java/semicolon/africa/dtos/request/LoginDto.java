package semicolon.africa.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
final public class LoginDto {
    @NotBlank(message = "Email is required")
    @Email(message = "invalid email type")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 10, message = "Password must be at least 4 characters and maximum of 10")
    private String password;
}