/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.custom.anotation;

import johnny.custom.anotation.model.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnny
 */
@Service
public class ApplicationService {
    
    @AuthorizeValidator({"ADMIN"})
    public User getAdmin(){
        
        return DataRepository.getAdmin();
    }
}
