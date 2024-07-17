package Alura_SpringBoot.Foro.Hub.domain.Usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistrarUsuario(@NotBlank
                                    String email,
                                    @NotBlank
                                    String nombre,
                                    @NotBlank
                                    String password
) {

}