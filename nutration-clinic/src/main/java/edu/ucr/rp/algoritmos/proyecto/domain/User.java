package edu.ucr.rp.algoritmos.proyecto.domain;

public class User {
    private String name, email, password, address;
    private int phoneNumber, iD, rol;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = encrypt(password);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public int getiD() {
        return iD;
    }

    public User setiD(int iD) {
        this.iD = iD;
        return this;
    }

    public int getRol() {
        return rol;
    }

    public User setRol(int rol) {
        this.rol = rol;
        return this;
    }

    private String encrypt(String password) {
        this.password = password;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");

            byte[] array = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();

        } catch (java.security.NoSuchAlgorithmException me) {
            return null;
        }

    }
}
