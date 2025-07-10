
package Controller;

public class CboxExercicio extends CboxPai {
    
    @Override
    protected String getSQL() {
        return "SELECT nome FROM exercicios ORDER BY nome";
    }

    @Override
    protected String getMensagemErro() {
        return "Erro ao carregar exercícios";
    }
    
}
