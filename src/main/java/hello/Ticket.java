package hello;

import com.google.cloud.firestore.CollectionReference;
import com.google.firebase.cloud.FirestoreClient;

import java.util.concurrent.ExecutionException;

public class Ticket {

    public static Ticket builder(User user, Reward reward) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = FirestoreClient.getFirestore().collection("ticket");

        int id = collectionReference.get().get().getDocuments().size() + 1;

        Ticket ticket = new Ticket();
        ticket.id = String.valueOf(id);
        ticket.title = "CUPOM-" + id;
        ticket.userName = user.getName();
        ticket.userEmail = user.getEmail();
        ticket.rewardId = reward.getId();
        ticket.rewardTitle = reward.getTitulo();
        ticket.rewardDescription = reward.getDescricao();

        collectionReference.document(ticket.id).set(ticket).get();

        return ticket;
    }

    private Ticket() { }

    private String id;
    private String title;
    private String userName;
    private String userEmail;
    private String rewardId;
    private String rewardTitle;
    private String rewardDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
