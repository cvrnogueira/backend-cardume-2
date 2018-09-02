package hello;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eventos")
public class HelloController {

    @CrossOrigin(origins = "*")
    @GetMapping("/lista")
    public List<Event> listOfEvents() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> docRef = FirestoreClient.getFirestore().collection("eventos").get();
        return docRef.get().toObjects(Event.class);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/lista/{email}")
    public List<Event> eventsOfUser(@PathVariable String email) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = FirestoreClient.getFirestore().collection("eventos");

        Query query = collectionReference.whereGreaterThanOrEqualTo("email", email);

        return query.get().get().toObjects(Event.class);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/checkin")
    public void confirmationEvent(@RequestBody Checkin checkin) throws Exception {
        DocumentReference docRef = FirestoreClient.getFirestore().collection("eventos").document(checkin.getId());

        Event event = docRef.get().get().toObject(Event.class);
        event.addVolunteers(checkin.getEmail());

        DocumentReference docRef2 = FirestoreClient.getFirestore().collection("pessoas").document(checkin.getEmail());
        User user = docRef2.get().get().toObject(User.class);

        if(user == null)
            throw new Exception("Usuário não existe na base!");

        byte[] data =  (checkin.getEmail() + "_" + event.getMoedas()).getBytes();
        String base64 = Base64.getEncoder().encodeToString(data);

        user.addCheckInToEventos(
                event.getData_fim(),
                event.getData_inicio(),
                event.getEndereco(),
                event.getId(),
                event.getTitulo(),
                base64);

        docRef.set(event).get();

        docRef2.set(user).get();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/registerUser")
    public WriteResult registerUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        CollectionReference docRef = FirestoreClient.getFirestore().collection("pessoas");
        return docRef.document(user.getEmail()).set(user).get();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public User login(@RequestBody User user) throws Exception {
        DocumentReference docRef = FirestoreClient.getFirestore().collection("pessoas").document(user.getEmail());

        User mUser = docRef.get().get().toObject(User.class);

        if(mUser == null)
            throw new Exception("Usuário já registrado no evento.");
        else {
            if(!mUser.getSenha().equalsIgnoreCase(user.getSenha()))
                throw new Exception("Usuário ou senha inválidos.");
            else
                return mUser;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getPointsNumber")
    public int getPointNumber(@RequestParam String userId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = FirestoreClient.getFirestore().collection("pessoas").document(userId);
        return docRef.get().get().toObject(User.class).getMoeda();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/novoEvento")
    public WriteResult addEvent(@RequestBody Event event) throws ExecutionException, InterruptedException {
        CollectionReference docRef = FirestoreClient.getFirestore().collection("eventos");

        int newId = docRef.get().get().getDocuments().size() + 1;

        event.setId(String.valueOf(newId));

        return docRef.document(event.getId()).set(event).get();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/adicionarPontos/{token}")
    public void addPoints(@PathVariable String token) throws Exception {

        byte[] data = Base64.getDecoder().decode(token);
        String text = new String(data);

        String[] content = text.split("_");

        DocumentReference docRef = FirestoreClient.getFirestore().collection("pessoas").document(content[0]);
        User user = docRef.get().get().toObject(User.class);

        if(user == null)
            throw new Exception("Usuário não existe na base!");

        CheckinToEvent mCheckinToEvent = null;

        for (CheckinToEvent checkinToEvent : user.getCheckin()) {
            if(checkinToEvent.getToken().equals(token)) {
                mCheckinToEvent = checkinToEvent;
                break;
            }
        }

        if(mCheckinToEvent == null)
            throw new Exception("Token inválido.");

        int total = user.getMoeda() + Integer.parseInt(content[1]);

        user.setMoeda(total);

        user.getCheckin().remove(mCheckinToEvent);

        docRef.set(user).get();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/premios")
    public List<Reward> rewards() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> docRef = FirestoreClient.getFirestore().collection("premios").get();
        return docRef.get().toObjects(Reward.class);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/trocar")
    public Ticket exchange(@RequestBody Exchange exchange) throws Exception {
        DocumentReference docRef = FirestoreClient.getFirestore().collection("premios").document(exchange.getRewardId());
        Reward reward = docRef.get().get().toObject(Reward.class);

        DocumentReference docRef2 = FirestoreClient.getFirestore().collection("pessoas").document(exchange.getUserId());
        User user = docRef2.get().get().toObject(User.class);

        if(reward == null || user == null)
            throw new Exception("Não foi possível efetuar a troca.");

        int points = user.getMoeda() - reward.getValor();

        if(points < 0)
            throw new Exception("Saldo insuficiente.");

        user.setMoeda(points);

        docRef2.set(user).get();

        return Ticket.builder(user, reward);
    }
}
