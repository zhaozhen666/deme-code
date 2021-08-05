package ad;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.zhao.ad.common.dump.table.*;
import com.zhao.ad.sponsor.SponsorApplication;
import com.zhao.ad.sponsor.constants.CommonStatus;
import com.zhao.ad.sponsor.dao.AdPlanRepository;
import com.zhao.ad.sponsor.dao.AdUnitRepository;
import com.zhao.ad.sponsor.dao.CreativeRepository;
import com.zhao.ad.sponsor.dao.unit_condition.AdUnitDistrictRepository;
import com.zhao.ad.sponsor.dao.unit_condition.AdUnitItRepository;
import com.zhao.ad.sponsor.dao.unit_condition.AdUnitKeywordRepository;
import com.zhao.ad.sponsor.dao.unit_condition.CreativeUnitRepository;
import com.zhao.ad.sponsor.entity.AdPlan;
import com.zhao.ad.sponsor.entity.AdUnit;
import com.zhao.ad.sponsor.entity.Creative;
import com.zhao.ad.sponsor.entity.unit_condition.AdUnitDistrict;
import com.zhao.ad.sponsor.entity.unit_condition.AdUnitIt;
import com.zhao.ad.sponsor.entity.unit_condition.AdUnitKeyword;
import com.zhao.ad.sponsor.entity.unit_condition.CreativeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SponsorApplication.class)
@Slf4j
public class DumpDataService {
    @Autowired
    AdPlanRepository planRepository;
    @Autowired
    AdUnitRepository unitRepository;
    @Autowired
    CreativeRepository creativeRepository;
    @Autowired
    CreativeUnitRepository creativeUnitRepository;
    @Autowired
    AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    AdUnitItRepository adUnitItRepository;
    @Autowired
    AdUnitKeywordRepository keywordRepository;

    private void dumpAdPlanTable(String fileName) {
        List<AdPlan> adPlans = planRepository.findAllByPlanStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(adPlans)) {
            return;
        }
        List<AdPlanTable> planTables = new ArrayList<>();
        adPlans.forEach(p -> {
            planTables.add(new AdPlanTable(p.getId(), p.getUserId(),
                    p.getPlanStatus(), p.getStartDate(), p.getEndDate()));
        });
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdPlanTable planTable : planTables) {
                writer.write(JSON.toJSONString(planTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dump adPlan Table io error");
        }
    }

    private void dumpAdUnitTable(String fileName) {
        List<AdUnit> adUnits = unitRepository.findAllByUnitStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(adUnits)) {
            return;
        }
        List<AdUnitTable> unitTables = new ArrayList<>();
        adUnits.forEach(p -> {
            unitTables.add(new AdUnitTable(p.getId(), p.getUnitStatus(), p.getPositionType(), p.getPlanId()));
        });
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitTable unitTable : unitTables) {
                writer.write(JSON.toJSONString(unitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dump adPlan Table io error");
        }
    }


    private void dumpAdCreativeTable(String fileName) {
        List<Creative> creatives = creativeRepository.findAll();
        if (CollectionUtils.isEmpty(creatives)) {
            return;
        }
        List<AdCreativeTable> creativeTables = new ArrayList<>();
        creatives.forEach(p -> {
            creativeTables.add(new AdCreativeTable(p.getId(),
                    p.getName(),
                    p.getType(),
                    p.getMaterialType(),
                    p.getHeight(),
                    p.getWidth(),
                    p.getAuditStatus(),
                    p.getUrl()));
        });
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdCreativeTable creativeTable : creativeTables) {
                writer.write(JSON.toJSONString(creativeTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dump adPlan Table io error");
        }
    }


    private void dumpAdCreativeUnitTable(String fileName) {
        List<CreativeUnit> creativeUnits = creativeUnitRepository.findAll();
        if (CollectionUtils.isEmpty(creativeUnits)) {
            return;
        }
        List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();
        creativeUnits.forEach(p -> {
            creativeUnitTables.add(new AdCreativeUnitTable(p.getId(), p.getUnitId()));
        });
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdCreativeUnitTable creativeUnitTable : creativeUnitTables) {
                writer.write(JSON.toJSONString(creativeUnitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dump adPlan Table io error");
        }
    }

    private void dumpAdUnitDistrictTable(String fileName) {
        List<AdUnitDistrict> unitDistricts = adUnitDistrictRepository.findAll();
        if (CollectionUtils.isEmpty(unitDistricts)) {
            return;
        }
        List<AdUnitDistrictTable> unitDistrictTables = new ArrayList<>();
        unitDistricts.forEach(p -> {
            unitDistrictTables.add(new AdUnitDistrictTable(p.getUnitId(), p.getProvince(), p.getCity()));
        });
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitDistrictTable unitDistrictTable : unitDistrictTables) {
                writer.write(JSON.toJSONString(unitDistrictTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dump adPlan Table io error");
        }
    }

    private void dumpAdUnitItTable(String fileName) {
        List<AdUnitIt> unitIts = adUnitItRepository.findAll();
        if (CollectionUtils.isEmpty(unitIts)) {
            return;
        }
        List<AdUnitItTable> unitItTables = new ArrayList<>();
        unitIts.forEach(p -> {
            unitItTables.add(new AdUnitItTable(p.getUnitId(), p.getItTag()));
        });
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitItTable unitItTable : unitItTables) {
                writer.write(JSON.toJSONString(unitItTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dump adPlan Table io error");
        }
    }

    private void dumpAdUnitKeywordTable(String fileName) {
        List<AdUnitKeyword> unitKeywords = keywordRepository.findAll();
        if (CollectionUtils.isEmpty(unitKeywords)) {
            return;
        }
        List<AdUnitKeywwordTable> keywwordTables = new ArrayList<>();
        unitKeywords.forEach(p -> {
            keywwordTables.add(new AdUnitKeywwordTable(p.getUnitId(), p.getKeyword()));
        });
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitKeywwordTable unitKeywwordTable : keywwordTables) {
                writer.write(JSON.toJSONString(unitKeywwordTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dump adPlan Table io error");
        }
    }
}
