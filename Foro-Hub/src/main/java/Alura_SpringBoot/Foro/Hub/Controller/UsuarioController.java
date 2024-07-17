package Alura_SpringBoot.Foro.Hub.Controller;

import Alura_SpringBoot.Foro.Hub.domain.Usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    ResponseEntity<DatosUsuario> registrarUsuario(@RequestBody @Valid DatosRegistrarUsuario datos){
        String encodedPassword = passwordEncoder.encode(datos.password());
        var usuarioNuevo = new Usuario(datos.nombre(),datos.email(),encodedPassword);
        usuarioRepository.save(usuarioNuevo);

        var response = new DatosUsuario(usuarioNuevo);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosUsuario>> listadoUsuarios(@PageableDefault(size = 4) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosUsuario> buscarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);

        var response = new DatosUsuario(usuario);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosUsuario> actualizarUsuario(@RequestBody @Valid ActualizarUsuario actualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(actualizarUsuario.id());

        String encodedPassword = null;
        if(actualizarUsuario.password() != null){
            encodedPassword = passwordEncoder.encode(actualizarUsuario.password());
        }
        usuario.actualizarDatos(actualizarUsuario.nombre(), encodedPassword);

        var response = new DatosUsuario(usuario);
        return ResponseEntity.ok(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
