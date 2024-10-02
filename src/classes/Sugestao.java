package classes;
//@author Ryan S. Santos

public class Sugestao {
    private String descricao;
    private String usuario; // Nome do usuário que fez a sugestão

    public Sugestao(String descricao, String usuario) {
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsuario() {
        return usuario;
    }
}

