package classes;

public class LinhaOnibus {
    private String linha;
    private String partida;
    private String destino;

    public LinhaOnibus(String linha, String partida, String destino) {
        this.linha = linha;
        this.partida = partida;
        this.destino = destino;
    }

    public String getLinha() {
        return linha;
    }

    public String getPartida() {
        return partida;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Linha: " + linha + ", Partida: " + partida + ", Destino: " + destino;
    }
}
