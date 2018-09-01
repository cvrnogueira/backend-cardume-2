package hello;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class HelloController {

    @CrossOrigin(origins = "*")
        @RequestMapping("/eventos")
    public Map<String, Object> index() throws ExecutionException, InterruptedException {
        DocumentReference docRef = FirestoreClient.getFirestore().collection("cardume").document("eventos");

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        return document.getData();
    }

}
