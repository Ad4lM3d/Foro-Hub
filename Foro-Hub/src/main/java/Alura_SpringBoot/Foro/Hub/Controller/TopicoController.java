package Alura_SpringBoot.Foro.Hub.Controller;


import Alura_SpringBoot.Foro.Hub.domain.Topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    IRepositoryTopico topicoRepository;

    @Autowired
    TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosTopico> registrarTopico(@RequestBody @Valid RegistrarTopicos datos){
        var response = topicoService.registrarTopico(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosTopico>> listadoTopicos(@PageableDefault(size = 4) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue((SpringDataWebProperties.Pageable) paginacion).map(DatosTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosTopico> buscarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);

        var response = new DatosTopico(topico);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico topicoActualizado) {
        Topico topico = topicoRepository.getReferenceById(topicoActualizado.id());
        topico.actualizarDatos(topicoActualizado);

        var response = new DatosTopico(topico);
        return ResponseEntity.ok(response);
    }

    // DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.borrarTopico();
        return ResponseEntity.noContent().build();
    }
}