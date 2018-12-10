package pl.PiotrG25.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {

    private String connectionString = "jdbc:mysql://localhost:3306/db_1?useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
    private String connectionUser = "root";
    private String connectionPassword = "coderslab";


    public void execute(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(
                Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
        ){
            System.out.println("Hellow world!");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
