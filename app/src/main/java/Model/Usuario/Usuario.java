package Model.Usuario;

import java.util.Date;

public abstract class Usuario {

    protected String id;
    protected String email;
    protected String senha;
    protected String nome;
    protected Date dtNasc;
    protected String sexo;
    protected String cep;
    protected String cel;

    public Usuario(String id, String email, String senha, String nome, Date dtNasc, String sexo, String cep, String cel) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }
}
