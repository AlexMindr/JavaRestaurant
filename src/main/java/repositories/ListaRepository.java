package repositories;

import models.Comenzi;
import models.Lista;
import utils.DbCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ListaRepository {
    public static Integer save(List<Integer> feluri) throws SQLException {
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "SELECT id_lista FROM lista ORDER BY id_lista DESC LIMIT 1";
        ResultSet resultSet = statement.executeQuery(sql);
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id_lista") + 1;
        }

        for (int i = 0; i < feluri.size(); i++) {

            sql = "INSERT INTO lista(id_lista,id_felmnc) VALUES(?,?)";
            PreparedStatement preparedStatement = DbCon.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, feluri.get(i));
            preparedStatement.execute();

        }
        return id;
    }
    public static List<Integer> transformFromString(String string){

        String[] strings;
        String delimiter = ",";
        List<Integer> lista= new ArrayList<>();
        strings = string.split(delimiter);
        Integer idd=0;
        for (int i = 0; i < strings.length; i++) {
            idd = parseInt(strings[i]);
            lista.add(idd);

        }
        return lista;

    }
}
