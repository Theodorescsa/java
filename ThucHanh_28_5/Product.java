package ThucHanh_28_5;
import java.util.*;
import java.io.*;
import java.net.*;
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


    Product(String productCode, String productName, String description, String productDetail,List<typeProduct> listType) {
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.productDetail = productDetail;
        this.listType = listType;
    }
    public void SetProductInfo() {
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
    

    

