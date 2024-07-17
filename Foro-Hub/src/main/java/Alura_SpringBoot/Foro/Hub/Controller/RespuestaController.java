package Alura_SpringBoot.Foro.Hub.Controller;

import Alura_SpringBoot.Foro.Hub.domain.Respuesta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    IRepositoryRespuesta respuestaRepository;

    @Autowired
    SRespuesta respuestaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos){
        var response = respuestaService.registrarRespuesta(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuesta>> listadoRespuestas(@PageableDefault(size = 4) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuesta> buscarRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var response = new DatosRespuesta(respuesta);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid ActualizarRespuestas rtaActualizada) {
        Respuesta respuesta = respuestaRepository.getReferenceById(rtaActualizada.id());
        respuesta.actualizarDatos(rtaActualizada);

        var response = new DatosRespuesta(respuesta);
        return ResponseEntity.ok(response);
    }
    // DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}