package Alura_SpringBoot.Foro.Hub.domain.Topico;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryTopico extends JpaRepository<Topico,Long> {
    Page<Topico> findByStatusTrue(SpringDataWebProperties.Pageable paginacion);

    Boolean existsByTitulo(String titulo);
    Boolean existsByMensaje(String mensaje);
}
