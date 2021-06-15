package dao;

import com.zhao.RunBoot;
import com.zhao.entity.City;
import com.zhao.repository.CityRepository;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestHint {
    @Resource
    CityRepository cityRepository;
    @Test
    public void testHint(){
        HintManager hintManager =HintManager.getInstance();
        hintManager.setDatabaseShardingValue(0L);
        List<City> list = cityRepository.findAll();
        System.out.println("主库表大小"+list.size());
        hintManager.setDatabaseShardingValue(1L);
        List<City> list1 = cityRepository.findAll();
        System.out.println("从库表大小"+list1.size());
    }
}
