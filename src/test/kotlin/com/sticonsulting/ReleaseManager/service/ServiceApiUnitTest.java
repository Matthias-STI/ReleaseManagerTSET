package com.sticonsulting.ReleaseManager.service;

import com.sticonsulting.ReleaseManager.service.dto.DeploymentRequest;
import com.sticonsulting.ReleaseManager.service.dto.DeploymentResponse;
import com.sticonsulting.ReleaseManager.service.entity.Service;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceApiUnitTest {

    @Mock
    ServiceService serviceService;

    @Testable
    ServiceApi serviceApi;

    @BeforeEach
    void setup() {
        serviceApi = new ServiceApi(serviceService);
    }

    @Test
    void getNonExistentVersionExpectEmpty() {
        when( serviceService.getLatestSystemVersion()).thenReturn(0 );
        assert(serviceApi.getServices(1).length ==0);
    }

    @Test
    void deployNew() {
        when( serviceService.deploy("foo",1)).thenReturn(1);
        DeploymentResponse deploymentResponse = new DeploymentResponse(1);
        assert(serviceApi.deploy(new DeploymentRequest("foo",1))).equals(deploymentResponse);
    }
}
