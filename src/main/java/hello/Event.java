package hello;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    private Date data_fim;
    private Date data_inicio;
    private String descricao;
    private String endereco;
    private String id;
    private int moedas;
    private String titulo;
    private String email;
    private String telefone;
    private int numero_voluntarios;
    private List<String> voluntarios = new ArrayList<>();

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumero_voluntarios() {
        return numero_voluntarios;
    }

    public void setNumero_voluntarios(int numero_voluntarios) {
        this.numero_voluntarios = numero_voluntarios;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(List<String> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public void addVolunteers(String volunteer) throws Exception {
        if (getVoluntarios().contains(volunteer))
            throw new Exception("Usuário já registrado no evento.");

        if (getVoluntarios().size() >= getNumero_voluntarios())
            throw new Exception("Número de chamadas assumidas.");

        getVoluntarios().add(volunteer);
    }
}