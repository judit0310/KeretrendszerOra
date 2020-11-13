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
import java.util.stream.Collectors;

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
    public Dolgozo getDolgozoById(String id) throws DolgozoNotFound {
            return dao.readDolgozo(id);
    }

    @Override
    public void updateDolgozo(Dolgozo dolgozo) {

    }

    @Override
    public void removeDolgozo(Dolgozo dolgozo) {

    }

    @Override
    public Collection<Dolgozo> readAllDolgozoOfReszleg(Reszleg reszleg) {
        Collection<Dolgozo> dolgozok = getAllDolgozo();
        Collection<Dolgozo> result = dolgozok.stream().filter(d -> d.getReszleg() == reszleg).collect(Collectors.toList());
        return result;
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
    public Collection<Dolgozo> getAllDolgozoInWageRange(int minimum, int maximum) {
        if(maximum <= minimum){
            throw new IllegalArgumentException("Maximum is greater, than minimum");
        }
        return getAllDolgozo().stream().filter(d-> d.getFizetes() >= minimum && d.getFizetes()< maximum).collect(Collectors.toList());
    }

    @Override
    public Collection<Dolgozo> getAllDolgozoWithMinimumWage(int minimum) {
        return getAllDolgozo().stream().filter(d-> d.getFizetes() >= minimum).collect(Collectors.toList());
    }

    @Override
    public Map<Reszleg, Double> atlagFizetesReszlegenkent() {
        return null;
    }
}
