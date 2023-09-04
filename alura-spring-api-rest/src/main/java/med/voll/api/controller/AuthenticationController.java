package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.authentication.dto.AuthenticateLogin;
import med.voll.api.authentication.dto.DetailsTokeJWT;
import med.voll.api.model.user.User;
import med.voll.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticateLogin data) {
        var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DetailsTokeJWT(tokenJWT));
    }
}
