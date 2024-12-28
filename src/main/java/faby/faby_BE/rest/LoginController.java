package faby.faby_BE.rest;

import faby.faby_BE.dto.ApiResponseDTO;
import faby.faby_BE.entity.Student;
import faby.faby_BE.entity.User;
import faby.faby_BE.service.StudentService;
import faby.faby_BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Autowired
    private UserService userService;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/helloLogin")
    public String sayHello(){
        System.out.println("hello login");
        return "Hello login - " + applicationName;
    }

    @PostMapping("/signupUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("---SignupUser---");
        User userCreated = userService.saveUser(user);
        System.out.println("User created: " + userCreated);
        // Restituisci lo studente salvato avvolto in un ResponseEntity con lo stato HTTP 201 (Created)
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }



    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@RequestBody User user) {
        try {
            // Autenticazione dell'utente
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            // Se l'autenticazione ha successo, imposta il contesto di sicurezza
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Restituisci un messaggio di successo con stato 200
            return ResponseEntity.ok(new ApiResponseDTO(true, "Login effettuato con successo!"));
        }
        catch (BadCredentialsException e) {
            // Messaggio chiaro se le credenziali sono errate
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDTO(false, "Errore di login: Credenziali non valide."));
        }
        catch (Exception e) {
            // Restituisci un errore generico per altre eccezioni
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDTO(false, "Errore di login: Si è verificato un errore imprevisto."));
        }
    }



}
