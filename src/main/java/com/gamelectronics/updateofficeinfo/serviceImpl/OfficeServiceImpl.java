package com.gamelectronics.updateofficeinfo.serviceImpl;

import com.gam.phoenix.samanapi.resource.model.request.CreateNodeRequestModel;
import com.gam.phoenix.spring.commons.service.NonPersistenceServiceException;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.repository.OfficeRepository;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import com.gamelectronics.updateofficeinfo.utils.OfficeBeanCopy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;

    @Value("${BASE_PATH}")
    private String basePath;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    @Transactional(rollbackOn = NonPersistenceServiceException.class)
    public List<Office> registerOffice(List<Office> offices) throws NonPersistenceServiceException {
        for (Office item : offices) {
            setOfficeDataForSamanSystem(item);
        }
        return officeRepository.saveAll(offices);
    }

    @Override
    @Transactional(rollbackOn = NonPersistenceServiceException.class)
    public void updateAllOfficeFiled(Office office) throws NonPersistenceServiceException {
        Optional<Office> foundOffice = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (foundOffice.isPresent()) {
            foundOffice = Optional.of(office);
            officeRepository.save(foundOffice.get());
            setOfficeDataForSamanSystem(foundOffice.get());
        } else {
            throw new NonPersistenceServiceException("404", "office " + office.getOfficeCode() + " notFound.");
        }
    }

    @Override
    @Transactional(rollbackOn = NonPersistenceServiceException.class)
    public void updateNotNullOfficeFiled(Office office) throws InvocationTargetException, IllegalAccessException, NonPersistenceServiceException {
        Optional<Office> foundOffice = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (foundOffice.isPresent()) {
            //todo : use mapstruct
            OfficeBeanCopy officeBeanCopy = new OfficeBeanCopy();
            officeBeanCopy.copyProperties(foundOffice.get(), office);
            officeRepository.save(foundOffice.get());
            setOfficeDataForSamanSystem(foundOffice.get());
        } else {
            throw new NonPersistenceServiceException("404", "office " + office.getOfficeCode() + " notFound.");
        }
    }

    @Override
    public Office getOffice(String officeCode) throws NonPersistenceServiceException {
        Optional<Office> office = officeRepository.findByOfficeCode(officeCode);
        if (office.isPresent()) {
            return office.get();
        } else {
            throw new NonPersistenceServiceException("404", "office " + officeCode + " notFound.");
        }
    }

    private void setOfficeDataForSamanSystem(Office office) throws NonPersistenceServiceException {
        String url = UriComponentsBuilder.fromUriString(basePath + "/nodes/contents/documents/mci/a-office")
                .queryParam("updateIfExists", true)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        CreateNodeRequestModel createNodeRequestModel = new CreateNodeRequestModel();
        createNodeRequestModel.setNodeType("mci:office");
        createNodeRequestModel.setName(office.getOfficeCode());
        createNodeRequestModel.setProperties(setSamanProperties(office));


        HttpEntity<CreateNodeRequestModel> requestEntity = new HttpEntity<CreateNodeRequestModel>(createNodeRequestModel, headers);
        ResponseEntity<String> nodeId;
        try {
            nodeId = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
        } catch (Exception e) {
            throw new NonPersistenceServiceException("500", "connection to saman system is failed." + e.getMessage());
        }
        if (nodeId.getBody().isEmpty()) {
            throw new NonPersistenceServiceException("500", "import data to saman system is failed.");
        }
    }

    private Map<String, Object> setSamanProperties(Office office) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("mci:officeCode", office.getOfficeCode());
        properties.put("mci:officeName", office.getOfficeName());
        properties.put("mci:provider", office.getProvider());
        properties.put("mci:officeType", office.getOfficeType());
        properties.put("mci:managerName", office.getManagerName());
        properties.put("mci:managerNationalId", office.getManagerNationalId());
        properties.put("mci:managerMobileNumber", office.getManagerMobileNumber());
        properties.put("mci:officeMobileNumber", office.getOfficeMobileNumber());

        properties.put("mci:centerId", office.getProvinceId());
        properties.put("mci:centerName", office.getProvinceName());
        properties.put("mci:orgId", office.getRegionId());
        properties.put("mci:orgName", office.getRegionName());

        properties.put("mci:officeAddress", office.getOfficeAddress());
        properties.put("mci:officePostalCode", office.getOfficePostalCode());
        properties.put("mci:officeStatus", office.getOfficeStatus());
        properties.put("mci:officeLevel", office.getOfficeLevel());
        properties.put("mci:officeLevelType", office.getOfficeLevelType());


        return properties;
    }
}
