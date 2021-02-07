package repositories;

import models.Angajati;
import models.Comenzi;
import models.Meniu;
import utils.DbCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MeniuRepository {
    public static void save(Meniu fel) throws SQLException {
        String sql = "INSERT INTO meniu(nume_felmnc, pret) VALUES(?, ?)";
        PreparedStatement preparedStatement = DbCon.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, fel.getNume_felmnc());
        preparedStatement.setDouble(2, fel.getPret());
        preparedStatement.execute();
    }

    public static void edit(Integer id,String numenou,Double pretnou) throws SQLException {
        String sql = "UPDATE meniu SET `nume_felmnc` = '"+ numenou +"', `pret` = '"+pretnou+"' WHERE (`id_felmnc` ='"+id+"')";
        PreparedStatement preparedStatement = DbCon.getConnection().prepareStatement(sql);

        preparedStatement.execute();
    }

    public static List<Meniu> ListaProduse() throws SQLException{
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "SELECT * FROM meniu ";

        ResultSet resultSet = statement.executeQuery(sql);
        List<Meniu> meniu=new ArrayList<>();
        while (resultSet.next()) {
            Meniu fel = new Meniu(
                    resultSet.getInt("id_felmnc"),
                    resultSet.getString("nume_felmnc"),
                    resultSet.getDouble("pret")
            );
        meniu.add(fel);
        }
            return meniu;

    }

    public static Meniu findByNume(String nume) throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "SELECT * FROM meniu WHERE nume_felmnc like '"+nume+"'";
        ResultSet resultSet = statement.executeQuery(sql);



        if (resultSet.next()) {
            Meniu fel = new Meniu(
                    resultSet.getInt("id_comanda"),
                    resultSet.getString("id_angajat"),
                    resultSet.getDouble("id_lista")
            );
            return fel;
        }
        else return null;

    }
}


