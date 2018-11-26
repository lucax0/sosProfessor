package Model.Usuario;

import java.util.Date;

public class Professor extends Usuario {

    private float nota;
    private String graduacao;

    public Professor(String id, String email, String senha, String nome, Date dtNasc, String sexo, String cep, String cel, float nota, String graduacao) {
        super(id, email, senha, nome, dtNasc, sexo, cep, cel);
        this.nota = nota;
        this.graduacao = graduacao;
    }

    public Professor(float nota, String graduacao) {
        this.nota = nota;
        this.graduacao = graduacao;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(String graduacao) {
        this.graduacao = graduacao;
    }
}
