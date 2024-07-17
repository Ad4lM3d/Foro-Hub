package Alura_SpringBoot.Foro.Hub.domain.Respuesta;

import java.time.LocalDateTime;

public record DatosRespuesta(Long id,
                             String autor,
                             String tituloTopico,
                             LocalDateTime fechaCreacion,
                             String solucion
) {
    public DatosRespuesta(Respuesta respuesta){
        this(respuesta.getId(),
                respuesta.getAutor().getNombre(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion());
    }
}
