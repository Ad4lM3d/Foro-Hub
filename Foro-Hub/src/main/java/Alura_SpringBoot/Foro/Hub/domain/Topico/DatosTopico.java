package Alura_SpringBoot.Foro.Hub.domain.Topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosTopico(Long id,
                          String usuario,
                          String titulo,
                          String mensaje,
                          String curso) {
    public DatosTopico(Topico topico){
        this(topico.getId(), topico.getUsuario().getNombre(), topico.getTitulo(), topico.getMensaje(), topico.getCurso());
    }
}
