package uni.eszterhazy.keretrendszer.dao.relational;

import org.junit.Test;
import uni.eszterhazy.keretrendszer.dao.DolgozoDAO;
import uni.eszterhazy.keretrendszer.exceptions.*;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.NyelvIsmeret;
import uni.eszterhazy.keretrendszer.model.Reszleg;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DolgozoDAORelationalTest {

    @Test
    public void test() throws NevNemLehetUres, FizetesNegativ, RosszSzuletesiDatum, DolgozoAlreadyAdded, DolgozoNotFound {
        DolgozoDAO dao = new DolgozoDAORelational();
        Dolgozo dolgozo = new Dolgozo();
        dolgozo.setNev("Kiss Béla");
        dolgozo.setFizetes(140000);
        dolgozo.setReszleg(Reszleg.HR);
        dolgozo.setSzuletesiDatum(LocalDate.of(1987,12,7));
        NyelvIsmeret ny1 = new NyelvIsmeret("Angol","C1");
        NyelvIsmeret ny2 = new NyelvIsmeret("Német","A1");
        List nyelvek = new ArrayList<>();
        nyelvek.add(ny1);
        nyelvek.add(ny2);
        dolgozo.setNyelvIsmeret(nyelvek);
        dao.createDolgozo(dolgozo);
        System.out.println(dao.readDolgozo(dolgozo.getId()));
        System.out.println(dao.readAllDolgozoOfReszleg(Reszleg.KONYVELES));
    }

}