package cn.ist.lowcoding.streamservice.service;

import cn.ist.lowcoding.streamservice.model.combination.Combination;
import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.pojo.dto.request.CreateDataRequest;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    DataRepo dataRepo;

    @Autowired
    CombinationService combinationService;

    public String registerData(CreateDataRequest request) {
        //得到DataDO
        Data data = new Data();
        BeanUtils.copyProperties(request, data);

        //存入数据库
        dataRepo.save(data);
        return data.getId();
    }

    private void fillCombinationsInData(Data data) {
        List<Combination> combinations = data.getCombinations();
        List<String> combinationIds = data.getCombinationIds();
        for (String combinationId : combinationIds) {
            Combination combination = combinationService.getCombinationById(combinationId);
            combinations.add(combination);
        }
    }

    public List<Data> getAllData() {
        List<Data> allData = dataRepo.findAll();
        for (Data data : allData) {
            fillCombinationsInData(data);
        }
        return allData;
    }

    public Data getDataById(String dataId) {
        Data data = dataRepo.findById(dataId).orElseThrow(() -> new RuntimeException("找不到对应的数据源"));
        fillCombinationsInData(data);
        return data;
    }

    public void deleteData(String dataId) {
        Data data = getDataById(dataId);
        List<String> combinationIds = data.getCombinationIds();
        for (String combinationId : combinationIds) {
            combinationService.deleteCombinationById(combinationId);
        }

        dataRepo.deleteById(dataId);
    }
}
