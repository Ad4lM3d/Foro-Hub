package Alura_SpringBoot.Foro.Hub.domain.Topico.Filtro;

import Alura_SpringBoot.Foro.Hub.domain.Topico.RegistrarTopicos;
import Alura_SpringBoot.Foro.Hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

@Component
public class Nulos implements IValid{

    @Override
    public void validar(RegistrarTopicos datos) {
        if(datos.idUsuario() == null){
            throw new ValidacionDeIntegridad("idUsuario no puede ser null");
        }
        if(datos.titulo() == null){
            throw new ValidacionDeIntegridad("Titulo no puede ser null");
        }
        if(datos.mensaje() == null){
            throw new ValidacionDeIntegridad("Mensaje no puede ser null");
        }
        if(datos.curso() == null){
            throw new ValidacionDeIntegridad("Curso no puede ser null");
        }
    }
}
