package Alura_SpringBoot.Foro.Hub.domain.Topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        String curso

) {
}
