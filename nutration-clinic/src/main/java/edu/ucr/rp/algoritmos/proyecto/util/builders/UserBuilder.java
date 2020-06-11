package edu.ucr.rp.algoritmos.proyecto.util.builders;

import edu.ucr.rp.algoritmos.proyecto.domain.User;

public class UserBuilder {
    private String name, email, password;
    private Integer phoneNumber, iD, rol;

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder withID(Integer iD) {
        this.iD = iD;
        return this;
    }

    public UserBuilder withRol(Integer rol) {
        this.rol = rol;
        return this;
    }

    public User build(){
        return new User(name,
                email,
                password,
                phoneNumber,
                iD,
                rol
                );
    }

}
