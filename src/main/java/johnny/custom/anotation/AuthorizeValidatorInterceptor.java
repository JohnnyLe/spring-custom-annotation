package johnny.custom.anotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;
import johnny.custom.anotation.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by Johnny on 23/03/18.
 */

@Aspect
@Component
public class AuthorizeValidatorInterceptor {


    
    @Before(value = "@annotation(johnny.custom.anotation.AuthorizeValidator)  && @annotation(roles)")
    public void before(JoinPoint caller, AuthorizeValidator roles) {

        System.out.println("Roles: "+Arrays.toString( roles.value())); 
        
        // Capture access token from current request 
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder                
        .getRequestAttributes()).getRequest();     
        String token = request.getHeader("token");
        System.out.println("token= " + token); 
        
        // Check Get current Authen user from access token
        User currentAuthenUser=DataRepository.getUserByToken(token);
        if(currentAuthenUser==null)
             throw new HTTPException(401);
        
        // Validate Role
        boolean isvalid=validateRole(currentAuthenUser.getRole(), roles);
        if(!isvalid)
             throw new HTTPException(401);
        
        //Method method = getCurrentMethod(caller);



    }


    /**
     * 
     * @param currentRole
     * @param roles
     * @return 
     */
    private boolean validateRole(String currentRole, AuthorizeValidator roles){
        for(String role: roles.value()){
            if(currentRole.equals(role))
                return true;
        }
        return false;
    }

    private Method getCurrentMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
