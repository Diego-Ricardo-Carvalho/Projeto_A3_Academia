package Model;
public class Treino {
private String nome, pesoRecomendado, diaDaSemana;
private int id, idExercicio, repeticoes, series;
public Treino(String nm, String pr, String dds, int idEx, int rep, int ser){
    this.nome = nm;
    this.pesoRecomendado = pr;
    this.diaDaSemana = dds;
    this.idExercicio = idEx;
    this.repeticoes = rep;
    this.series = ser;
}
}
