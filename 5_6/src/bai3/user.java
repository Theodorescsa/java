package bai3;

public class user {
    private String fullName,email,phoneNumber,address,password;
    user(String fullname,String email, String phonenumber, String address, String password) {
        this.fullName = fullname;
        this.email = email;
        this.phoneNumber = phonenumber;
        this.address = address;
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public String getPassword() {
        return password;
    }

}
