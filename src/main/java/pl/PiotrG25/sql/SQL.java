package pl.PiotrG25.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

    /*Reading database structure

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM tab1;");
        ResultSetMetaData rsmd = rs.getMetaData();

        int cols = rsmd.getColumnCount();

        for(int i = 1; i <= cols; i++){
            System.out.println("Class: " + rsmd.getColumnClassName(i));
            System.out.println("Name: " + rsmd.getColumnName(i));
            System.out.println("Type: " + rsmd.getColumnTypeName(i));
            System.out.println("Precision: " + rsmd.getPrecision(i));
            System.out.println("Scale: " + rsmd.getScale(i));
        }
    */

    private String connectionString = "jdbc:mysql://localhost:3306/db_1?useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
    private String connectionUser = "root";
    private String connectionPassword = "coderslab";


    /*Insert part of this project*/
    public Insert insert(String insertStatement){
        return new Insert(insertStatement);
    }

    public class Insert{
        private String insertStatement;

        public Insert(String insertStatement){
            this.insertStatement = insertStatement;
        }

        public void execute(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try(Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);){

                System.out.println("Hellow world!");

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
