package uni.eszterhazy.keretrendszer.dao.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import uni.eszterhazy.keretrendszer.dao.DolgozoDAO;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoNotFound;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.Reszleg;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

public class DolgozoDAOMongo implements DolgozoDAO {
    private MongoClient client;
    private DB db;
    private DBCollection collection;


    public DolgozoDAOMongo(String uri, String database, String collection) throws UnknownHostException {
        this.client = new MongoClient(new MongoClientURI(uri));
        this.db = client.getDB(database);
        this.collection = db.getCollection(collection);
    }

    public void createDolgozo(Dolgozo dolgozo) throws DolgozoAlreadyAdded {
        try {
            readDolgozo(dolgozo.getId());
        } catch (DolgozoNotFound dolgozoNotFound) {
            collection.insert(DolgozoAdapter.dolgozoToDBObject(dolgozo));
            return;
        }
       throw new DolgozoAlreadyAdded(dolgozo.getId());
    }

    public Collection<Dolgozo> readAllDolgozo() {
        DBCursor cursor = collection.find(new BasicDBObject(), new BasicDBObject().append("_id",0));
        Collection<Dolgozo> result = new ArrayList<>();
        cursor.forEach(o->{
            result.add(DolgozoAdapter.dbObjectToDolgozo(o));
        });
        return result ;
    }

    public Dolgozo readDolgozo(String id) throws DolgozoNotFound {
        DBCursor cursor = collection.find(new BasicDBObject().append("id",id), new BasicDBObject().append("_id",0));
        if(cursor.length()==0){
            throw new DolgozoNotFound(id);
        }
        return DolgozoAdapter.dbObjectToDolgozo(cursor.one());
    }

    public void updateDolgozo(Dolgozo dolgozo) {

    }

    public void deleteDolgozo(Dolgozo dolgozo) {

    }

    public Collection<Dolgozo> readAllDolgozoOfReszleg(Reszleg reszleg) {
        return null;
    }


}
