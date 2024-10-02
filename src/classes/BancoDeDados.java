package classes;
//@author Ryan S. Santos
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    // Configurações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco"; // Altere "seu_banco" para o nome do seu banco
    private static final String USUARIO = "root"; // Altere para seu usuário
    private static final String SENHA = ""; // Altere para sua senha

    // Método para estabelecer uma conexão com o banco de dados
    public Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro de conexão
            return null; // Retorna null em caso de falha
        }
    }
    // =================== Funções de Usuários ===================
// Método para adicionar usuários comuns
public void cadastrarUsuarioComum(String nome, String email, String senha, String endereco) {
    // Inserindo na tabela usuarios
    String sqlUsuarios = "INSERT INTO usuarios (nome, email, senha, endereco, tipo) VALUES (?, ?, ?, ?, 'comum')";

    try (Connection conn = conectar(); // Conecta ao banco de dados
         PreparedStatement stmtUsuarios = conn.prepareStatement(sqlUsuarios)) {

        // Configura os parâmetros da consulta para a tabela usuarios
        stmtUsuarios.setString(1, nome); // Nome do usuário
        stmtUsuarios.setString(2, email); // Email do usuário
        stmtUsuarios.setString(3, senha); // senha do usuário 
        stmtUsuarios.setString(4, endereco); // Endereço do usuário       
        stmtUsuarios.executeUpdate(); // Executa a atualização na tabela usuarios

    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao cadastrar
    }
}

// Método para adicionar usuários com permissão de administrador
public void cadastrarGerente(String nome, String email, String senha, String endereco) {
    // Inserindo na tabela gestores
    String sqlGestores = "INSERT INTO gestores (nome, email, senha, endereco, tipo) VALUES (?, ?, ?, ?, 'gerente')";

    try (Connection conn = conectar(); // Conecta ao banco de dados
         PreparedStatement stmtGestores = conn.prepareStatement(sqlGestores)) {

        // Configura os parâmetros da consulta para a tabela gestores
        stmtGestores.setString(1, nome); // Nome do gerente
        stmtGestores.setString(2, email); // Email do gerente
        stmtGestores.setString(3, senha); // senha do usuário 
        stmtGestores.setString(4, endereco); // Endereço do usuário 
        stmtGestores.executeUpdate(); // Executa a atualização na tabela gestores

    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao cadastrar
    }
}

