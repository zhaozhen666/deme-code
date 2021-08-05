package dao;

import com.zhao.RunBoot;
import com.zhao.entity.City;
import com.zhao.repository.CityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestMasterSlave {
    @Resource
    CityRepository cityRepository;

    @Test
    public void testMasterSlave() {
        City city = new City();
        city.setName("shanghai");
        city.setProvince("shanghai");
        cityRepository.save(city);
    }

    @Test
    public void findAll() {
        List<City> list = cityRepository.findAll();
        list.forEach(c -> {
            System.out.println(c.getId() + " " + c.getName() + " " + c.getProvince());
        });
    }
}
