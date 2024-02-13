package com.gamelectronics.updateofficeinfo.serviceImpl;

import com.gam.phoenix.samanapi.resource.model.request.CreateNodeRequestModel;
import com.gamelectronics.updateofficeinfo.exception.NotFoundOfficeException;
import com.gamelectronics.updateofficeinfo.exception.SamanExceptionException;
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

@Service
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;

    @Value("${BASE_PATH}")
    private String basePath;

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

        try {
            ResponseEntity<String> nodeId = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            if (nodeId.getBody().isEmpty()) {
                throw new SamanExceptionException("import data to saman system is failed.");
            }
        } catch (Exception e) {
            throw new SamanExceptionException("connection to saman system is failed." + e.getMessage());
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
