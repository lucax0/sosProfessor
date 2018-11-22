package Model.Usuario;

import java.util.Date;

public class Aluno extends Usuario {
    protected float indiceFalta;
//CONSTRUTORES

    public Aluno(int id, String email, String senha, String nome, Date dtNasc, char sexo, String cep, String cel, float indiceFalta) {
        super(id, email, senha, nome, dtNasc, sexo, cep, cel);
        this.indiceFalta = indiceFalta;
    }

    public Aluno(float indiceFalta) {
        this.indiceFalta = indiceFalta;
    }
//CONSTRUTORES
    public float getIndiceFalta() {
        return indiceFalta;
    }

    public void setIndiceFalta(float indiceFalta) {
        this.indiceFalta = indiceFalta;
    }

}
