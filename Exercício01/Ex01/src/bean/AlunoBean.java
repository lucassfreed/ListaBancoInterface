package bean;

public class AlunoBean {
    
    private int id;
    private byte frequencia;
    private float nota1, nota2, nota3, media;
    private String nome, codigoMatricula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(byte frequencia) {
        this.frequencia = frequencia;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getNota3() {
        return nota3;
    }

    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(String codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }
}
