package Alura_SpringBoot.Foro.Hub.domain.Usuario;

import jakarta.validation.constraints.NotBlank;

public record AutentificarUsuario(@NotBlank
                                  String email,
                                  @NotBlank
                                  String password) {
}
