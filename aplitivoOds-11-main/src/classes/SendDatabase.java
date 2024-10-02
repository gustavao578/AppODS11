package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class SendDatabase {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/seu_banco"; // Altere para o seu URL do banco
    private static final String DB_USER = "root"; // Altere para o seu usuário do banco
    private static final String DB_PASSWORD = "27224"; // Altere para a sua senha do banco

    // Lista maior de nomes e sobrenomes
    private static final String[][] NOMES = {
        {"Ricardo", "Silva"},
        {"Ana", "Souza"},
        {"Carlos", "Pereira"},
        {"Juliana", "Almeida"},
        {"Felipe", "Costa"},
        {"Mariana", "Lima"},
        {"Eduardo", "Santos"},
        {"Patrícia", "Oliveira"},
        {"Gustavo", "Ferreira"},
        {"Fernanda", "Barbosa"},
        {"Thiago", "Cardoso"},
        {"Camila", "Melo"},
        {"Lucas", "Nogueira"},
        {"Isabela", "Teixeira"},
        {"Renato", "Martins"},
        {"Roberta", "Ribeiro"},
        {"Bruno", "Dias"},
        {"Simone", "Gomes"},
        {"Sérgio", "Azevedo"},
        {"Tatiane", "Figueiredo"},
        {"André", "Cunha"},
        {"Monique", "Pereira"},
        {"Leonardo", "Furtado"},
        {"Daniele", "Barros"},
        {"Robson", "Tavares"},
        {"Fernanda", "Mendes"},
        {"Jorge", "César"},
        {"Tatiane", "Dantas"},
        {"Vitor", "Teixeira"},
        {"Eliane", "Santos"},
        {"Aline", "Ferreira"},
        {"Caio", "Alves"},
        {"Gisele", "Melo"},
        {"Luiz", "Rodrigues"},
        {"Marcela", "Lopes"},
        {"Júlio", "Cavalcante"},
        {"Priscila", "Coutinho"},
        {"Fabiano", "Almeida"},
        {"Karla", "Oliveira"},
        {"Ricardo", "Freitas"},
        {"Sabrina", "Silva"},
        {"Gustavo", "Lima"},
        {"Sofia", "Marinho"},
        {"Mário", "Barbosa"},
        {"Tatiane", "Machado"},
    };

    private static final String[] EMAILS = {
        "@yahoo.com",
        "@gmail.com",
        "@hotmail.com",
        "@outlook.com",
        "@live.com",
        "@bol.com.br",
    };

    public static void main(String[] args) {
        try (Connection conn = conectar()) {
            SendDatabase usuarioService = new SendDatabase();
            usuarioService.cadastrarUsuarios(conn, 75, "comum");  // Cadastra usuários comuns
            usuarioService.cadastrarUsuarios(conn, 25, "gerente"); // Cadastra gerentes
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erro ao conectar ao banco de dados
        }
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void cadastrarUsuarios(Connection conn, int quantidade, String tipo) throws SQLException {
        String sqlUsuarios = "INSERT INTO usuarios (nome, email, senha, endereco, tipo) VALUES (?, ?, ?, ?, ?)";
        String sqlGestores = "INSERT INTO gestores (nome, email, senha, endereco, tipo) VALUES (?, ?, ?, ?, ?)";
        String sqlVerificarEmailUsuarios = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        String sqlVerificarEmailGestores = "SELECT COUNT(*) FROM gestores WHERE email = ?";

        try (
            PreparedStatement stmtUsuarios = conn.prepareStatement(sqlUsuarios);
            PreparedStatement stmtGestores = conn.prepareStatement(sqlGestores);
            PreparedStatement stmtVerificarEmailUsuarios = conn.prepareStatement(sqlVerificarEmailUsuarios);
            PreparedStatement stmtVerificarEmailGestores = conn.prepareStatement(sqlVerificarEmailGestores)
        ) {
            Random random = new Random();

            for (int i = 0; i < quantidade; i++) {
                String[] nomeCompleto = NOMES[random.nextInt(NOMES.length)];
                String nome = nomeCompleto[0] + " " + nomeCompleto[1];
                String email = nomeCompleto[0].toLowerCase() + i + EMAILS[random.nextInt(EMAILS.length)];
                String senha = "senha123"; // Considere usar hashing
                String endereco = "Rua " + (random.nextInt(100) + 1) + ", São Paulo, SP";

                // Verificar se o email já existe na tabela usuarios
                stmtVerificarEmailUsuarios.setString(1, email);
                try (ResultSet rs = stmtVerificarEmailUsuarios.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // Email já existe na tabela usuarios
                        System.out.println("Email duplicado encontrado na tabela usuarios: " + email);
                        i--; // Decrementa i para tentar novamente
                        continue; // Ignora a iteração atual
                    }
                }

                // Verificar se o email já existe na tabela gestores
                stmtVerificarEmailGestores.setString(1, email);
                try (ResultSet rsGestores = stmtVerificarEmailGestores.executeQuery()) {
                    if (rsGestores.next() && rsGestores.getInt(1) > 0) {
                        // Email já existe na tabela gestores
                        System.out.println("Email duplicado encontrado na tabela gestores: " + email);
                        i--; // Decrementa i para tentar novamente
                        continue; // Ignora a iteração atual
                    }
                }

                // Inserir na tabela usuários ou gestores
                if (tipo.equals("comum")) {
                    stmtUsuarios.setString(1, nome);
                    stmtUsuarios.setString(2, email);
                    stmtUsuarios.setString(3, senha);
                    stmtUsuarios.setString(4, endereco);
                    stmtUsuarios.setString(5, tipo);
                    stmtUsuarios.executeUpdate();
                    System.out.println("Usuário comum cadastrado: " + nome);
                } else if (tipo.equals("gerente")) {
                    stmtGestores.setString(1, nome);
                    stmtGestores.setString(2, email);
                    stmtGestores.setString(3, senha);
                    stmtGestores.setString(4, endereco);
                    stmtGestores.setString(5, tipo);
                    stmtGestores.executeUpdate();
                    System.out.println("Gerente cadastrado: " + nome);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
    }
}
