package uni.eszterhazy.keretrendszer.dao.mongo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import uni.eszterhazy.keretrendszer.model.Dolgozo;

import java.io.IOException;

public class DolgozoAdapter {
    private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    public static Dolgozo dbObjectToDolgozo(DBObject dolgozo){
        try {
            Dolgozo obj = mapper.readValue(dolgozo.toString(),Dolgozo.class);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static DBObject dolgozoToDBObject(Dolgozo dolgozo){
        String dolgozoString= "";
        try {
            dolgozoString = mapper.writeValueAsString(dolgozo);
            BasicDBObject obj = mapper.readValue(dolgozoString, BasicDBObject.class);
            return obj;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BasicDBObject();
    }
}
