package Controller;

import Model.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import JDBC.ConexaoDB;

public class PagamentosDAO extends FazTudo<Pagamento>{ //incompleto

    public PagamentosDAO() {
        super(Pagamento.class);
    }
public List<Pagamento> listarPagamentos(int idMembro) throws SQLException{
    String sql = """
            SELECT  p.* FROM pagamentos   p JOIN membro_plano mp ON p.id_memplan = mp.id_memplan WHERE mp.id_membro = ?
            """;
List<Pagamento> pagamentos = new ArrayList<>();
try(Connection conn = getConnection();
PreparedStatement stmt = conn.prepareStatement(sql)){
    stmt.setInt(1, idMembro);
    ResultSet rs = stmt.executeQuery();

    while(rs.next()){
        Pagamento pagamento = mapearResultSetParaEntidade(rs); //frankestein
        pagamentos.add(pagamento);
    }
}
return pagamentos;
}

public void registrarPagamento(int idMembro, double valorBase, double valorPago, int desconto, String mes) throws SQLException {
    int idMemplan = obterIdMembroPlanoAtual(idMembro);
    
    Pagamento pagamento = new Pagamento();
    pagamento.setIdMemplan(idMemplan);
    pagamento.setStatusPagamento(true);
    pagamento.setValor(valorBase);
    pagamento.setValorPago(valorPago);
    pagamento.setPercentualDesconto(desconto);
    pagamento.setMes(mes);
    pagamento.setDataPagamento(LocalDate.now());

    PagamentoDAO dao = new PagamentoDAO();
    dao.create(pagamento);
}

private int obterIdMembroPlanoAtual(int idMembro) throws SQLException {
    String sql = "SELECT id_memplan FROM membro_plano WHERE id_membro = ? ORDER BY id_memplan DESC LIMIT 1";
    try (Connection conn = ConexaoDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idMembro);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_memplan");
        } else {
            throw new SQLException("Membro não tem relacionamento ativo com plano.");


}
         }
        }
}
