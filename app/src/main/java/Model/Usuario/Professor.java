package Model.Usuario;

import java.util.Date;
import java.util.LinkedList;

public class Professor extends Usuario {

    private float nota;
    private LinkedList<String> graduacao;

    public Professor(String id, String email, String senha, String nome, Date dtNasc, String sexo, String cep, String cel, float nota, LinkedList<String> graduacao) {
        super(id, email, senha, nome, dtNasc, sexo, cep, cel);
        this.nota = nota;
        this.graduacao = graduacao;
    }

    public Professor(float nota, LinkedList<String> graduacao) {
        this.nota = nota;
        this.graduacao = graduacao;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public LinkedList<String> getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(LinkedList<String> graduacao) {
        this.graduacao = graduacao;
    }
}
