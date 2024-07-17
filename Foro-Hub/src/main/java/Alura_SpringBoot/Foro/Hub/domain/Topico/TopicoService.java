package Alura_SpringBoot.Foro.Hub.domain.Topico;

import Alura_SpringBoot.Foro.Hub.domain.Topico.Filtro.IValid;
import Alura_SpringBoot.Foro.Hub.domain.Usuario.IUsuarioRepository;
import Alura_SpringBoot.Foro.Hub.domain.Usuario.Usuario;
import Alura_SpringBoot.Foro.Hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    IRepositoryTopico topicoRepository;

    @Autowired
    List<IValid> validadores;

    public DatosTopico registrarTopico(RegistrarTopicos datos){
        if(!usuarioRepository.existsById(datos.idUsuario())){
            throw new ValidacionDeIntegridad("Id de usuario no encontrado");
        }

        //Validaciones
        validadores.forEach(v->v.validar(datos));

        Usuario usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var topicoNuevo = new Topico(datos.titulo(), datos.mensaje(),usuario, datos.curso());

        topicoRepository.save(topicoNuevo);

        return new DatosTopico(topicoNuevo);
    }
}
