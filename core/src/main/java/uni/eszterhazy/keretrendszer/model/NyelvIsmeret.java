package uni.eszterhazy.keretrendszer.model;

public class NyelvIsmeret {
    private String nyelv;
    private String szint;
    private int id;

    public NyelvIsmeret() {
    }

    public NyelvIsmeret(String nyelv, String szint) {
        this.nyelv = nyelv;
        this.szint = szint;
    }

    public String getNyelv() {
        return nyelv;
    }

    public void setNyelv(String nyelv) {
        this.nyelv = nyelv;
    }

    public String getSzint() {
        return szint;
    }

    public void setSzint(String szint) {
        this.szint = szint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NyelvIsmeret{" +
                "nyelv='" + nyelv + '\'' +
                ", szint='" + szint + '\'' +
                '}';
    }
}
