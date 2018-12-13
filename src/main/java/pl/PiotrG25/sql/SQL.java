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

    private String connectionString = "jdbc:mysql://localhost:3306/db_1?useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
    private String connectionUser = "root";
    private String connectionPassword = "coderslab";


    /*Insert part of this project*/
    public Insert insert(){
        return new Insert();
    }

    public class Insert{
        private String insertStatement1 = "INSERT INTO tab1 (";
        private String insertStatement2 = "VALUES (";

        private Map<String, String> columnValue = new HashMap<>();

        public Insert(){}

        public Insert into(String column, Integer value){
            insertStatement1 += column + ",";
            insertStatement2 += "?,";
            columnValue.put("Integer", ""+value);
            return this;
        }
        public Insert into(String column, Long value){
            insertStatement1 += column + ",";
            insertStatement2 += "?,";
            columnValue.put("Long", ""+value);
            return this;
        }
        public Insert into(String column, Double value){
            insertStatement1 += column + ",";
            insertStatement2 += "?,";
            columnValue.put("Double", ""+value);
            return this;
        }
        public Insert into(String column, String value){
            insertStatement1 += column + ",";
            insertStatement2 += "?,";
            columnValue.put("String", ""+value);
            return this;
        }

        public void execute(){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try(Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);){

                insertStatement1 = insertStatement1.substring(0, insertStatement1.length() - 1) + ") ";
                insertStatement2 = insertStatement2.substring(0, insertStatement2.length() - 1) + ");";
                String insertStatement = insertStatement1 + insertStatement2;


                System.out.println(insertStatement);


                PreparedStatement pstm = conn.prepareStatement(insertStatement);
                Iterator iterator = columnValue.entrySet().iterator();

                for(int i = 1; iterator.hasNext(); i++){
                    Map.Entry<String, String> mapEntry = (Map.Entry<String, String>)iterator.next();
                    switch (mapEntry.getKey()){
                        case "Integer":
                            pstm.setInt(i, Integer.valueOf(mapEntry.getValue()));
                            break;
                        case "Long":
                            pstm.setLong(i, Long.valueOf(mapEntry.getValue()));
                            break;
                        case "Double":
                            pstm.setDouble(i, Double.valueOf(mapEntry.getValue()));
                            break;
                        case "String":
                            pstm.setString(i, mapEntry.getValue());
                            break;
                    }
                }

                pstm.executeUpdate();
                pstm.close();

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
