package starter;

import com.zhao.StarterUseApplication;
import com.zhao.starter.StarterBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarterUseApplication.class)
public class StarterUseTest {
    @Autowired
    StarterBean starterBean;
    @Test
    public void starUse(){
        System.out.println(starterBean);
    }
}
