package pl.PiotrG25.sql;


import java.sql.*;
import java.util.*;

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
    public Insert insert(){
        return new Insert();
    }

    public class Insert{
        private String insertStatement = "INSERT INTO tab1 (?) VALUES (?);";
        private Map<String, String> columnValue = new HashMap<>();

        public Insert(){}

        public Insert into(String column, String value){
            columnValue.put(column, value);
            return this;
        }

        public void execute(){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try(Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
                PreparedStatement pstm = conn.prepareStatement(insertStatement);){

                String columns = "";
                String values = "";
                Iterator iterator = columnValue.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, String> mentry = (Map.Entry<String, String>)iterator.next();
                    columns += mentry.getKey() + ", ";
                    values += mentry.getValue() + ", ";
                }

                columns = columns.substring(0, columns.length() - 2);
                values = values.substring(0, values.length() - 2);

                System.out.println(columns);
                System.out.println(values);

                pstm.setString(1, columns);//wrong
                pstm.setString(2, values);//wrong

                pstm.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
