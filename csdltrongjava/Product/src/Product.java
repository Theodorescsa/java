import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;
class typeProduct {
    String typeName;
    int price;

    typeProduct() {};
}
public class Product {
    String productCode, productName, description, productDetail;
    typeProduct type;
    List<typeProduct> listType;
    public final static int DEFAULT_PORT = 2007;
    final static String jdbcURL = "jdbc:mysql://localhost:3306/MyProduct";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    final static String table = "product"; 

    Product(String productCode, String productName, String description, String productDetail,List<typeProduct> listType) {
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.productDetail = productDetail;
        this.listType = listType;
    }
    public void SetProductInfo() {
        String user = "root"; 
        String password = "dinhthai2004"; 

        Connection connection = null;
        Statement statement = null;

        List<typeProduct> newListType = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Moi nhap ma san pham cua san pham:");
        String newProducttCode = scanner.nextLine();
        System.out.println("Moi nhap ten san pham cua san pham:");
        String newProductName = scanner.nextLine();
        System.out.println("Moi nhap mo ta cua san pham:");
        String newDescription = scanner.nextLine();
        System.out.println("Moi nhap chi tiet cua san pham:");
        String newProductDetail = scanner.nextLine();
        while (true) {
            System.out.println("Moi nhap ten bien the cua san pham (nhap 'xong' de ket thuc):");
            String typeName = scanner.nextLine();
            if (typeName.equalsIgnoreCase("xong")) {
                break;
            }
            System.out.println("Moi nhap gia cua bien the cua san pham:");
            String price = scanner.nextLine();

            typeProduct type = new typeProduct();
            type.typeName = typeName;
            try {
                type.price = Integer.parseInt(price);
            } catch (NumberFormatException e) {
                System.out.println("Gia khong hop le, vui long nhap lai.");
                continue;
            }

            newListType.add(type);
        }
        // System.out.println("Moi nhap bien the  cua san pham:");
        // String newTypeProduct = scanner.nextLine();  
        // System.out.println("Moi nhap gia cua bien the cua san pham:");
        // String newPriceProduct = scanner.nextLine();  
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, user, password);
            System.out.println("Ket noi thanh cong!");

            statement = connection.createStatement();
            String createTypeProductTableQuery = "create table if not exists typeproduct ("
            +"typename varchar(50),"
            +"price int)";
            statement.executeUpdate(createTypeProductTableQuery);
            System.out.println("table type product da duoc tao thanh cong.");
            // String createIndexQuery = "create index typename_index ON typeproduct (typename)";
            // statement.executeUpdate(createIndexQuery);
            for (int i = 0; i < newListType.size(); i++) {
                String typeName = newListType.get(i).typeName;
                int price = newListType.get(i).price;
                
                String checkTypeNameQuery = "select typename from typeproduct where typename = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(checkTypeNameQuery);
                preparedStatement.setString(1, typeName);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    System.out.println("Type name '" + typeName + "' da ton tai");
                } else {
                    String insertTypeProductQuery = "insert into typeproduct (typename, price) values (?, ?)";
                    preparedStatement = connection.prepareStatement(insertTypeProductQuery);
                    preparedStatement.setString(1, typeName);
                    preparedStatement.setInt(2, price);
                    preparedStatement.executeUpdate();
                }
            }
            

            String createProductTableQuery = "create table if not exists product ("
                    + "id int primary key auto_increment, "
                    + "productcode varchar(50), "
                    + "productname varchar(100), "
                    + "description text, "
                    + "productdetail text, "
                    + "typename varchar(50), "
                    + "foreign key (typename) references typeproduct(typename) "
                    + "on delete cascade)";
            statement.executeUpdate(createProductTableQuery);
            System.out.println("table product da duoc tao thanh cong.");
            for (int i = 0; i < newListType.size(); i++) {
                String insertProductQuery = "insert into product (productcode, productname, description, productdetail, typename) values "
                + "('"+newProducttCode+"','"+newProductName+"','"+newDescription+"','"+newProductDetail+"','"+newListType.get(i).typeName+"')";
                statement.executeUpdate(insertProductQuery);
            }
           

            String query = "select * from " + table;
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
        Product newProduct = new Product(newProducttCode, newProductName, newDescription, newProductDetail, newListType);
        System.out.println("Thong tin san pham moi:");
        System.out.println("Ma san pham: " + newProduct.productCode);
        System.out.println("Ten san pham: " + newProduct.productName);
        System.out.println("Mo ta: " + newProduct.description);
        System.out.println("Chi tiet: " + newProduct.productDetail);
        for (typeProduct tp : newProduct.listType) {
            System.out.println("Bien the: " + tp.typeName + ", Gia: " + tp.price);
        }
        scanner.close();
    }
    public static void main(String[] args) {
        Product product = new Product("", "", "", "", new ArrayList<>());
        product.SetProductInfo();
    }
}
    

    

