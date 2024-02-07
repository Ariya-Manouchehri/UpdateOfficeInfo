package com.gamelectronics.updateofficeinfo.serviceImpl;

import com.gam.phoenix.samanapi.resource.model.request.CreateNodeRequestModel;
import com.gamelectronics.updateofficeinfo.exception.NotFoundOfficeException;
import com.gamelectronics.updateofficeinfo.exception.SamanExceptionException;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.repository.OfficeRepository;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import com.gamelectronics.updateofficeinfo.utils.OfficeBeanCopy;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    @Transactional(rollbackOn = SamanExceptionException.class)
    public List<Office> registerOffice(List<Office> offices) {
        for (Office item : offices) {
            setOfficeDataForSamanSystem(item);
        }
        return officeRepository.saveAll(offices);
    }

    @Override
    @Transactional(rollbackOn = SamanExceptionException.class)
    public void updateAllOfficeFiled(Office office) throws NotFoundOfficeException {
        Office officeFinder = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (officeFinder != null) {
            officeFinder = office;
            officeRepository.save(officeFinder);
            setOfficeDataForSamanSystem(officeFinder);
        } else {
            throw new NotFoundOfficeException("office " + office.getOfficeCode() + " notFound.");
        }
    }

    @Override
    @Transactional(rollbackOn = SamanExceptionException.class)
    public void updateNotNullOfficeFiled(Office office) throws InvocationTargetException, IllegalAccessException {
        Office officeFinder = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (officeFinder != null) {
            OfficeBeanCopy officeBeanCopy = new OfficeBeanCopy();
            officeBeanCopy.copyProperties(officeFinder, office);
            officeRepository.save(officeFinder);
            setOfficeDataForSamanSystem(officeFinder);
        } else {
            throw new NotFoundOfficeException("office " + office.getOfficeCode() + " notFound.");
        }
    }

    @Override
    public Office getOffice(String officeCode) {
        Office officeFinder = officeRepository.findByOfficeCode(officeCode);
        if (officeFinder != null) {
            return officeFinder;
        } else {
            throw new NotFoundOfficeException("office " + officeCode + " notFound.");
        }
    }

    private void setOfficeDataForSamanSystem(Office office) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer your_access_token");

        CreateNodeRequestModel createNodeRequestModel = new CreateNodeRequestModel();
        createNodeRequestModel.setNodeType("mci:office");
        createNodeRequestModel.setName("ariyaOffices");
        HashMap<String, Object> samanModel = new HashMap<>();
        samanModel.put("properties", office);
        createNodeRequestModel.setProperties(samanModel);

        HttpEntity<CreateNodeRequestModel> requestEntity = new HttpEntity<CreateNodeRequestModel>(createNodeRequestModel, headers);

        MultiValueMap<String, Boolean> queryParam = new LinkedMultiValueMap<>();
        queryParam.add("updateIfExists", true);

        try {
            ResponseEntity<String> nodeId = restTemplate.exchange("http://localhost:8081/saman/api/v1/nodes/contents/documents/mci/a-office",
                    HttpMethod.POST,
                    requestEntity,
                    String.class,
                    queryParam
            );
            if (nodeId.getBody().isEmpty()) {
                throw new SamanExceptionException("import data to saman system is failed.");
            }
        } catch (Exception e) {
            throw new SamanExceptionException("connection to saman system is failed." + e.getMessage());
        }
    }
}
