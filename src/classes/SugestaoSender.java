package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SugestaoSender {
    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco"; // URL do seu banco de dados
    private static final String USER = "root"; // seu usuário do banco
    private static final String PASSWORD = ""; // sua senha do banco

    private static final String[] EVENTOS = {
        "Show de música ao vivo no parque",
        "Feira de artesanato na praça central",
        "Festival de comida de rua",
        "Exposição de arte contemporânea",
        "Palestra sobre sustentabilidade",
        "Cinema ao ar livre",
        "Corrida de rua",
        "Workshop de fotografia",
        "Teatro na escola municipal",
        "Festival de música clássica"
    };

    private static final String[] TRANSPORTES = {
        "Melhorar a frequência dos ônibus",
        "Implementar ciclovias na cidade",
        "Criar aplicativo para rastreamento de ônibus",
        "Aumentar o número de linhas de metrô",
        "Incluir carros elétricos no transporte público",
        "Implementar desconto para estudantes",
        "Criar um sistema de caronas",
        "Melhorar a sinalização nas paradas de ônibus",
        "Instalar mais bicicletários",
        "Promover campanhas de educação sobre o transporte público"
    };

    private static final String[] APLICATIVO = {
        "Adicionar opção de feedback para usuários",
        "Implementar notificações sobre horários de transporte",
        "Melhorar a interface do usuário",
        "Adicionar mapas interativos",
        "Incluir informações sobre eventos na cidade",
        "Criar uma seção de dicas de segurança",
        "Implementar um sistema de recompensas",
        "Adicionar suporte a múltiplos idiomas",
        "Melhorar a performance do aplicativo",
        "Incluir uma área de perguntas frequentes"
    };

    public static void main(String[] args) {
        List<String> usuarios = obterUsuarios();
        enviarSugestoesParaBanco(usuarios, 64);
    }

    private static List<String> obterUsuarios() {
        List<String> usuarios = new ArrayList<>();
        String sql = "SELECT nome FROM usuarios"; // Altere 'nome' para a coluna que armazena o nome do usuário
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(rs.getString("nome")); // Altere 'nome' conforme necessário
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private static void enviarSugestoesParaBanco(List<String> usuarios, int quantidade) {
        Random random = new Random();
        for (int i = 0; i < quantidade; i++) {
            String sugestao = gerarSugestao(i);
            String usuario = usuarios.get(random.nextInt(usuarios.size())); // Seleciona um usuário aleatório
            adicionarSugestao(sugestao, usuario);
        }
        System.out.println(quantidade + " sugestões enviadas com sucesso!");
    }

    private static String gerarSugestao(int i) {
        String tipo;
        Random random = new Random();
        if (i % 3 == 0) {
            tipo = EVENTOS[random.nextInt(EVENTOS.length)];
        } else if (i % 3 == 1) {
            tipo = TRANSPORTES[random.nextInt(TRANSPORTES.length)];
        } else {
            tipo = APLICATIVO[random.nextInt(APLICATIVO.length)];
        }
        return tipo;
    }

    public static void adicionarSugestao(String descricao, String usuario) {
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

    private static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

