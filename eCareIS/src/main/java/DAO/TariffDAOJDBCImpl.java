package DAO;

import entity.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alexey on 11.06.2015.
 */

public class TariffDAOJDBCImpl implements TariffDAO {
    @Override
    public List<Tariff> findAll(){
        List<Tariff> allTariffs = new ArrayList<Tariff>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecareis", "root", "111111");
                Statement stmt = conn.createStatement();

                if (stmt.execute("SELECT * FROM tariff")) {
                    rs = stmt.getResultSet();
                }
                while (rs.next()) {
                    Tariff currentTariff = new Tariff();
                    currentTariff.setTitle(rs.getString(2));
                    currentTariff.setPrice(rs.getInt(3));
                    allTariffs.add(currentTariff);
                }
            }
            finally {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (allTariffs==null) {
            allTariffs = Collections.EMPTY_LIST;
        }
        return allTariffs;
    }
    @Override
    public void create(Tariff tariff){
        Connection conn = null;
        ResultSet rs = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecareis", "root", "111111");
                Statement stmt = conn.createStatement();

                String stmnt = "INSERT INTO `ecareis`.`tariff` (`title`, `price`) VALUES ('"
                        + tariff.getTitle()+ "', '"+ tariff.getPrice()+"')" ;
                System.out.println(stmnt);
                stmt.execute(stmnt);
            }
            finally {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
