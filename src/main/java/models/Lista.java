package models;

public class Lista {
    private Integer id_lista;
    private Integer id_felmnc;

    public Lista(Integer id_lista, Integer id_felmnc) {
        this.id_lista = id_lista;
        this.id_felmnc = id_felmnc;
    }

    public Integer getId_lista() {
        return id_lista;
    }

    public void setId_lista(Integer id_lista) {
        this.id_lista = id_lista;
    }

    public Integer getId_felmnc() {
        return id_felmnc;
    }

    public void setId_felmnc(Integer id_felmnc) {
        this.id_felmnc = id_felmnc;
    }
}
