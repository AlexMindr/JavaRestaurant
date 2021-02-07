package models;

public class Meniu {
    private Integer id_felmnc;
    private String nume_felmnc;
    private Double pret;

    public Meniu(Integer id_felmnc, String nume_felmnc, Double pret) {
        this.id_felmnc = id_felmnc;
        this.nume_felmnc = nume_felmnc;
        this.pret = pret;

    }

    public Integer getId_felmnc() {
        return id_felmnc;
    }

    public String getNume_felmnc() {
        return nume_felmnc;
    }

    public Double getPret() {
        return pret;
    }

    public void setId_felmnc(Integer id_felmnc) {
        this.id_felmnc = id_felmnc;
    }

    public void setNume_felmnc(String nume_felmnc) {
        this.nume_felmnc = nume_felmnc;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }
    @Override
    public String toString() {
        return id_felmnc + ". " + nume_felmnc + " cu pretul de: " + pret;
    }
}
