import com.hust.hungry.HungryApplication;
import com.hust.hungry.entity.Business;
import com.hust.hungry.entity.JsonResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.hust.hungry.service.BusinessService;
import java.util.List;

@SpringBootTest(classes = HungryApplication.class)
public class BusinessServiceTest {
    @Autowired
    private BusinessService businessService;
    @Test
    public void getbusinesslist() {
        List<Business> businessList = businessService.list();
        System.out.println(businessList.size());
    }
}

