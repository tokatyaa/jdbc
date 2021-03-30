package core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private final String URL = "jdbc:mysql://localhost:3306/new_schema1?serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "Tokovaya200325";

    private final String INSERT_NEW = "INSERT INTO Users VALUES (?,?,?,?)";
    private final String GET_ALL = "SELECT * FROM Users";
    private final String CLEAR = "TRUNCATE TABLE Users";
    private final String DROP = "DROP TABLE Users";


    Connection connection;
    PreparedStatement preparedStatement;


    public Util() {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Драйвер загужен, соединение установлено");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Не удалось загрузить драйвер, соединение не установлено");
            }

            try{
                preparedStatement = connection.prepareStatement(INSERT_NEW);
                preparedStatement.setLong(1, 1);
                preparedStatement.setString(2, "Sherlock");
                preparedStatement.setString(3, "Holmes");
                preparedStatement.setByte(4, (byte) 32);
                preparedStatement.execute();

                preparedStatement.setLong(1, 2);
                preparedStatement.setString(2, "Mycroft");
                preparedStatement.setString(3, "Holmes");
                preparedStatement.setByte(4, (byte) 36);
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement(INSERT_NEW);
                preparedStatement.setLong(1, 3);
                preparedStatement.setString(2, "John");
                preparedStatement.setString(3, "Watson");
                preparedStatement.setByte(4, (byte) 33);
                preparedStatement.execute();

                preparedStatement.setLong(1, 4);
                preparedStatement.setString(2, "Mrs");
                preparedStatement.setString(3, "Hudson");
                preparedStatement.setByte(4, (byte) 66);
                preparedStatement.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        try{
            preparedStatement = connection.prepareStatement(GET_ALL);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                long id = result.getLong("id");
                String name = result.getString("firstName");
                String lastName = result.getString("lastName");
                byte age = result.getByte("age");

                System.out.println("Пользователь с именем " + name + " добавлен в таблицу.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            preparedStatement = connection.prepareStatement(CLEAR);
            System.out.println("Таблица очищена");

        } catch (SQLException exc) {
            exc.printStackTrace();
        }

        try {
            preparedStatement = connection.prepareStatement(DROP);
            System.out.println("Таблица удалена");
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }

        public Connection getConnection () {
            return connection;
        }

        public void setConnection (Connection connection){
            this.connection = connection;
        }
}
