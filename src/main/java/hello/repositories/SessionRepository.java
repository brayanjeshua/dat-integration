package hello.repositories;

import hello.models.Session;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, String> {
  Session findBy_id(ObjectId _id);
}