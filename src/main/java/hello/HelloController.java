package hello;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/eventos")
public class HelloController {

    @CrossOrigin(origins = "*")
    @GetMapping("/lista")
    public Map<String, Object> listOfEvents() throws ExecutionException, InterruptedException {
        DocumentReference docRef = FirestoreClient.getFirestore().collection("cardume").document("eventos");

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        return document.getData();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/checkin")
    public Map<String, Object> confirmationEvent(@RequestAttribute("id") String id, @RequestAttribute("email") String email) throws ExecutionException, InterruptedException {

        DocumentReference docRef = FirestoreClient.getFirestore().collection("cardume").document("eventos");

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        return document.getData();
    }

}
