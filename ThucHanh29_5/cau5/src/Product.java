import java.sql.*;
public class Product {
    String productCode, productName;
    int price, quantity;
    final static String jdbcURL = "jdbc:mysql://localhost:3306/Product29_5";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    final static String table = "product"; 

    Product(String productCode, String productName, int price, int quantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    public void SetProductInfo() {
        String user = "root"; 
        String password = "dinhthai2004"; 

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, user, password);
            System.out.println("Ket noi thanh cong!");

            statement = connection.createStatement();
  



            String createProductTableQuery = "create table if not exists product ("
                    + "id int primary key auto_increment, "
                    + "productcode varchar(50), "
                    + "productname varchar(100), "
                    + "quantity int, "
                    + "price int)";
                  
            statement.executeUpdate(createProductTableQuery);
            System.out.println("table product da duoc tao thanh cong.");
            String query = "SELECT * FROM product ORDER BY price DESC";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsMetaData.getColumnName(i) + "\t");
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

            resultSet.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Khong tim thay driver jdbc.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
    }
    public static void main(String[] args) {
        Product product = new Product("", "", 0,0);
        
        product.SetProductInfo();
    }
}
    

    

