package hello;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    MockedData mocked = new MockedData();
    @CrossOrigin(origins = "*")
        @RequestMapping("/eventos")
    public Evento index() {
        return mocked.getEvent();
    }

}
