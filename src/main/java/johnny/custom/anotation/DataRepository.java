/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.custom.anotation;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import johnny.custom.anotation.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Johnny on 23/03/18.
 */

public class DataRepository {
    
    private static List<User> users;
    public static User getUserByToken(String token){
        initData();
        
        for(User user: users){
            if(token.equals(user.getToken()))
                return user;
        }
        
        return null;
    }
    
    
    public static User getAdmin() {
        User admin = new User();
        admin.setId(1);
        admin.setUserName("admin");
        admin.setRole("ADMIN");
        admin.setToken("admin-xxx-xxx");

        return admin;
    }
    
    private static void initData(){
        users=new ArrayList<User>();
        
        User admin=new User();
        admin.setId(1);
        admin.setUserName("admin");
        admin.setRole("ADMIN");
        admin.setToken("admin-xxx-xxx");
        
        User user=new User();
        user.setId(2);
        user.setUserName("user");
        user.setRole("USER");
        user.setToken("user-xxx-xxx");
        
        users.add(user);
        users.add(admin);
            
    }
}
