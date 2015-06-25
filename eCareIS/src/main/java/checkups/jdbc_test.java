package checkups;


import java.sql.*;

/**
 * Created by alexey on 18.06.2015.
 */
public class jdbc_test {
    public static void main(String[] args) {
        Connection conn = null;
        ResultSet rs = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "111111");

                Statement stmt = conn.createStatement();

                if (stmt.execute("SELECT * FROM train")) {
                    rs = stmt.getResultSet();
                }
                while (rs.next()) {
                    System.out.println(rs.getString(3));
                }

//                SELECT * FROM ticket where id = 1


                String prepQuery = "SELECT * FROM ticket, passenger, train where train.id=ticket.train_id and ticket.passenger_id = passenger.id and passenger.name=?";
                PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
                preparedStatement.setString(1, "Иван ");

                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("date") + rs.getString(9) );
                }
            }
            finally {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
