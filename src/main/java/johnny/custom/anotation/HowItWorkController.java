package johnny.custom.anotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Johnny on 23/03/18.
 */

@RestController
public class HowItWorkController {


    @AuthorizeValidator({"ADMIN","MANAGER"})
    @RequestMapping("/admin/test")
    public String admin(){
        String comment = "hello, only can access by ADMIN or MANAGER";
        return  comment;
    }



    @AuthorizeValidator({"USER"})
    @RequestMapping("/user/test")
    public String user(){

        return  "Hellow, only can access by USER";
    }
}
