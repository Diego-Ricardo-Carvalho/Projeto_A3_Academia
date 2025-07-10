
package Controller;

public class CboxAluno extends CboxPai {
    
    @Override
    protected String getSQL() {
        return "SELECT nome FROM membros ORDER BY nome";
    }

    @Override
    protected String getMensagemErro() {
        return "Erro ao carregar alunos";
    }
    
}
