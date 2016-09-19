package entities;

public class User {

    private Integer id;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String country;
    private String city;
    private String address;
    private String afm;
    private Integer role;
    private Integer validated;


    public User (String fullname, String username, String password, String email, String phone,
                 String country, String city, String address, String afm, Integer role) {

        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.address = address;
        this.afm = afm;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getValidated() {
        return validated;
    }

    public void setValidated(Integer validated) {
        this.validated = validated;
    }
}
