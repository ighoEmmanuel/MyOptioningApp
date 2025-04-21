package semicolon.africa.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semicolon.africa.dtos.reposonse.LogInResponse;
import semicolon.africa.dtos.request.LoginDto;
import semicolon.africa.service.AuthLoginService;
import semicolon.africa.service.AuthLoginServiceImp;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthLoginService authenticationService;

    public AuthenticationController(AuthLoginServiceImp authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    public LogInResponse login(@Valid @RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }


}
