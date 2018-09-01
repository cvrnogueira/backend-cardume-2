package hello;

import java.util.Date;

public class Evento {


    private String description;
    private String titulo;
    private Date inicio;
    private Date fim;
    private String adress;
    private long duration;

    public Evento(String description, String titulo, Date inicio, Date fim, String adress, long duration) {
        this.description = description;
        this.titulo = titulo;
        this.inicio = inicio;
        this.fim = fim;
        this.adress = adress;
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
