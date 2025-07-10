
package Controller;

import JDBC.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public abstract class CboxPai {
    protected abstract String getMensagemErro();
    protected abstract String getSQL();
    public void preencherComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(getSQL());
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                if (nome != null && !nome.trim().isEmpty()) {
                    comboBox.addItem(nome);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                getMensagemErro() + ": " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

