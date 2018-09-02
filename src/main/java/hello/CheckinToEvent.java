package hello;

import java.util.Date;

public class CheckinToEvent {

    private Date data_fim;
    private Date data_inicio;
    private String endereco;
    private String id;
    private String titulo;
    private String token;

    public CheckinToEvent() {
    }

    public CheckinToEvent(Date data_fim, Date data_inicio, String endereco, String id, String titulo, String token) {
        this.data_fim = data_fim;
        this.data_inicio = data_inicio;
        this.endereco = endereco;
        this.id = id;
        this.titulo = titulo;
        this.token = token;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}