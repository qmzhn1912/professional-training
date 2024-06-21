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
    @Test
    public void testRegisterSuccess() {
        // Arrange
        User user = new User();
        user.setUserId("12345672221");
        user.setUserName("jack");
        user.setUserSex(0);
        user.setUserImg("+0zWPUl8tYMA1qOwi82bxzAAvybikSfZhme0srdHm89Zq1XLF2lxd5uTMOzsZ2eQyrbvWfWKM0vl/N/2ETSZqf1oa9pWPXCsT/sPn9AOeIade/c5vZ0VizrU09GiKO/Zu/8oKCbNrqGa4XrVrD/LOmN4fISuaD1Ei4l47UCtCL9Qy9pA1sJx4wQAIIiijBZ1VtJ5gOShl20c7Six1gZrXTiAlzaQz5yQB/z/ASYreCvt25D1AAAAAElFTkSuQmCC");
        user.setPassword("123");
        user.setDelTag(1);
        userService.register(user);
    }
}
