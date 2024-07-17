package Alura_SpringBoot.Foro.Hub.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByEmail(String email);
}
