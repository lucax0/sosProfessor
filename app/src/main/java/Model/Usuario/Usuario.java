package Model.Usuario;

import java.util.Date;

public abstract class Usuario {

    protected int id;
    protected String email;
    protected String senha;
    protected String nome;
    protected Date dtNasc;
    protected char sexo;
    protected char[] cep = new char[9];
    protected char[] cel = new char[9];

    public Usuario(int id, String email, String senha, String nome, Date dtNasc, char sexo, char[] cep, char[] cel) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.sexo = sexo;
        this.cep = cep;
        this.cel = cel;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char[] getCep() {
        return cep;
    }

    public void setCep(char[] cep) {
        this.cep = cep;
    }

    public char[] getCel() {
        return cel;
    }

    public void setCel(char[] cel) {
        this.cel = cel;
    }
}
