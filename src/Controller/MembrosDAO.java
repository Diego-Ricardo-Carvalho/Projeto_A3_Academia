package Controller;

import Model.Membro;
import java.sql.SQLException;
import java.time.LocalDate;
public class MembrosDAO extends FazTudo<Membro> {
    public MembrosDAO(){
        super(Membro.class);
    }
    public void cadastrarAluno(String nome, String cpf, String telefone, String endereco, int id_plano) throws SQLException {
        Membro novoMembro = new Membro();
        novoMembro.setNome(nome);
        novoMembro.setCpf(cpf);
        novoMembro.setTelefone(telefone);
        novoMembro.setEndereco(endereco);
        novoMembro.setData_cadastro(LocalDate.now());
        novoMembro.setId_plano(id_plano);
        
        
        // Usa o método create herdado de FazTudo
        this.create(novoMembro);
    }
}

