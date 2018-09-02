package hello;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private String email;
    private int moeda;
    private String name;
    private String telefone;
    private String senha;
    private List<CheckinToEvent> checkin = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoeda() {
        return moeda;
    }

    public void setMoeda(int moeda) {
        this.moeda = moeda;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<CheckinToEvent> getCheckin() {
        return checkin;
    }

    public void setCheckin(List<CheckinToEvent> checkin) {
        this.checkin = checkin;
    }

    public void addCheckInToEventos(Date data_fim, Date data_inicio, String endereco, String id, String titulo, String token) {
        getCheckin().add(new CheckinToEvent(data_fim, data_inicio, endereco, id, titulo, token));
    }
}
