package Alura_SpringBoot.Foro.Hub.domain.Respuesta;

import Alura_SpringBoot.Foro.Hub.domain.Topico.IRepositoryTopico;
import Alura_SpringBoot.Foro.Hub.domain.Topico.Topico;
import Alura_SpringBoot.Foro.Hub.domain.Usuario.IUsuarioRepository;
import Alura_SpringBoot.Foro.Hub.domain.Usuario.Usuario;
import Alura_SpringBoot.Foro.Hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SRespuesta {
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    IRepositoryTopico topicoRepository;

    @Autowired
    IRepositoryRespuesta respuestaRepository;

    public DatosRespuesta registrarRespuesta(DatosRegistroRespuesta datos){
        if(!usuarioRepository.existsById(datos.idUsuario())){
            throw new ValidacionDeIntegridad("Id de usuario no encontrado");
        }

        if(!topicoRepository.existsById(datos.idTopico())){
            throw new ValidacionDeIntegridad("Id de topico no encontrado");
        }

        Usuario usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        Topico topico = topicoRepository.getReferenceById(datos.idTopico());

        Respuesta respuestaNueva = new Respuesta(datos.solucion(),topico,usuario);
        respuestaRepository.save(respuestaNueva);

        return new DatosRespuesta(respuestaNueva);
    }
}
