    package Controller;

import JDBC.ConexaoDB;
import Model.Plano;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class PlanosDAO extends FazTudo<Plano> {
public PlanosDAO() {
    super(Plano.class);
}
public static void iniciar(){
    System.out.println("Testando Conexão");
   Connection conn = ConexaoDB.getConnection(); //pega conexão com o banco de dados
    System.out.println("Testando Conexão");
        String sql = "SELECT COUNT(*) AS TOTAL FROM planos";//comando sql que conta o número das linhas da tabela
        try (Statement stmt = conn.createStatement(); //basicamente cria um espaço para fila de mensagens sql
             ResultSet rs = stmt.executeQuery(sql)) {
                 System.out.println("Testando Conexão");
            if (rs.next() && rs.getInt("TOTAL") == 0) { //pega o apelido 'TOTAL' da contagem e considera o valor de
                stmt.executeUpdate("INSERT INTO planos (nome, valor) VALUES ('Diária', 15.00)"); //0, se for 0 vai rodar
                stmt.executeUpdate("INSERT INTO planos (nome, valor) VALUES ('Plano SE Mensal', 150.00)");
                stmt.executeUpdate("INSERT INTO planos (nome, valor) VALUES ('Plano Black', 300.00)");
                stmt.executeUpdate("INSERT INTO planos (nome, valor) VALUES ('Plano Black Anual', 2640.00)");
                JOptionPane.showMessageDialog(null, "Dados … inseridos com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showInputDialog(e);
        } finally {
    ConexaoDB.closeConnection(conn); 
     System.out.println("Testando Conexão");//usa o método de conexao db para fechar conexão
        }
    }
    public static int obterIdPorNome(String nomePlano) throws SQLException {
    String sql = "SELECT id FROM planos WHERE nome = ?";
     JOptionPane.showMessageDialog(null, "Avaliando Conexão");
    try (Connection conn = ConexaoDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nomePlano);                // define o parâmetro primeiro
 JOptionPane.showMessageDialog(null,"Conexão bem Sucedida");
        try (ResultSet rs = stmt.executeQuery()) {   // só agora executa
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
    }
    throw new SQLException("Plano não encontrado: " + nomePlano);
}

public void registrarPlano(String nome, double valor) throws SQLException {
    Plano novoPlano = new Plano();
    novoPlano.setNome(nome);
    novoPlano.setValor(valor);
}
}
