import uni.eszterhazy.keretrendszer.dao.DolgozoDAO;
import uni.eszterhazy.keretrendszer.dao.json.DolgozoDAOJSON;
import uni.eszterhazy.keretrendszer.dao.mongo.DolgozoDAOMongo;
import uni.eszterhazy.keretrendszer.dao.relational.DolgozoDAORelational;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.FizetesNegativ;
import uni.eszterhazy.keretrendszer.exceptions.NevNemLehetUres;
import uni.eszterhazy.keretrendszer.exceptions.RosszSzuletesiDatum;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.NyelvIsmeret;
import uni.eszterhazy.keretrendszer.service.DolgozoService;
import uni.eszterhazy.keretrendszer.service.impl.DolgozoServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Proba {
    public static void main(String[] args) throws FizetesNegativ, NevNemLehetUres, RosszSzuletesiDatum, IOException {
        //DolgozoDAO dao = new DolgozoDAOJSON("adatok.json");
        DolgozoDAO dao = new DolgozoDAOMongo("mongodb://localhost:27018", "dolgozok","dolgozo");
        DolgozoService service = new DolgozoServiceImpl(dao);

        Dolgozo dolgozo = new Dolgozo();
        dolgozo.setFizetes(180000);
        dolgozo.setNev("Nagy János");
        dolgozo.setSzuletesiDatum(LocalDate.of(1990,5,1));
        List<NyelvIsmeret> nyelvek = new ArrayList<>();
        nyelvek.add(new NyelvIsmeret("Angol","B2"));
        dolgozo.setNyelvIsmeret(nyelvek);
        try {
            service.addDolgozo(dolgozo);
        } catch (DolgozoAlreadyAdded dolgozoAlreadyAdded) {
            dolgozoAlreadyAdded.printStackTrace();
        }
        System.out.println(service.getDolgozoById(dolgozo.getId()));
        System.out.println(service.getAllDolgozo());
        System.out.println(service.atlagFizetes());
    }
}
