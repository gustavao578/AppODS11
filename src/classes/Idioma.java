package classes;
//@author Ryan S. Santos

public class Idioma {
    private static String idiomaAtual = "pt"; // Defina um idioma padrão

    public static String getIdiomaAtual() {
        return idiomaAtual;
    }

    public static void setIdiomaAtual(String idioma) {
        idiomaAtual = idioma;
    }
}
