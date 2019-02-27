package hello;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public Login login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
            @RequestParam(value = "name", defaultValue = "World") String name) throws RemoteException {

        return new Login(email, password);
    }
}