// Método para listar usuários
public List<Usuario> listarUsuarios() {
    List<Usuario> usuarios = new ArrayList<>(); // Lista para armazenar usuários
    String sql = "SELECT * FROM usuarios"; // Consulta todos os registros da tabela usuarios
    try (Connection conn = conectar(); // Conecta ao banco de dados
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) { // Executa a consulta
        while (rs.next()) {
            Usuario usuario = new Usuario(); // Cria um novo objeto Usuario
            // Preenche os dados do usuário a partir do ResultSet
            usuario.setId(rs.getInt("id")); // Presumindo que a coluna 'id' ainda existe
            usuario.setNome(rs.getString("nome")); // Nome do usuário
            usuario.setEmail(rs.getString("email")); // Email do usuário
            usuario.setSenha(rs.getString("senha")); // Certifique-se de que a senha está na tabela correta
            usuario.setEndereco(rs.getString("endereco")); // Endereço do usuário
            usuario.setTipo(rs.getString("tipo")); // Tipo de usuário (comum ou gerente)
            usuarios.add(usuario); // Adiciona o usuário à lista
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao listar
    }
    return usuarios; // Retorna a lista de usuários
}

// Método para listar administradores
public List<Usuario> listaradmins() {
    List<Usuario> usuarios = new ArrayList<>(); // Lista para armazenar usuários
    String sql = "SELECT * FROM gestores"; // Consulta todos os registros da tabela gestores
    try (Connection conn = conectar(); // Conecta ao banco de dados
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) { // Executa a consulta
        while (rs.next()) {
            Usuario usuario = new Usuario(); // Cria um novo objeto Usuario
            // Preenche os dados do usuário a partir do ResultSet
            usuario.setId(rs.getInt("id")); // Presumindo que a coluna 'id' ainda existe
            usuario.setNome(rs.getString("nome")); // Nome do gerente
            usuario.setEmail(rs.getString("email")); // Email do gerente
            usuario.setSenha(rs.getString("senha")); // Certifique-se de que a senha está na tabela correta
            usuario.setEndereco(rs.getString("endereco")); // Endereço do gerente
            usuario.setTipo(rs.getString("tipo")); // Tipo de usuário (gerente)
            usuarios.add(usuario); // Adiciona o gerente à lista
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao listar
    }
    return usuarios; // Retorna a lista de gerentes
}

// Método para remover usuários
public boolean removerUsuario(String email) {
    String sqlUsuarios = "DELETE FROM usuarios WHERE email = ?"; // SQL para deletar da tabela usuarios
    String sqlGestores = "DELETE FROM gestores WHERE email = ?"; // SQL para deletar da tabela gestores
    
    try (Connection conn = conectar(); // Conecta ao banco de dados
         PreparedStatement stmtUsuarios = conn.prepareStatement(sqlUsuarios);
         PreparedStatement stmtGestores = conn.prepareStatement(sqlGestores)) {
        
        // Tenta remover primeiro da tabela usuarios
        stmtUsuarios.setString(1, email); // Configura o parâmetro da consulta
        int affectedRows = stmtUsuarios.executeUpdate(); // Executa a remoção
        
        // Se não afetou nenhuma linha, tenta remover da tabela gestores
        if (affectedRows == 0) {
            stmtGestores.setString(1, email); // Configura o parâmetro da consulta
            affectedRows = stmtGestores.executeUpdate(); // Executa a remoção
        }
        
        return affectedRows > 0; // Retorna true se uma linha foi afetada
    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao remover
        return false; // Retorna false em caso de erro
    }
}

// Método para editar usuários
public String editarUsuario(String emailAtual, String novoEmail, String novoNome, String novoEndereco) {
    // Verifica se todos os campos estão vazios
    if (novoEmail == null && novoNome == null && novoEndereco == null) {
        return "nenhumCampoPreenchido"; // Mensagem se nenhum campo foi preenchido
    }

    // Verifica onde o usuário está (usuarios ou gestores)
    String tipoUsuario = null;
    String sqlTipo = "SELECT tipo FROM usuarios WHERE email = ? UNION SELECT tipo FROM gestores WHERE email = ?";
    
    try (Connection conn = conectar(); // Conecta ao banco de dados
         PreparedStatement stmtTipo = conn.prepareStatement(sqlTipo)) {
        stmtTipo.setString(1, emailAtual); // Para tabela usuarios
        stmtTipo.setString(2, emailAtual); // Para tabela gestores
        ResultSet rs = stmtTipo.executeQuery(); // Executa a consulta para obter o tipo do usuário

        if (rs.next()) {
            tipoUsuario = rs.getString("tipo"); // Obtém o tipo do usuário
        } else {
            return "nenhumUsuarioEncontrado"; // Mensagem se o usuário não for encontrado
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "erroAoBuscarUsuario: " + e.getMessage(); // Mensagem de erro ao buscar usuário
    }

    // Atualiza o usuário na tabela correspondente
    StringBuilder sql = new StringBuilder();
    if ("comum".equals(tipoUsuario)) {
        sql.append("UPDATE usuarios SET "); // Prepara a atualização para a tabela usuarios
    } else if ("gerente".equals(tipoUsuario)) {
        sql.append("UPDATE gestores SET "); // Prepara a atualização para a tabela gestores
    } else {
        return "tipoUsuarioInvalido"; // Mensagem se o tipo do usuário for inválido
    }

    boolean hasUpdates = false; // Flag para verificar se houve atualizações

    // Adiciona os campos que precisam ser atualizados
    if (novoEmail != null) {
        sql.append("email = ?, "); // Adiciona email à consulta
        hasUpdates = true; // Indica que houve uma atualização
    }
    if (novoNome != null) {
        sql.append("nome = ?, "); // Adiciona nome à consulta
        hasUpdates = true; // Indica que houve uma atualização
    }
    if (novoEndereco != null) {
        sql.append("endereco = ?, "); // Adiciona endereço à consulta
        hasUpdates = true; // Indica que houve uma atualização
    }

    if (hasUpdates) {
        sql.setLength(sql.length() - 2); // Remove a última vírgula e espaço
        sql.append(" WHERE email = ?"); // Adiciona a cláusula WHERE

        try (Connection conn = conectar(); // Conecta ao banco de dados
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            if (novoEmail != null) {
                stmt.setString(index++, novoEmail); // Configura novo email
            }
            if (novoNome != null) {
                stmt.setString(index++, novoNome); // Configura novo nome
            }
            if (novoEndereco != null) {
                stmt.setString(index++, novoEndereco); // Configura novo endereço
            }
            stmt.setString(index, emailAtual); // E-mail atual para a cláusula WHERE
            int affectedRows = stmt.executeUpdate(); // Executa a atualização

            if (affectedRows > 0) {
                return "usuarioAtualizado"; // Mensagem se o usuário foi atualizado
            } else {
                return "nenhumUsuarioAtualizado"; // Mensagem se nenhum usuário foi atualizado
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao atualizar
            return "erroAoAtualizarUsuario: " + e.getMessage(); // Mensagem de erro ao atualizar
        }
    }

    return "nenhumCampoParaAtualizar"; // Mensagem se não houver campos para atualizar
}
// Método para mudar o tipo de usuário (comum para gerente ou vice-versa)
public boolean mudarTipoUsuario(String email) {
    String tipoAtual = null; // Variável para armazenar o tipo atual do usuário

    // Primeiro, precisamos determinar o tipo atual do usuário
    String sqlTipo = "SELECT tipo FROM usuarios WHERE email = ? UNION SELECT tipo FROM gestores WHERE email = ?";

    try (Connection conn = conectar(); // Conecta ao banco de dados
         PreparedStatement stmtTipo = conn.prepareStatement(sqlTipo)) {
        stmtTipo.setString(1, email); // Para tabela usuarios
        stmtTipo.setString(2, email); // Para tabela gestores
        ResultSet rs = stmtTipo.executeQuery(); // Executa a consulta

        if (rs.next()) {
            tipoAtual = rs.getString("tipo"); // Armazena o tipo atual do usuário
        } else {
            return false; // Retorna falso se o usuário não for encontrado em nenhuma tabela
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao buscar tipo de usuário
        return false; // Retorna falso em caso de erro
    }

    try (Connection conn = conectar()) { // Conecta novamente ao banco de dados
        if ("gerente".equals(tipoAtual)) {
            // Se o tipo atual é 'gerente', precisa remover da tabela 'gestores' e adicionar à tabela 'usuarios'
            String sqlRemoverGestor = "DELETE FROM gestores WHERE email = ?"; // SQL para remover gerente
            String sqlAdicionarUsuario = "INSERT INTO usuarios (nome, endereco, tipo, email) SELECT nome, endereco, 'comum', email FROM gestores WHERE email = ?"; // SQL para adicionar como usuário comum

            try (PreparedStatement stmtRemoverGestor = conn.prepareStatement(sqlRemoverGestor);
                 PreparedStatement stmtAdicionarUsuario = conn.prepareStatement(sqlAdicionarUsuario)) {

                stmtRemoverGestor.setString(1, email); // Configura o parâmetro da consulta para remover
                stmtRemoverGestor.executeUpdate(); // Executa a remoção do gerente

                stmtAdicionarUsuario.setString(1, email); // Configura o parâmetro da consulta para adicionar
                stmtAdicionarUsuario.executeUpdate(); // Executa a adição à tabela de usuários
            }
        } else if ("comum".equals(tipoAtual)) {
            // Se o tipo atual é 'comum', precisa remover da tabela 'usuarios' e adicionar à tabela 'gestores'
            String sqlRemoverUsuario = "DELETE FROM usuarios WHERE email = ?"; // SQL para remover usuário comum
            String sqlAdicionarGestor = "INSERT INTO gestores (nome, endereco, tipo, email) SELECT nome, endereco, 'gerente', email FROM usuarios WHERE email = ?"; // SQL para adicionar como gerente

            try (PreparedStatement stmtRemoverUsuario = conn.prepareStatement(sqlRemoverUsuario);
                 PreparedStatement stmtAdicionarGestor = conn.prepareStatement(sqlAdicionarGestor)) {

                stmtRemoverUsuario.setString(1, email); // Configura o parâmetro da consulta para remover
                stmtRemoverUsuario.executeUpdate(); // Executa a remoção do usuário

                stmtAdicionarGestor.setString(1, email); // Configura o parâmetro da consulta para adicionar
                stmtAdicionarGestor.executeUpdate(); // Executa a adição à tabela de gestores
            }
        }
        return true; // Retorna verdadeiro se a troca de tipo foi bem-sucedida
    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao mudar tipo de usuário
        return false; // Retorna falso em caso de erro
    }
}
// Método para validar o login do usuário
    public String validarLogin(String email, String senha) {
    String tipoUsuario = verificarUsuario(email, senha);
    if (tipoUsuario != null) {
        return tipoUsuario; // Retorna o tipo de usuário encontrado
    }
    return "usuarioNaoEncontrado"; // Mensagem se o usuário não for encontrado
}

private String verificarUsuario(String email, String senha) {
    String sqlUsuario = "SELECT tipo, senha FROM usuarios WHERE email = ?";
    String sqlGestor = "SELECT tipo, senha FROM gestores WHERE email = ?";
    
    try (Connection conn = conectar()) {
        // Verificando usuários comuns
        try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
            stmtUsuario.setString(1, email);
            ResultSet rsUsuario = stmtUsuario.executeQuery();
            if (rsUsuario.next()) {
                String senhaArmazenada = rsUsuario.getString("senha");
                if (senha.equals(senhaArmazenada)) { // Comparação direta da senha
                    return rsUsuario.getString("tipo"); // Retorna o tipo de usuário
                }
            }
        }

        // Verificando gestores
        try (PreparedStatement stmtGestor = conn.prepareStatement(sqlGestor)) {
            stmtGestor.setString(1, email);
            ResultSet rsGestor = stmtGestor.executeQuery();
            if (rsGestor.next()) {
                String senhaArmazenada = rsGestor.getString("senha");
                if (senha.equals(senhaArmazenada)) { // Comparação direta da senha
                    return rsGestor.getString("tipo"); // Retorna o tipo de gestor
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao validar login
        // Retornar uma mensagem genérica de erro
        return null;
    }
    return null; // Retorna null se não encontrar o usuário
}


// Método para obter um usuário por e-mail
public Usuario obterUsuarioPorEmail(String email) {
    Usuario usuario = null; // Inicializa o objeto Usuario como nulo
    String sql = "SELECT * FROM usuarios WHERE email = ? UNION SELECT * FROM gestores WHERE email = ?"; // SQL para buscar usuário por e-mail

    try (Connection conn = conectar(); // Conecta ao banco de dados
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Configura os parâmetros da consulta
        stmt.setString(1, email); // Primeiro parâmetro: email para usuarios
        stmt.setString(2, email); // Segundo parâmetro: email para gestores
        ResultSet rs = stmt.executeQuery(); // Executa a consulta

        if (rs.next()) {
            usuario = new Usuario(); // Cria um novo objeto Usuario
            // Preenche os dados do usuário a partir do ResultSet
            usuario.setId(rs.getInt("id")); // Presumindo que a coluna 'id' ainda existe
            usuario.setNome(rs.getString("nome")); // Nome do usuário
            usuario.setEmail(rs.getString("email")); // Email do usuário
            usuario.setSenha(rs.getString("senha")); // Senha do usuário (certifique-se de que a senha está na tabela correta)
            usuario.setEndereco(rs.getString("endereco")); // Endereço do usuário
            usuario.setTipo(rs.getString("tipo")); // Tipo de usuário (comum ou gerente)
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Exibe erro ao obter usuário
    }

    return usuario; // Retorna o usuário encontrado ou nulo se não houver
}

   // =================== Funções de Ônibus ===================
    // Método para adicionar uma linha de ônibus ao banco de dados
    public void adicionarLinhaOnibus(String linha, String partida, String destino) {
        String sql = "INSERT INTO linhas_onibus (linha, partida, destino) VALUES (?, ?, ?)";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, linha);
            stmt.setString(2, partida);
            stmt.setString(3, destino);
            stmt.executeUpdate(); // Executa a inserção
            System.out.println("Linha de ônibus adicionada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar linha de ônibus: " + e.getMessage());
        }
    }
    // Método para remover uma linha de ônibus do banco de dados
    public void removerLinhaOnibus(String linha) {
        String sql = "DELETE FROM linhas_onibus WHERE linha = ?";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, linha);
            int rowsAffected = stmt.executeUpdate(); // Executa a remoção
            if (rowsAffected > 0) {
                System.out.println("Linha de ônibus removida com sucesso!");
            } else {
                System.out.println("Nenhuma linha de ônibus encontrada com o nome: " + linha);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover linha de ônibus: " + e.getMessage());
        }
    }
    // Método para buscar linhas de ônibus
    public List<LinhaOnibus> buscarLinhasOnibus(String partida, String destino) {
    List<LinhaOnibus> linhas = new ArrayList<>();
    
    // Consulta no banco de dados (Exemplo SQL)
    String sql = "SELECT linha, partida, destino FROM linhas_onibus WHERE LOWER(destino) LIKE LOWER(?) AND partida = ?";
    
    try (Connection conn = conectar(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, "%" + destino + "%"); // Usando LIKE para pesquisar
        stmt.setString(2, partida);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String linha = rs.getString("linha");
            String partidaDB = rs.getString("partida");
            String destinoDB = rs.getString("destino");
            linhas.add(new LinhaOnibus(linha, partidaDB, destinoDB));
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return linhas;
}

    // =================== Funções de Sugestões ===================
    // Métodos de gerenciamento de sugestões
    public void adicionarSugestao(String descricao, String usuario) {
        String sql = "INSERT INTO sugestoes (descricao, usuario) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, descricao); // Configura a descrição
            stmt.setString(2, usuario); // Configura o usuário
            stmt.executeUpdate(); // Executa a inserção
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao adicionar sugestão
        }
    }

    public boolean removerSugestao(String descricao) {
        String sql = "DELETE FROM sugestoes WHERE descricao = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, descricao); // Configura a descrição da sugestão a ser removida
            int affectedRows = stmt.executeUpdate(); // Executa a remoção
            return affectedRows > 0; // Retorna true se uma linha foi afetada
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao remover sugestão
            return false; // Retorna false em caso de erro
        }
    }

    public List<Sugestao> listarSugestoes() {
        List<Sugestao> sugestoes = new ArrayList<>(); // Lista para armazenar sugestões
        String sql = "SELECT * FROM sugestoes";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Cria um novo objeto Sugestao a partir dos dados do ResultSet
                Sugestao sugestao = new Sugestao(rs.getString("descricao"), rs.getString("usuario"));
                sugestoes.add(sugestao); // Adiciona a sugestão à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao listar sugestões
        }
        return sugestoes; // Retorna a lista de sugestões
    }

    public List<Sugestao> listarSugestoesPorUsuario(String usuarioEmail) {
        List<Sugestao> sugestoes = new ArrayList<>(); // Lista para armazenar sugestões do usuário
        String sql = "SELECT * FROM sugestoes WHERE usuario = ?";  
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setString(1, usuarioEmail); // Configura o parâmetro para o email do usuário
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                // Cria um novo objeto Sugestao a partir dos dados do ResultSet
                Sugestao sugestao = new Sugestao(rs.getString("descricao"), rs.getString("usuario"));
                sugestoes.add(sugestao); // Adiciona a sugestão à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao listar sugestões
        }
        return sugestoes; // Retorna a lista de sugestões do usuário
    }
    // =================== Funções de Áreas de Risco ===================
    // Métodos de gerenciamento de áreas de risco
    public void adicionarAreaDeRisco(String descricao, String localizacao) {
        String sql = "INSERT INTO areas_risco (descricao, localizacao) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, descricao); // Configura a descrição da área de risco
            stmt.setString(2, localizacao); // Configura a localização da área de risco
            stmt.executeUpdate(); // Executa a inserção
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao adicionar área de risco
        }
    }

    public List<AreaRisco> listarAreasDeRisco() {
        List<AreaRisco> areasRisco = new ArrayList<>(); // Lista para armazenar áreas de risco
        String sql = "SELECT * FROM areas_risco";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Cria um novo objeto AreaRisco a partir dos dados do ResultSet
                AreaRisco area = new AreaRisco(rs.getString("descricao"), rs.getString("localizacao"));
                areasRisco.add(area); // Adiciona a área de risco à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao listar áreas de risco
        }
        return areasRisco; // Retorna a lista de áreas de risco
    }

   // =================== Funções de EspacoPublico ===================
    // Métodos de gerenciamento de espaços públicos
    public void adicionarEspacoPublico(String nome, String descricao) {
        String sql = "INSERT INTO espacos_publicos (nome, descricao) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome); // Configura o nome do espaço público
            stmt.setString(2, descricao); // Configura a descrição do espaço público
            stmt.executeUpdate(); // Executa a inserção
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao adicionar espaço público
        }
    }

    public List<EspacoPublico> listarEspacosPublicos() {
        List<EspacoPublico> espacosPublicos = new ArrayList<>(); // Lista para armazenar espaços públicos
        String sql = "SELECT * FROM espacos_publicos";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Cria um novo objeto EspacoPublico a partir dos dados do ResultSet
                EspacoPublico espaco = new EspacoPublico(rs.getString("nome"), rs.getString("descricao"));
                espacosPublicos.add(espaco); // Adiciona o espaço público à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao listar espaços públicos
        }
        return espacosPublicos; // Retorna a lista de espaços públicos
    }
    // =================== Funções de Planejamento Urbano ===================
    // Métodos de gerenciamento de planejamento urbano
    public void adicionarPlanejamentoUrbano(String nome, String descricao) {
        String sql = "INSERT INTO planejamento_urbano (nome, descricao) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome); // Configura o nome do planejamento urbano
            stmt.setString(2, descricao); // Configura a descrição do planejamento urbano
            stmt.executeUpdate(); // Executa a inserção
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao adicionar planejamento urbano
        }
    }

    public List<PlanejamentoUrbano> listarPlanejamentoUrbano() {
        List<PlanejamentoUrbano> planejamentoUrbano = new ArrayList<>(); // Lista para armazenar planejamentos urbanos
        String sql = "SELECT * FROM planejamento_urbano";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Cria um novo objeto PlanejamentoUrbano a partir dos dados do ResultSet
                PlanejamentoUrbano planejamento = new PlanejamentoUrbano(rs.getString("nome"), rs.getString("descricao"));
                planejamentoUrbano.add(planejamento); // Adiciona o planejamento urbano à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao listar planejamentos urbanos
        }
        return planejamentoUrbano; // Retorna a lista de planejamentos urbanos
    }
}
