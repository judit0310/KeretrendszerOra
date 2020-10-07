package uni.eszterhazy.keretrendszer.dao.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uni.eszterhazy.keretrendszer.dao.DolgozoDAO;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.Reszleg;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoNotFound;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DolgozoDAOJSON implements DolgozoDAO {
    File jsonFile;
    ObjectMapper mapper;

    public DolgozoDAOJSON(String jsonFilePath) throws IOException {
        jsonFile= new File(jsonFilePath);
        if(!jsonFile.exists()){
            jsonFile.createNewFile();
            FileWriter writer = new FileWriter(jsonFile);
            writer.write("[]");
            writer.flush();
            writer.close();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public void createDolgozo(Dolgozo dolgozo) throws DolgozoAlreadyAdded {
        Collection<Dolgozo> dolgozok = readAllDolgozo();

        Dolgozo result = null;
        try {
            result = readDolgozo(dolgozo.getId());
        } catch (DolgozoNotFound dolgozoNotFound) {
            dolgozok.add(dolgozo);
            try {
                mapper.writeValue(jsonFile, dolgozok);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new DolgozoAlreadyAdded(dolgozo.getId());
    }

    public Collection<Dolgozo> readAllDolgozo() {
        Collection<Dolgozo> dolgozok = new ArrayList<Dolgozo>();
        TypeReference type = new TypeReference<ArrayList<Dolgozo>>() {};
        try {
            dolgozok = mapper.readValue(jsonFile,type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dolgozok;
    }

    public Dolgozo readDolgozo(String id) throws DolgozoNotFound {
        Collection<Dolgozo> dolgozok = readAllDolgozo();
        for (Dolgozo d: dolgozok){
            if(d.getId().equalsIgnoreCase(id)){
                return d;
            }
        }
        throw new DolgozoNotFound(id);
    }

    public void updateDolgozo(Dolgozo dolgozo) {

    }

    public void deleteDolgozo(Dolgozo dolgozo) {

    }

    public Collection<Dolgozo> readAllDolgozoOfReszleg(Reszleg reszleg) {
        return null;
    }
}
