package Alura_SpringBoot.Foro.Hub.domain.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarTopicos(@NotNull
                               Long idUsuario,
                               @NotBlank
                               String titulo,
                               @NotBlank
                               String mensaje,
                               @NotBlank
                               String curso
) {
}