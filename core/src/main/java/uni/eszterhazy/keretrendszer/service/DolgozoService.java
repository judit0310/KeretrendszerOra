package uni.eszterhazy.keretrendszer.service;

import uni.eszterhazy.keretrendszer.exceptions.DolgozoAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoNotFound;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.Reszleg;

import java.util.Collection;
import java.util.Map;

public interface DolgozoService {
    void addDolgozo(Dolgozo dolgozo) throws DolgozoAlreadyAdded;
    Collection<Dolgozo> getAllDolgozo();
    Dolgozo getDolgozoById(String id) throws DolgozoNotFound;
    void updateDolgozo(Dolgozo dolgozo);
    void removeDolgozo(Dolgozo dolgozo);
    Collection<Dolgozo> readAllDolgozoOfReszleg(Reszleg reszleg);
    double atlagFizetes();

    Collection<Dolgozo> getAllDolgozoInWageRange(int minimum, int maximum);

    Collection<Dolgozo> getAllDolgozoWithMinimumWage(int minimum);

    Map<Reszleg,Double> atlagFizetesReszlegenkent();
}
