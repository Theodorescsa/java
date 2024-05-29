import java.util.*;
class Product {
    String productCode, productName;
    float price;
    int quantity;
    Product(String productCode, String productName, float price, int quantity) {
        this.productCode = productCode;
        this.productName  = productName;
        this.price = price;
        this.quantity = quantity;
    }
    public float TotalPrice() {
        float total = price*quantity;
        return total;
    }
}
class Customer {
    String customerName, phoneNumber, address;
    Customer(String customerName, String phoneNumber, String address) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
class Order {
    Customer customer;
    List<Product> products = new ArrayList<>();
    String orderNumber;
    Order(String orderNumber,Customer customer, List<Product> product) {
        this.customer = customer;
        this.products = product;
        this.orderNumber = orderNumber;
    }
}
public class Orders {
    List<Order> orders = new ArrayList<>();
    Orders(List<Order> orders) {
        this.orders = orders;
    }
    public void AddProduct(String orderNumber,Product product) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).orderNumber.equals(orderNumber)) {
                orders.get(i).products.add(product);
            }
        }
        System.out.println("Da them san pham vao don hang");
    }
    public void EditQuantityNeedToBuy(String madonhang,String productCode,int quantity) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).orderNumber == madonhang) {
                for (int j = 0; j < orders.get(i).products.size(); j++) {
                    if (orders.get(i).products.get(j).productCode == productCode) {
                        orders.get(i).products.get(j).quantity = quantity;
                    }
                }
            }

        }
        System.out.println("Don hang sau khi chinh sua:");
        printOrder();
    }
    public void TotalCheckout(String madonhang) {
        int sum = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).orderNumber == madonhang) {
                for (int j = 0; j < orders.get(i).products.size(); j++) {
                    sum += orders.get(i).products.get(j).TotalPrice();
                }
                break;
            }
         }
         System.out.println("Tong so tien phai thanh toan cho don hang "+madonhang +"la:"+ sum);

    }
    public void printOrder() {
        System.out.println("Don hang");
        System.out.println("----------------------------------------");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("Ma don hang:"+orders.get(i).orderNumber);
            System.out.println("ten khach hang:"+orders.get(i).customer.customerName);
            System.out.println("So dien thoai:"+orders.get(i).customer.phoneNumber);
            System.out.println("Dia chi:"+orders.get(i).customer.address);

            for (int j = 0; j < orders.get(i).products.size(); j++) {
                System.out.println("Ma san pham:"+orders.get(i).products.get(j).productCode);
                System.out.println("Ten san pham:"+orders.get(i).products.get(j).productName);
                System.out.println("Gia:"+orders.get(i).products.get(j).price);
                System.out.println("So luong:"+orders.get(i).products.get(j).quantity);
                TotalCheckout(orders.get(i).orderNumber);
                System.out.println("----------------------------------------");
            }
        }
    }
    public static void main(String[] args) {
        Product p1 = new Product("23sds", "tivi", 1, 12);
        Product p2 = new Product("24sds", "tulanh", 2, 3);
        List<Product> products = new ArrayList<>();
        List<Order> listOrders = new ArrayList<>();
        products.add(p2);
        products.add(p1);
        Customer c1 = new Customer("Ngan Ha My", "102312854323", "Shanghai");
        Customer c2 = new Customer("Nguyen Dinh Thai", "12854323", "Hanoi");
        Order order1 = new Order("1", c1, products);
        Order order2 = new Order("2", c2, products);

        listOrders.add(order1);
        listOrders.add(order2);

        Orders orders = new Orders(listOrders);
        orders.EditQuantityNeedToBuy("1", "24sds", 1);
        orders.printOrder();
        orders.TotalCheckout("1");

    }
}
