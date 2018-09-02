package hello;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;
public class Event {
    @SerializedName("data_fim")
    private Date endDate;
    @SerializedName("data_inicio")
    private Date startDate;
    @SerializedName("descricao")
    private String description;
    @SerializedName("dono")
    private String owner;
    @SerializedName("duracao")
    private long duration;
    @SerializedName("id")
    private int id;
    @SerializedName("moedas")
    private int points;
    @SerializedName("titulo")
    private String title;
    @SerializedName("numero_voluntarios")
    private int n_volunteers;
    @SerializedName("voluntarios")
    private List<String> volunteers;
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public long getDuration() {
        return duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getN_volunteers() {
        return n_volunteers;
    }
    public void setN_volunteers(int n_volunteers) {
        this.n_volunteers = n_volunteers;
    }
    public List<String> getVolunteers() {
        return volunteers;
    }
    public void setVolunteers(List<String> volunteers) {
        this.volunteers = volunteers;
    }
}