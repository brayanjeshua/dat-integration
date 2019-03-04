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
	public Session login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
			@RequestParam(value = "name", defaultValue = "World") String name) throws RemoteException {

        SessionToken login = new Login(email, password).getToken();
        Session session = repository.findBy_id(new ObjectId("5c7c7b01030b7432c5b23e9b"));
        session.setToken(login.toString());
        return repository.save(session);
	}
}