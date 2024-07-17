package Alura_SpringBoot.Foro.Hub.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{
    public ValidacionDeIntegridad(String valid){
        super(valid);
    }
}
