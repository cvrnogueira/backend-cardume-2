package hello;
import com.google.gson.annotations.SerializedName;
import org.codehaus.jackson.annotate.JacksonAnnotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Event {
    private Date data_fim;
    private Date data_inicio;
    private String descricao;
    private String dono;
    private long duracao;
    private String id;
    private int moedas;
    private String titulo;
    private int numero_voluntarios;
    private List<String> voluntarios;

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

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
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

    public List<String> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(List<String> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public boolean addVolunteers(String volunteer) {
        if(getVoluntarios() == null){
            setVoluntarios(new ArrayList<>());
        }
        if(getVoluntarios().size() < getNumero_voluntarios()){
            getVoluntarios().add(volunteer);
            return true;
        }
        else{
            return false;
        }
    }
}