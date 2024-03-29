package edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.interfaces.Persistence;
import edu.ucr.rp.algoritmos.proyecto.util.files.JsonUtil;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class UserPersistence implements Persistence<User, UserLinkedList> {
    private final String path = "files/user.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    /**
     * Para guardar un usuario.
     * @param userLinkedList que se quiere guardar
     * @return true si se guardó, si no, false
     */
    @Override
    public boolean write(UserLinkedList userLinkedList) {
        if (userLinkedList == null) return false;
        return saveUsers(userLinkedList);
    }

    private boolean saveUsers(UserLinkedList userLinkedList) {
        jsonUtil.toFile(new File(path), userLinkedList);
        return true;
    }

    /**
     * Para leer una lista de usuarios.
     * @return lista de usuarios
     */
    @Override
    public UserLinkedList read() {
        return readUsers();
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

    /**
     * Elimina todos los usuarios del sistema.
     * @return true si se eliminaron todos los usuarios, si no, false
     */
    @Override
    public boolean deleteAll() {
        try {
            FileUtils.forceDelete(new File(path));
            return true;
        }catch (IOException e){
            return false;
        }
    }

}
