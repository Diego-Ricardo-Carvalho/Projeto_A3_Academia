package Controller;

import JDBC.ConexaoDB;
import Model.Exercicio;
import java.sql.SQLException;

public class ExerciciosDAO extends FazTudo<Exercicio>{
public ExerciciosDAO(){
    super(Exercicio.class);
}
public void registrarExercicio(String nome, String maquina, String grupoMuscular)throws SQLException {
    Exercicio novoExercicio = new Exercicio();
    novoExercicio.setNome(nome);
    novoExercicio.setMaquina(maquina);
    novoExercicio.setGrupoMuscular(grupoMuscular);
    this.create(novoExercicio);
}

}

