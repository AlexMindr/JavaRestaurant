package models;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

public class Comenzi {
    private Integer id_comanda;
    private Integer id_lista;
    private Integer id_angajat;
    private Integer masa;
    private Timestamp data_ora;
    private Integer finalizata;

    public Comenzi(Integer id_comanda, Integer id_angajat, Integer id_lista,Integer masa, Timestamp data_ora,Integer finalizata) {
        this.id_comanda = id_comanda;
        this.id_lista = id_lista;
        this.id_angajat = id_angajat;
        this.masa= masa;
        this.data_ora = data_ora;
        this.finalizata = finalizata;
    }

    public Integer getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(Integer id_comanda) {
        this.id_comanda = id_comanda;
    }

    public Integer getMasa() {
        return masa;
    }

    public void setMasa(Integer masa) {
        this.masa = masa;
    }

    public Integer getId_lista() {
        return id_lista;
    }

    public void setId_lista(Integer id_lista) {
        this.id_lista = id_lista;
    }

    public Integer getId_angajat() {
        return id_angajat;
    }

    public void setId_angajat(Integer id_angajat) {
        this.id_angajat = id_angajat;
    }

    public Timestamp getData_ora() {
        return data_ora;
    }

    public void setData_ora(Timestamp data_ora) {
        this.data_ora = data_ora;
    }

    public Integer getFinalizata() {
        return finalizata;
    }

    public void setFinalizata(Integer finalizata) {
        this.finalizata = finalizata;
    }
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return "IDCom: "+id_comanda + " IdAng: " + id_angajat + " IdList: " + id_lista+" Masa: "+masa+ " DataOra: "+ formatter.format(data_ora)+" Finalizata:" +finalizata;
    }
}
