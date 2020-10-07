package uni.eszterhazy.keretrendszer.service.impl;

import org.apache.log4j.Logger;
import uni.eszterhazy.keretrendszer.dao.DolgozoDAO;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoNotFound;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.Reszleg;
import uni.eszterhazy.keretrendszer.service.DolgozoService;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoAlreadyAdded;

import java.util.Collection;
import java.util.Map;

public class DolgozoServiceImpl implements DolgozoService {
    Logger logger = Logger.getLogger(this.getClass());
    private DolgozoDAO dao;

    public DolgozoServiceImpl(DolgozoDAO dao) {
        this.dao = dao;
    }

    @Override
    public void addDolgozo(Dolgozo dolgozo) throws DolgozoAlreadyAdded {
        dao.createDolgozo(dolgozo);
    }

    @Override
    public Collection<Dolgozo> getAllDolgozo() {
        Collection<Dolgozo> result = dao.readAllDolgozo();
        logger.info("Az adatbazisban "+result.size()+" dolgozo van felveve");
        return result;
    }

    @Override
    public Dolgozo getDolgozoById(String id) {
        try {
            return dao.readDolgozo(id);
        } catch (DolgozoNotFound dolgozoNotFound) {
            dolgozoNotFound.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateDolgozo(Dolgozo dolgozo) {

    }

    @Override
    public void removeDolgozo(Dolgozo dolgozo) {

    }

    @Override
    public Collection<Dolgozo> readAllDolgozoOfReszleg(Reszleg reszleg) {
        return null;
    }

    @Override
    public double atlagFizetes() {
        Collection<Dolgozo> dolgozok = getAllDolgozo();
      /*  double sum=0;
        for (Dolgozo d : dolgozok){
            sum += d.getFizetes();
        }
        return sum / dolgozok.size();*/

        return dolgozok.stream().mapToDouble(d ->d.getFizetes()).average().getAsDouble();
    }

    @Override
    public Map<Reszleg, Double> atlagFizetesReszlegenkent() {
        return null;
    }
}
