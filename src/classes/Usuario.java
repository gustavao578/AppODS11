package classes;
//@author Ryan S. Santos
 
public class Usuario {
    private int id; // Identificador único do usuário
    private String nome; // Nome do usuário
    private String email; // Email do usuário
    private String senha; // Senha do usuário
    private String tipo; // Tipo do usuário: 'comum' ou 'gerente'
    private String endereco; // Endereço do usuário (novo campo)

    // Construtor padrão
    public Usuario() {
    }

    // Construtor que inicializa todos os atributos
    public Usuario(String nome, String email, String senha, String tipo, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.endereco = endereco; // Inicializa o campo endereço
    }

    // Getters e Setters para os atributos

    public int getId() {
        return id; // Retorna o ID do usuário
    }

    public void setId(int id) {
        this.id = id; // Define o ID do usuário
    }

    public String getNome() {
        return nome; // Retorna o nome do usuário
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do usuário
    }

    public String getEmail() {
        return email; // Retorna o email do usuário
    }

    public void setEmail(String email) {
        this.email = email; // Define o email do usuário
    }

    public String getSenha() {
        return senha; // Retorna a senha do usuário
    }

    public void setSenha(String senha) {
        this.senha = senha; // Define a senha do usuário
    }

    public String getTipo() {
        return tipo; // Retorna o tipo do usuário
    }

    public void setTipo(String tipo) {
        this.tipo = tipo; // Define o tipo do usuário
    }

    public String getEndereco() { // Getter para o endereço
        return endereco; // Retorna o endereço do usuário
    }

    public void setEndereco(String endereco) { // Setter para o endereço
        this.endereco = endereco; // Define o endereço do usuário
    }
}
