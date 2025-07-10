package Model;

public class Funcionario {
private String nome, cpf, login, senha, cargo;
private int id;

public Funcionario(){}

public Funcionario(String nome, String cpf, String login, String senha, String cargo){
    this.nome = nome;
    this.cpf = cpf;
    this.login = login;
    this.senha = senha;
    this.cargo = cargo;
}
public String getNome() {
    return nome;
}
public String getCpf() {
    return cpf;
}
public String getLogin() {
    return login;
}
public String getSenha() {
    return senha;
}public String getCargo() {
    return cargo;
}
public int getId() {
    return id;
}
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setId(int id) {
        this.id = id;
    }

}
