package hello;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
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

}
