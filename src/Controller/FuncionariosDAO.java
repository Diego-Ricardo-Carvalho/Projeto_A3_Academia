package Controller;

import Model.Funcionario;
import java.sql.*;

public class FuncionariosDAO extends FazTudo<Funcionario> {
    public FuncionariosDAO(){
        super(Funcionario.class);
    }
public void cadastrarFuncionario(String nome, String cpf, String login, String senha, String cargo)throws SQLException {
Funcionario novoFuncionario = new Funcionario();
novoFuncionario.setNome(nome);
novoFuncionario.setCpf(cpf);
novoFuncionario.setLogin(login);
novoFuncionario.setSenha(senha);
novoFuncionario.setCargo(cargo);
this.create(novoFuncionario);
}
public String autenticarFuncionario(String login, String senha) throws SQLException {
        String sql = "SELECT cargo FROM funcionarios WHERE login = ? AND senha = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
              try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cargo");
                }
            }
        }
        return null;
    }
}


