package models;

public class Angajati {
    private Integer id_angajat;
    private String nume;
    private String username;
    private String parola;
    private String ocupatie;

    public Angajati( Integer id_angajat,String nume, String username, String parola, String ocupatie) {
        this.id_angajat =id_angajat;
        this.nume = nume;
        this.username = username;
        this.parola = parola;
        this.ocupatie = ocupatie;
    }

    public Integer getId_angajat() {
        return id_angajat;
    }

    public String getNume() {
        return nume;
    }

    public String getUsername() {
        return username;
    }

    public String getParola() {
        return parola;
    }

    public String getOcupatie() {
        return ocupatie;
    }

    public void setId_angajat(Integer id_angajat) {
        this.id_angajat = id_angajat;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setOcupatie(String ocupatie) {
        this.ocupatie = ocupatie;
    }
    @Override
    public String toString() {
        return id_angajat + " " + nume + " " + ocupatie;
    }
}
