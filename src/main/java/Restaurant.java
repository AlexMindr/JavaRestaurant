import models.Angajati;
import models.Comenzi;
import models.Meniu;
import org.w3c.dom.ls.LSOutput;
import repositories.AngajatiRepository;
import repositories.ComenziRepository;
import repositories.ListaRepository;
import repositories.MeniuRepository;
import utils.DbCon;

import javax.security.auth.login.AccountLockedException;
import java.sql.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.time.ZonedDateTime.now;

public class Restaurant {
    public static void main(String[] args) throws SQLException {
///Tester code
       // Comenzi comanda= new Comenzi(0,2,4,Timestamp.valueOf("2020-05-16 15:50:00"),0);
        //ComenziRepository.save(comanda);

        //System.out.println(AngajatiRepository.findByUsername("alexmdr"));

        /*String string =


        String[] tempArray;
        String delimiter = ",";

        tempArray = string.split(delimiter);

        for (int i = 0; i < tempArray.length; i++)
            System.out.println(tempArray[i]);
        */



        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
*/

//        System.out.println(Timestamp.valueOf(string));
       // System.out.println(ComenziRepository.findByDateTimeInterval("2020-05-05","2020-05-20","00:00","24:00")
        //);
    }


}
