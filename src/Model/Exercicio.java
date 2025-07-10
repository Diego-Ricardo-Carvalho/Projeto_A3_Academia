package Model;
public class Exercicio{
private String nome, maquina, grupoMuscular;
private int id;

public Exercicio(){}

public Exercicio(String nome, String maquina, String grupoMuscular){
this.nome = nome;
this.maquina = maquina;
this.grupoMuscular = grupoMuscular;
}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}