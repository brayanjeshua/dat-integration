package hello;

import java.rmi.RemoteException;
import java.util.List;

import com.tcore.tcoreTypes.SessionToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.repositories.SessionRepository;
import hello.models.*;
import org.bson.types.ObjectId;

@RestController
public class LoginController {
    @Autowired
    private SessionRepository repository;

    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password)
           throws RemoteException {
        new Login(email, password);
		SessionToken token = Login.sessionToken;

        Session session = repository.findBy_id(new ObjectId("5c7c7b01030b7432c5b23e9b"));
        session.setToken(token.toString());
        repository.save(session);
        
        return token.toString();
    }
}