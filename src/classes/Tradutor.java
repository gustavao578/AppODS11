package classes;
//@author Ryan S. Santos
import java.util.HashMap;
import java.util.Map;

public class Tradutor {
    private Map<String, String[]> dicionario; // Mapa que armazena as traduções
    private String idiomaAtual; // Idioma atualmente selecionado

    // Construtor que inicializa o tradutor com o idioma especificado
    public Tradutor(String idioma) {
        this.idiomaAtual = idioma;
        inicializarDicionario(); // Chama o método para inicializar o dicionário
    }

    // Método privado para inicializar o dicionário de traduções
    private void inicializarDicionario() {
        dicionario = new HashMap<>(); // Cria um novo HashMap para o dicionário

    // Dicionário com traduções em [Português, Inglês, Espanhol]
    // --- Cadastro ---
    // Texto relacionado ao processo de cadastro de usuários
    dicionario.put("tituloCadastro", new String[]{"Faça Seu Cadastro de Morador", "Make Your Resident Registration", "Haga Su Registro de Residente"});
    dicionario.put("nomeLabel", new String[]{"Nome :", "Name :", "Nombre :"});
    dicionario.put("emailLabel", new String[]{"Email:", "Email:", "Correo electrónico:"});
    dicionario.put("senhaLabel", new String[]{"Senha:", "Password:", "Contraseña:"});
    dicionario.put("mostrarSenha", new String[]{"mostrar senha", "show password", "mostrar contraseña"});
    dicionario.put("cadastrar", new String[]{"cadastrar", "register", "registrar"});
    dicionario.put("fazer_cadastro", new String[]{"Fazer Cadastro", "Register", "Hacer Registro"});
    dicionario.put("enderecoLabel", new String[]{"Endereço:", "Address:", "Dirección:"});
    dicionario.put("descricao1", new String[]{"Para realizar o seu cadastro,", "To complete your registration,", "Para realizar su registro,"});
    dicionario.put("descricao2", new String[]{"prencha os campos abaixo", "fill in the fields below", "complete los campos a continuación"});

    // --- Cadastro Gestor ---
    // Texto relacionado ao cadastro de gestores
    dicionario.put("tituloCadastroGestor", new String[]{"Cadastro de Gestor", "Manager Registration", "Registro de Gestor"});
    dicionario.put("nomeGestorLabel", new String[]{"Nome do Gestor:", "Manager Name:", "Nombre del Gestor:"});
    dicionario.put("emailGestorLabel", new String[]{"Email do Gestor:", "Manager Email:", "Correo electrónico del Gestor:"});

    // --- Login ---
    // Texto relacionado ao processo de login de usuários
    dicionario.put("tituloLogin", new String[]{"Login", "Login", "Iniciar sesión"});
    dicionario.put("usuarioLabel", new String[]{"Usuário:", "User:", "Usuario:"});
    dicionario.put("senhaLoginLabel", new String[]{"Senha:", "Password:", "Contraseña:"});
    dicionario.put("botaoLogin", new String[]{"Entrar", "Log In", "Iniciar sesión"});
    dicionario.put("mensagemErroLogin", new String[]{"Usuário ou senha incorretos.", "Incorrect username or password.", "Usuario o contraseña incorrectos."});
    
    // --- Idioma ---
    // Texto relacionado à seleção de idiomas na interface
    dicionario.put("opcoesMenu", new String[]{"opções", "options", "opciones"});
    dicionario.put("idiomaMenu", new String[]{"Idioma", "Language", "Idioma"});
    dicionario.put("ptBr", new String[]{"Português (pt-br)", "Portuguese (pt-br)", "Portugués (pt-br)"});
    dicionario.put("en", new String[]{"Inglês (en)", "English (en)", "Inglés (en)"});
    dicionario.put("es", new String[]{"espanhol (es)", "Spanish (es)", "español (es)"});

    // --- Tela Inicial ---
    // Texto relacionado à tela inicial da aplicação
    dicionario.put("telaInicial", new String[]{"Tela Inicial", "Home Screen", "Pantalla de Inicio"});
    dicionario.put("administracao", new String[]{"administração", "administration", "administración"});

    // --- Administração ---
    // Texto relacionado às funcionalidades administrativas
    dicionario.put("cadastro_gestao", new String[]{"Cadastro Gestão", "Management Registration", "Registro de Gestión"});
    dicionario.put("cadastro_moradores", new String[]{"Cadastro Moradores", "Resident Registration", "Registro de Residentes"});
    dicionario.put("gerenciamento_usuarios", new String[]{"Gerenciamento Usuarios", "User Management", "Gestión de Usuarios"});
    dicionario.put("gerenciamento_admins", new String[]{"Gerenciamento Admins", "Admin Management", "Gestión de Admins"});

    // --- Sugestões ---
    // Texto relacionado ao envio e gerenciamento de sugestões
    dicionario.put("menu_sugestoes", new String[]{"menu Sugestões", "Suggestions Menu", "Menú de Sugerencias"});
    dicionario.put("enviar_sugestoes", new String[]{"enviar sugestões", "send suggestions", "enviar sugerencias"});
    dicionario.put("sugestoes_gerais", new String[]{"sugestões gerais", "general suggestions", "sugerencias generales"});
    dicionario.put("minhas_sugestoes", new String[]{"minhas sugestões", "my suggestions", "mis sugerencias"});
    dicionario.put("area_de_sugestoes", new String[]{"Area de sugestões", "Suggestion Area", "Área de Sugerencias"});
    dicionario.put("sugestao", new String[]{"Sugestão:", "Suggestion:", "Sugerencia:"});
    dicionario.put("enviar", new String[]{"Enviar", "Send", "Enviar"}); // Adicionado aqui

    // --- Urbano ---
    // Texto relacionado ao transporte e espaços urbanos
    dicionario.put("urbano", new String[]{"urbano", "urban", "urbano"});
    dicionario.put("espacos_publicos", new String[]{"espaços publicos", "public spaces", "espacios públicos"});
    dicionario.put("onibus", new String[]{"onibus", "bus", "autobús"});

    // --- Usuário ---
    // Texto relacionado à edição e gerenciamento de usuários
    dicionario.put("editar_usuario", new String[]{"Editar Usuario", "Edit User", "Editar Usuario"});
    dicionario.put("modificar_permissao", new String[]{"modifcar permisão", "modify permission", "modificar permiso"});
    dicionario.put("excluir_usuario", new String[]{"Excluir Usuario", "Delete User", "Eliminar Usuario"});
    dicionario.put("apagar", new String[]{"apagar", "delete", "eliminar"});

    // --- Mensagens ---
    // Mensagens de status e feedback para o usuário
    dicionario.put("voltar", new String[]{"Voltar", "Back", "Regresar"});
    dicionario.put("bemVindo", new String[]{"Bem-vindo", "Welcome", "Bienvenido"});
    dicionario.put("erro", new String[]{"Erro", "Error", "Error"});
    dicionario.put("sucesso", new String[]{"Sucesso", "Success", "Éxito"});
    dicionario.put("carregando", new String[]{"Carregando...", "Loading...", "Cargando..."});
    dicionario.put("aguarde", new String[]{"Aguarde...", "Please wait...", "Por favor, espere..."});
    dicionario.put("tenteNovamente", new String[]{"Tente novamente.", "Please try again.", "Por favor, inténtelo de nuevo."});
    dicionario.put("sucessoOperacao", new String[]{"Operação realizada com sucesso!", "Operation completed successfully!", "¡Operación completada con éxito!"});
    dicionario.put("usuarioInexistente", new String[]{"Usuário inexistente.", "User does not exist.", "Usuario inexistente."});
    dicionario.put("erroBanco", new String[]{"Erro ao acessar o banco de dados.", "Error accessing the database.", "Error al acceder a la base de datos."});
    dicionario.put("erroDesconhecido", new String[]{"Erro desconhecido.", "Unknown error.", "Error desconocido."});
    dicionario.put("sucessoEditarUsuario", new String[]{"Edição realizada com sucesso!", "Edit successfully completed!", "Edición realizada con éxito!"});
    dicionario.put("nenhumCampoPreenchido", new String[]{"Nenhum campo preenchido para edição.", "No fields filled for editing.", "Ningún campo lleno para editar."});
    dicionario.put("nenhumCampoAlterado", new String[]{"Nenhum campo foi alterado.", "No fields were changed.", "Ningún campo fue cambiado."});
    dicionario.put("nenhumUsuarioEncontrado", new String[]{"Nenhum usuário encontrado com o e-mail fornecido.", "No user found with the provided email.", "No se encontró ningún usuario con el correo electrónico proporcionado."});
    dicionario.put("erroEditarUsuario", new String[]{"Erro ao editar usuário: ", "Error editing user: ", "Error al editar el usuario: "});
    dicionario.put("sucessoTrocaTipoUsuario", new String[]{"Tipo de usuário alterado com sucesso!", "User type changed successfully!", "Tipo de usuario cambiado con éxito!"});
    dicionario.put("nenhumUsuarioEncontradoTipo", new String[]{"Nenhum usuário encontrado com o e-mail fornecido para alteração de tipo.", "No user found with the provided email for type change.", "No se encontró ningún usuario con el correo electrónico proporcionado para cambiar de tipo."});
    dicionario.put("erroTrocaTipoUsuario", new String[]{"Erro ao alterar tipo de usuário: ", "Error changing user type: ", "Error al cambiar el tipo de usuario: "});
    }

    // Método para traduzir uma chave para o idioma atual
    public String traduzir(String chave) {
        String[] traducoes = dicionario.get(chave); // Busca as traduções pela chave
        if (traducoes != null) {
            // Retorna a tradução correspondente ao idioma atual
            switch (idiomaAtual) {
                case "pt":
                    return traducoes[0]; // Português
                case "en":
                    return traducoes[1]; // Inglês
                case "es":
                    return traducoes[2]; // Espanhol
                default:
                    return chave; // Retorna a chave se o idioma não for reconhecido
            }
        }
        return chave; // Retorna a chave se não encontrar tradução
    }

    // Método para alterar o idioma atual
    public void setIdiomaAtual(String idioma) {
        this.idiomaAtual = idioma; // Atualiza o idioma atual
    }
}
