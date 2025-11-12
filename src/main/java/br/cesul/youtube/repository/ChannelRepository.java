package br.cesul.youtube.repository;

import br.cesul.youtube.config.MongoConfig;
import br.cesul.youtube.model.Channel;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Sorts.descending;

public class ChannelRepository {
    private final MongoCollection<Channel> col = MongoConfig.db.getCollection("canais", Channel.class);

    public Channel findByName(String name) {
        return col.find(Filters.eq("name", name)).first();
    }

    public void insert(Channel c){
        col.insertOne(c);
    }

    public ObservableList<Channel> findAll(){
        var list = FXCollections.<Channel>observableArrayList();
        col.find().sort(descending()).forEach(list::add);
        return list;
    }

    public void updateSubscribers(String name, int subscribers) {
        Bson filter = Filters.eq("name", name);
        Bson update = Updates.set("subscribers", subscribers);
        col.updateOne(filter, update);
    }

    public void incrementSubscribers(String name) {
        Bson filter = Filters.eq("name", name);
        Bson update = Updates.inc("subscribers", 1);
        col.updateOne(filter, update);
    }
}
