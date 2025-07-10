package Controller;

public class cboxTreinos extends CboxPai {
      @Override
    protected String getSQL() {
        return "SELECT nome FROM treinos ORDER BY nome";
    }

    @Override
    protected String getMensagemErro() {
        return "Erro ao carregar treinos.";
    }
}
