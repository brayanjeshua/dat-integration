package hello;

import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcore.tcoreTypes.SessionToken;
import com.transcore.connexion.sample.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final Login loginclient = new Login();
    
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="email") String email,
    		@RequestParam(value="password") String password,
    		@RequestParam(value="name", defaultValue="World") String name) throws RemoteException {
    	
    	final SessionToken token = loginclient.log(email, password); 
    	System.out.println(token);
    	
    	return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
