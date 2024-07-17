package Alura_SpringBoot.Foro.Hub.domain.Respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(@NotNull
                                      Long idUsuario,
                                     @NotNull
                                      Long idTopico,
                                     @NotBlank
                                      String solucion
) {
}
