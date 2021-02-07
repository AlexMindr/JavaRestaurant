package repositories;

import models.Angajati;
import utils.DbCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AngajatiRepository {
    public static void save(Angajati angajat) throws SQLException {
        String sql = "INSERT INTO angajati(nume, username,parola,ocupatie) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = DbCon.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, angajat.getNume());
        preparedStatement.setString(2, angajat.getUsername());
        preparedStatement.setString(3, angajat.getParola());
        preparedStatement.setString(4, angajat.getOcupatie());
        preparedStatement.execute();
    }
    public static Angajati findByNewManager(String nume) throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "SELECT * FROM angajati WHERE ocupatie like 'manager' AND nume like '"+nume+"'";

        ResultSet resultSet = statement.executeQuery(sql);

        Angajati angajat= null;

        if (resultSet.next()) {
             angajat = new Angajati(
                    resultSet.getInt("id_angajat"),
                    resultSet.getString("nume"),
                    resultSet.getString("username"),
                    resultSet.getString("parola"),
                    resultSet.getString("ocupatie")
            );

        }



        return angajat;
    }

    public static Angajati findByUsername(String username) throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "SELECT * FROM angajati WHERE username like '" + username + "'";

        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            Angajati angajat = new Angajati(
                    resultSet.getInt("id_angajat"),
                    resultSet.getString("nume"),
                    resultSet.getString("username"),
                    resultSet.getString("parola"),
                    resultSet.getString("ocupatie")
            );
            return angajat;
        } else
            return null;
    }



    public static void deleteManDummy(Integer id) throws SQLException {
        String sql = "DELETE FROM angajati WHERE id_angajat like '"+id+"'";

        PreparedStatement preparedStatement = DbCon.getConnection().prepareStatement(sql);

        preparedStatement.execute();


    }

}
