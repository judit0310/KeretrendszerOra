package uni.eszterhazy.keretrendszer.dao;

import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.Reszleg;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoNotFound;

import java.util.Collection;

public interface DolgozoDAO {
    void createDolgozo(Dolgozo dolgozo) throws DolgozoAlreadyAdded;
    Collection<Dolgozo> readAllDolgozo();
    Dolgozo readDolgozo(String id) throws DolgozoNotFound;
    void updateDolgozo(Dolgozo dolgozo);
    void deleteDolgozo(Dolgozo dolgozo);
    Collection<Dolgozo> readAllDolgozoOfReszleg(Reszleg reszleg);
}
