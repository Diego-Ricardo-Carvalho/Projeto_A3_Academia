
package Controller;


import View.InterfaceInicial;
import View.TelaGerente;
import View.TelaProfessor;
import View.TelaSecretaria;
import java.sql.*;
import javax.swing.JOptionPane;

public class Logar {
    private FuncionariosDAO funcionariosDAO;
    TelaGerente telagen = new TelaGerente();
    InterfaceInicial tela1 = new InterfaceInicial();
    TelaProfessor telapof = new TelaProfessor();
    TelaSecretaria tesec = new TelaSecretaria();
    public Logar(FuncionariosDAO funcionariosDAO) {
        this.funcionariosDAO = funcionariosDAO;
    }

    public void realizarLogin(String login, String senha) {
        try {
            String cargo = funcionariosDAO.autenticarFuncionario(login, senha);
            if (cargo != null) {
                switch (cargo) {
                    case "Gerente":
                    CJanela.abrir(telagen, "Tela do Gerente", tela1);
                   
                        break;
                    case "Secretário":
                      CJanela.abrir(tesec, "Tela da Secretaria", tela1);
                        break;
                    case "Personal Trainer":
                    CJanela.abrir(telapof, "Tela do Personal Trainer", tela1);
                        break;
                        case "Assistente Geral":
                        JOptionPane.showMessageDialog(null, "VOCÊ NÃO TEM ACESSO AO SISTEMA, RECOMENDO SAIR AGORA! ");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Cargo não reconhecido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha incorretos, Tente novamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao autenticar: " + e.getMessage());
        }
    }
}

