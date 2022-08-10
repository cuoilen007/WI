package fptaptech.com.WIWebApp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WiWebAppApplication {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        ClassLoader loader = WiWebAppApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(loader.getResource("ServiceAccountkey.json")).getFile());
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);

        SpringApplication.run(WiWebAppApplication.class, args);
    }

}
