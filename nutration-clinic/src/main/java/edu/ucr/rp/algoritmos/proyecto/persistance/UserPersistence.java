package edu.ucr.rp.algoritmos.proyecto.persistance;

import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.persistance.util.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Maneja una lista completa de usuarios
 */
public class UserPersistence implements Persistence<User, UserLinkedList> {
    private final String path = "files/users.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    @Override
    public boolean write(User user) {
        if (user == null) return false;
        return saveUser(user);
    }

    @Override
    public UserLinkedList read() {
        return readUsers();
    }

    @Override
    public boolean deleteAll() {
        try {
            FileUtils.forceDelete(new File(path));
            return true;
        }catch (IOException e){
            return false;
        }
    }

    private boolean saveUser(User user) {
        jsonUtil.toFile(new File(path), user);
        return true;
    }

    private UserLinkedList readUsers(){
        File file = new File(path);
        if(file.exists()){
            try {
                return jsonUtil.asObject(file.toURI().toURL(), UserLinkedList.class);
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
