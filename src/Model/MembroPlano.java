package Model;

public class MembroPlano {
private int idMemplan, idMembro, idPlano;

public MembroPlano(){}

public MembroPlano(int idMemplan, int idMembro, int idPlano){
    this.idMembro = idMembro;
    this.idMemplan = idMemplan;
    this.idPlano = idPlano;
}

    public int getIdMemplan() {
        return idMemplan;
    }

    public void setIdMemplan(int idMemplan) {
        this.idMemplan = idMemplan;
    }

    public int getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(int idMembro) {
        this.idMembro = idMembro;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }


}
