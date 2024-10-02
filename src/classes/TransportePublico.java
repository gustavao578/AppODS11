package classes;
//@author Ryan S. Santos

public class TransportePublico {
    private String tipo; // Ex: "Ônibus", "Trem"
    private String linha;

    public TransportePublico(String tipo, String linha) {
        this.tipo = tipo;
        this.linha = linha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLinha() {
        return linha;
    }
}

