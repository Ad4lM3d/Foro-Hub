package Alura_SpringBoot.Foro.Hub.domain.Usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuario(@NotNull
                                Long id,
                                String nombre,
                                String password) {
}
