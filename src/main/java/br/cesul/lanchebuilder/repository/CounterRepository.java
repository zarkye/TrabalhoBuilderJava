package br.cesul.lanchebuilder.repository;

import br.cesul.lanchebuilder.config.MongoConfig;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class CounterRepository {
    private final MongoCollection<Document> col = MongoConfig.db.getCollection("counters");
    public long getNextPedidoId() {
        Document result = col.findOneAndUpdate(
                Filters.eq("_id", "pedidoId"),
                Updates.inc("seq", 1),
                new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true)
        );
        assert result != null;

        Number seq = result.get("seq", Number.class); // pega como Number
        return seq.longValue();
    }
}
