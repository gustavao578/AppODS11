package classes;

public class UsuarioLogado {
    private static Usuario usuario; // Armazena o usuário atualmente logado

    // Define o usuário logado
    public static void setUsuario(Usuario usuarioLogado) {
        usuario = usuarioLogado; // Atribui o usuário logado
    }

    // Retorna o usuário logado
    public static Usuario getUsuario() {
        return usuario; // Retorna o usuário atual
    }

    // Realiza logout, removendo o usuário logado
    public static void logout() {
        usuario = null; // Limpa a referência ao usuário logado
    }

    // Verifica se o usuário logado é um gerente
    public static boolean isGerente() {
        return usuario != null && "gerente".equalsIgnoreCase(usuario.getTipo()); // Retorna true se for gerente
    }
}
