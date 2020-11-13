package uni.eszterhazy.keretrendszer.model;

import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import uni.eszterhazy.keretrendszer.exceptions.FizetesNegativ;
import uni.eszterhazy.keretrendszer.exceptions.NevNemLehetUres;
import uni.eszterhazy.keretrendszer.exceptions.RosszSzuletesiDatum;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



public class Dolgozo {
    private String nev;
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate szuletesiDatum;
    private Reszleg reszleg;
    private double fizetes;
    private List<NyelvIsmeret> nyelvIsmeret;

    Logger logger = Logger.getLogger(this.getClass());

    public Dolgozo() {
        this.id= UUID.randomUUID().toString();
        logger.warn("Uj dolgozo lett letrehozva a "+this.id+" azonositoval");
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) throws NevNemLehetUres {
        if (nev.trim().length()==0){
            throw new NevNemLehetUres();
        }
        this.nev = nev;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getSzuletesiDatum() {
        return szuletesiDatum;
    }

    public void setSzuletesiDatum(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate szuletesiDatum) throws RosszSzuletesiDatum {
        if (szuletesiDatum.isAfter(LocalDate.now().minusYears(16))){
            throw new RosszSzuletesiDatum("16 evnel fiatalabb: "+szuletesiDatum);
        }
        if (szuletesiDatum.isBefore(LocalDate.now().minusYears(100))){
            throw new RosszSzuletesiDatum("100 evnel idosebb: "+szuletesiDatum);
        }
        this.szuletesiDatum = szuletesiDatum;
    }

    public Reszleg getReszleg() {
        return reszleg;
    }

    public void setReszleg(Reszleg reszleg) {
        this.reszleg = reszleg;
    }

    public double getFizetes() {
        return fizetes;
    }

    public void setFizetes(double fizetes) throws FizetesNegativ {
        if (fizetes < 0){
            throw new FizetesNegativ(fizetes);
        }
        this.fizetes = fizetes;
    }

    public List<NyelvIsmeret> getNyelvIsmeret() {
        return nyelvIsmeret;
    }

    public void setNyelvIsmeret(List<NyelvIsmeret> nyelvIsmeret) {
        this.nyelvIsmeret = nyelvIsmeret;
    }

    @Override
    public String toString() {
        return "Dolgozo{" +
                "nev='" + nev + '\'' +
                ", id='" + id + '\'' +
                ", szuletesiDatum=" + szuletesiDatum +
                ", reszleg=" + reszleg +
                ", fizetes=" + fizetes +
                ", nyelvIsmeret=" + nyelvIsmeret +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dolgozo dolgozo = (Dolgozo) o;
        return Double.compare(dolgozo.fizetes, fizetes) == 0 &&
                Objects.equals(nev, dolgozo.nev) &&
                Objects.equals(id, dolgozo.id) &&
                Objects.equals(szuletesiDatum, dolgozo.szuletesiDatum) &&
                reszleg == dolgozo.reszleg &&
                nyelvIsmeret.equals(dolgozo.nyelvIsmeret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nev, id, szuletesiDatum, reszleg, fizetes, nyelvIsmeret, logger);
    }
}
