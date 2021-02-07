package repositories;

import models.Angajati;
import models.Comenzi;
import models.Meniu;
import utils.DbCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComenziRepository {
    public static void save(Comenzi comanda) throws SQLException {
        String sql = "INSERT INTO comenzi(id_angajat,id_lista,masa,data_ora) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = DbCon.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, comanda.getId_angajat());
        preparedStatement.setInt(2, comanda.getId_lista());
        preparedStatement.setInt(3, comanda.getMasa());
        preparedStatement.setTimestamp(4, comanda.getData_ora());
        preparedStatement.execute();
    }

    public static void finalizare(Comenzi comanda) throws SQLException {
        String sql = "UPDATE comenzi SET `finalizata` = '1' WHERE `id_comanda` ='" + comanda.getId_comanda() + "'";
        PreparedStatement preparedStatement = DbCon.getConnection().prepareStatement(sql);

        preparedStatement.execute();
    }

    public static void findByPopularitate() throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "SELECT lista.id_felmnc,nume_felmnc,pret,count(lista.id_felmnc) cantitate FROM lista JOIN meniu using (id_felmnc) GROUP BY lista.id_felmnc ORDER BY count(lista.id_felmnc) desc";

        ResultSet resultSet = statement.executeQuery(sql);

        List<Meniu> meniu = new ArrayList<>();
        List<Integer> cantitati= new ArrayList<>();
        while (resultSet.next()) {
            Meniu fel = new Meniu(
                    resultSet.getInt("id_felmnc"),
                    resultSet.getString("nume_felmnc"),
                    resultSet.getDouble("pret")
            );
            meniu.add(fel);
            Integer cantitate= resultSet.getInt("cantitate");
            cantitati.add(cantitate);
            System.out.println(fel+" cantitate vanduta: "+ cantitate);
        }

    }

    public static List<Comenzi> findByDateTimeInterval(String date1, String date2, String time1, String time2) throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "select * from comenzi where date(data_ora) between '" + date1 + "' and '" + date2 + "' AND TIME(data_ora) between '" + time1 + "' and '" + time2 + "' AND `finalizata`=1";

        ResultSet resultSet = statement.executeQuery(sql);

        List<Comenzi> comenzi = new ArrayList<>();

        while (resultSet.next()) {
            Comenzi comanda = new Comenzi(
                    resultSet.getInt("id_comanda"),
                    resultSet.getInt("id_angajat"),
                    resultSet.getInt("id_lista"),
                    resultSet.getInt("masa"),
                    resultSet.getTimestamp("data_ora"),
                    resultSet.getInt("finalizata")
            );
            comenzi.add(comanda);
        }

        return comenzi;
    }

    public static void ComenziInProcesare() throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "select id_comanda,id_angajat,id_lista,masa,data_ora,finalizata,nume from comenzi join angajati using (id_angajat) where finalizata = 0";

        ResultSet resultSet = statement.executeQuery(sql);
        List<Comenzi> comenzi = new ArrayList<>();
        List<String> numes = new ArrayList<>();

            while (resultSet.next()) {
                Comenzi comanda = new Comenzi(
                        resultSet.getInt("id_comanda"),
                        resultSet.getInt("id_angajat"),
                        resultSet.getInt("id_lista"),
                        resultSet.getInt("masa"),
                        resultSet.getTimestamp("data_ora"),
                        resultSet.getInt("finalizata"));
                comenzi.add(comanda);
                String nume = resultSet.getString("nume");
                numes.add(nume);
                System.out.println(comanda + " " + nume);
            }

    }

    public static List<Comenzi> findByAngajat(String username) throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "select id_comanda,id_angajat,id_lista,masa, data_ora,finalizata from comenzi join angajati using (id_angajat) where username ='"+ username +"'";

        ResultSet resultSet = statement.executeQuery(sql);

        List<Comenzi> comenzi = new ArrayList<>();

        while (resultSet.next()) {
            Comenzi comanda = new Comenzi(
                    resultSet.getInt("id_comanda"),
                    resultSet.getInt("id_angajat"),
                    resultSet.getInt("id_lista"),
                    resultSet.getInt("masa"),
                    resultSet.getTimestamp("data_ora"),
                    resultSet.getInt("finalizata")
            );
            comenzi.add(comanda);
        }
        return comenzi;
    }
    public static List<Comenzi> ComenziInLucruFaraNume() throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "select id_comanda,id_angajat,id_lista,masa,data_ora,finalizata from comenzi where finalizata = 0";

        ResultSet resultSet = statement.executeQuery(sql);
        List<Comenzi> comenzi = new ArrayList<>();

        while (resultSet.next()) {
            Comenzi comanda = new Comenzi(
                    resultSet.getInt("id_comanda"),
                    resultSet.getInt("id_angajat"),
                    resultSet.getInt("id_lista"),
                    resultSet.getInt("masa"),
                    resultSet.getTimestamp("data_ora"),
                    resultSet.getInt("finalizata"));
            comenzi.add(comanda);


        }
return comenzi;
    }
}
