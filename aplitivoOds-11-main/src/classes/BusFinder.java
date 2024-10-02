package classes;

import java.util.List;

public class BusFinder {
    private String partidaSelecionada = ""; // Variável para armazenar o ponto de partida selecionado
    private BancoDeDados bancoDeDados; // Instância da classe BancoDeDados

    // Construtor
    public BusFinder() {
        bancoDeDados = new BancoDeDados(); // Inicializa o BancoDeDados
    }

    // Método para definir o ponto de partida selecionado
    public void setPartidaSelecionada(String partida) {
        this.partidaSelecionada = partida;
    }

    // Método para obter o ponto de partida selecionado
    public String getPartidaSelecionada() {
        return partidaSelecionada;
    }

    // Método para verificar se um ponto de partida foi selecionado
    public boolean isPartidaSelecionada() {
        return !partidaSelecionada.isEmpty();
    }
}
