package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    MockedData mocked = new MockedData();
        @RequestMapping("/eventos")
    public Evento index() {
        return mocked.getEvent();
    }
    
}
