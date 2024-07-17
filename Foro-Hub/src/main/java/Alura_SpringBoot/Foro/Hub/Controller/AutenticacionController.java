package Alura_SpringBoot.Foro.Hub.Controller;

import Alura_SpringBoot.Foro.Hub.domain.Usuario.AutentificarUsuario;
import Alura_SpringBoot.Foro.Hub.domain.Usuario.DatosUsuario;
import Alura_SpringBoot.Foro.Hub.domain.Usuario.Usuario;
import Alura_SpringBoot.Foro.Hub.infra.security.DatosTokenJWT;
import Alura_SpringBoot.Foro.Hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity  realizarLogin(@RequestBody @Valid AutentificarUsuario datosUsuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosUsuario.email(), datosUsuario.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}

