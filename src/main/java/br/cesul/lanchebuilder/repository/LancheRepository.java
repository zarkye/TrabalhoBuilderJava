package br.cesul.lanchebuilder.repository;

import br.cesul.lanchebuilder.config.MongoConfig;
import br.cesul.lanchebuilder.model.Lanche;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;

import static com.mongodb.client.model.Sorts.descending;

public class LancheRepository {
    private final MongoCollection<Lanche> col = MongoConfig.db.getCollection("lanches", Lanche.class);

    public ObservableList<Lanche> findAll(){
        var list = FXCollections.<Lanche>observableArrayList();
        col.find().sort(descending()).forEach(list::add);
        return list;
    }
    public void insert(Lanche l){
        col.insertOne(l);
    }
}
