package edu.ucr.rp.algoritmos.proyecto.logic;

import edu.ucr.rp.algoritmos.proyecto.domain.User;

import java.util.List;

public class UserService implements Service<User, String> {

    @Override
    public boolean add(User element) {
        return false;
    }

    @Override
    public boolean edit(User element) {
        return false;
    }

    @Override
    public boolean remove(User element) {
        return false;
    }

    @Override
    public User get(String key) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
