package infrastructure;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import domain.entities.User;
import domain.ports.UserRepository;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongodbUserRepositoryImpl implements UserRepository<ObjectId> {

    private final MongoCollection<Document> collection;

    public MongodbUserRepositoryImpl(String uri, String databaseName, String collectionName){
            this.collection = MongoClients.create(uri)
                    .getDatabase(databaseName)
                    .getCollection(collectionName);
    }

    @Override
    public Optional<User<ObjectId>> findById(ObjectId id) {
        Document document = this.collection.find(new Document("_id", id)).first();
        if (document != null) return Optional.of(mapEntityFromDocument(document));
        return Optional.empty();
    }

    @Override
    public void save(User<ObjectId> value) {
       Document document = new Document("username", value.getUserName());
       this.collection.insertOne(document);
    }

    @Override
    public void update(ObjectId id, User<ObjectId> value) {
        this.collection.updateOne(Filters.eq("_id", id), Updates.set("username", value.getUserName()));
    }

    @Override
    public void delete(ObjectId id) {
        this.collection.deleteOne(new Document("_id", id));
    }

    @Override
    public List<User<ObjectId>> findAll() {
        List<User<ObjectId>> userList = new ArrayList<>();
        try (MongoCursor<Document> cursor = this.collection.find().iterator()) {
            while (cursor.hasNext()) {
                userList.add(mapEntityFromDocument(cursor.next()));
            }
        }
        return userList;
    }

    private User<ObjectId> mapEntityFromDocument(Document document){
        User<ObjectId> user = new User<>();
        user.setId(document.getObjectId("_id"));
        user.setUserName(document.getString("username"));
        return user;
    }
}
