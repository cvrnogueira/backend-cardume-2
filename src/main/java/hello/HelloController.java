package hello;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public HashMap<String, String> confirmationEvent(@RequestBody Checkin checkin) throws Exception {
        DocumentReference docRef = FirestoreClient.getFirestore().collection("eventos").document(checkin.getId());

        Event event = docRef.get().get().toObject(Event.class);
        if (!event.addVolunteers(checkin.getEmail())) {
            throw new Exception("Numero de chamadas assumidas");
        } else {
            docRef.set(event).get();

            byte[] data =  (checkin.getEmail() + "_" + event.getMoedas()).getBytes();
            String base64 = Base64.getEncoder().encodeToString(data);

            HashMap<String, String> map = new HashMap<>();
            map.put("token", base64);

            return map;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/registerUser")
    public WriteResult registerUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        CollectionReference docRef = FirestoreClient.getFirestore().collection("pessoas");
        return docRef.document(user.getEmail()).set(user).get();

    }


}
