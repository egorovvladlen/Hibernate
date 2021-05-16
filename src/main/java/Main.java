import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "AngelinaJolie1";


        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT name, count(MONTH(subscription_date))/(TIMESTAMPDIFF(MONTH,min(subscription_date),max(subscription_date))) as average\n" +
                    "FROM courses c JOIN subscriptions s ON c.id = s.course_id \n" +
                    "GROUP BY name;");

            while(resultSet.next()) {
                String courseName = resultSet.getString("name");
                String count = resultSet.getString("average");
                System.out.println(courseName + " " + count);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
