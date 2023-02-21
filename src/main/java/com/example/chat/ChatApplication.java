package com.example.Codeminechat;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CodemineChatApplication {

	public static void main(String[] args) throws IOException{
		
		ClassLoader classLoader=CodemineChatApplication.class.getClassLoader();
		
		File file=new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
		FileInputStream serviceAccount=new FileInputStream(file.getAbsolutePath());
		
		@SuppressWarnings("deprecation")
		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		  .setDatabaseUrl("https://fir-tutorial-72992-default-rtdb.firebaseio.com")
		  .build();
		
		FirebaseApp.initializeApp(options);

		
		SpringApplication.run(CodemineChatApplication.class, args);
	}

}
