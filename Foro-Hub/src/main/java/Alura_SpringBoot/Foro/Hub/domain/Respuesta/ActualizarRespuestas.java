package Alura_SpringBoot.Foro.Hub.domain.Respuesta;

import jakarta.validation.constraints.NotNull;

public record ActualizarRespuestas (@NotNull
                                    Long id,
                                    String solucion) {
}
