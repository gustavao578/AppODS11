package classes;
//@author Ryan S. Santos

public class EspacoPublico {
    private String nome;
    private String descricao;

    public EspacoPublico(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

