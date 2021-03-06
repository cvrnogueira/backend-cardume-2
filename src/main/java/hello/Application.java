package hello;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        try {


            FileInputStream serviceAccount = new FileInputStream(massageWhitespace("serviceAccountKey.json"));



            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://cardume-2018.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

        }catch (IOException e) {
            System.out.println("Error");
            //Log.error("error " + e);
        }

    }

    private static String massageWhitespace(String s) {
        String newString = "";
        for (Character c : s.toCharArray()) {
            if ("00a0".equals(Integer.toHexString(c | 0x10000).substring(1))) {
                newString += " ";
            } else {
                newString += c;
            }
        }
        return newString;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }


        };

    }


}
