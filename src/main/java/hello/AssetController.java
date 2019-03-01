package hello;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssetController {
    @RequestMapping("/asset")
    public String asset(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
            @RequestParam(value = "name", defaultValue = "World") String name) throws RemoteException {

        return "message";
    }
}