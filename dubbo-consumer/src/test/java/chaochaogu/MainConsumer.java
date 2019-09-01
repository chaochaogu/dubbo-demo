package chaochaogu;

import com.chaochaogu.model.User;
import com.chaochaogu.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chaochao gu
 * @date 2019/9/1
 */
public class MainConsumer {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService =(UserService) context.getBean("userService");
        User user = userService.getUserById(1);
        System.out.println(user);
    }
}
