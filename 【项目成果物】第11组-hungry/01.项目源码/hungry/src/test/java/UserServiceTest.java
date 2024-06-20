import com.hust.hungry.HungryApplication;
import com.hust.hungry.entity.User;
import com.hust.hungry.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = HungryApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testUserService() {
        User user = userService.getById("44444444444");
        System.out.println(user.toString());
    }
}
