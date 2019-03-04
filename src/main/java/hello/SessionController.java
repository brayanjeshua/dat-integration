package hello;

import hello.models.*;
import hello.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import org.bson.types.ObjectId;

@RestController
@RequestMapping("/session")
public class SessionController {
  @Autowired
  private SessionRepository repository;

  @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
  public List<Session> getAllSession() {
    return repository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Session getSessionById(@PathVariable("id") ObjectId id) {
    return repository.findBy_id(id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public void modifySessionById(@PathVariable("id") ObjectId id, @Valid @RequestBody Session Session) {
    Session.set_id(id);
    repository.save(Session);
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Session createSession(@Valid @RequestBody Session Session) {
    Session.set_id(ObjectId.get());
    repository.save(Session);
    return Session;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteSession(@PathVariable ObjectId id) {
    repository.delete(repository.findBy_id(id));
  }
}
