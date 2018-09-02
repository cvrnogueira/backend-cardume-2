package hello;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
    public Map<String, Object> confirmationEvent(@RequestAttribute("id") String id, @RequestAttribute("email") String email) throws ExecutionException, InterruptedException {
//adiciona a pessoa na fila, soma nro de moedas dela
        CollectionReference docRef = FirestoreClient.getFirestore().collection("eventos");

        docRef.document(id).set().get();
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        return document.getData();
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/registerUser")
    public WriteResult registerUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        CollectionReference docRef = FirestoreClient.getFirestore().collection("pessoas");
        return docRef.document(user.getEmail()).set(user).get();

    }


}
