package Alura_SpringBoot.Foro.Hub.domain.Topico.Filtro;

import Alura_SpringBoot.Foro.Hub.domain.Topico.IRepositoryTopico;
import Alura_SpringBoot.Foro.Hub.domain.Topico.RegistrarTopicos;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class Duplicados implements IValid{

    @Autowired
    IRepositoryTopico topicoRepository;

    @Override
    public void validar(RegistrarTopicos datos) {
        var tituloExiste = topicoRepository.existsByTitulo(datos.titulo());
        var mensajeExiste = topicoRepository.existsByMensaje(datos.mensaje());

        if(tituloExiste && mensajeExiste){
            throw new ValidationException("El topico esta duplicado. Ingrese topico diferente.");
        }
    }
}