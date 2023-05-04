package com.sticonsulting.ReleaseManager.service;

import com.sticonsulting.ReleaseManager.service.entity.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceServiceUnitTest {

    @Mock
    ServiceRepository serviceRepository;

   @Testable
   ServiceService serviceService;

   @BeforeEach
   void setup() {
       serviceService = new ServiceService(serviceRepository);
   }

   @Test
    void testSingleDeployment() {
       when(serviceRepository.getByNameAndVersionNr(anyString(), anyInt())).thenReturn(null);
       when(serviceRepository.findAll()).thenReturn(List.of());
       when(serviceRepository.save(any())).thenReturn(new Service(Integer.valueOf(1),"foo",1,1));


       assertThat( serviceService.deploy("foo",1)).isEqualTo(1);

       verify( serviceRepository).save(any());
   }

   @Test
   void testDeploymentOfSecondService() {
       when(serviceRepository.getByNameAndVersionNr(anyString(), anyInt())).thenReturn(null);
       when(serviceRepository.findAll()).thenReturn(List.of(new Service(Integer.valueOf(1),"bar",1,1)));

       Service mockService = new Service(null,"foo",1,2);
       when(serviceRepository.save(ArgumentMatchers.eq(mockService))).thenReturn( mockService );

       assertThat( serviceService.deploy("foo",1)).isEqualTo(2);
       verify( serviceRepository).save(any());
   }

    @Test
    void testRedeployment() {
        Service mockService = new Service(1,"foo",1,11);
        when(serviceRepository.getByNameAndVersionNr(anyString(), anyInt())).thenReturn(mockService);

        assertThat( serviceService.deploy("foo",1)).isEqualTo(11);
    }

    @Test
    void testGetVersion1() {
        Service fooFirst = new Service(1,"foo",1,1);
        Service barFirst = new Service(2,"bar",1,2);
        Service fooSecond = new Service(3,"foo",2,3);

        when( serviceRepository.findAll()).thenReturn( List.of( fooFirst, barFirst, fooSecond ) );

        assertThat(serviceService.getServicesWithSystemVersion(1)).isEqualTo(List.of(fooFirst));
    }

    @Test
    void testGetVersion2() {
        Service fooFirst = new Service(1,"foo",1,1);
        Service barFirst = new Service(2,"bar",1,2);
        Service fooSecond = new Service(3,"foo",2,3);

        when( serviceRepository.findAll()).thenReturn( List.of( fooFirst, barFirst, fooSecond ) );

        assertThat(serviceService.getServicesWithSystemVersion(2)).isEqualTo(List.of(fooFirst, barFirst));
    }

    @Test
    void testGetVersion3() {
        Service fooFirst = new Service(1,"foo",1,1);
        Service barFirst = new Service(2,"bar",1,2);
        Service fooSecond = new Service(3,"foo",2,3);

        when( serviceRepository.findAll()).thenReturn( List.of( fooFirst, barFirst, fooSecond ) );

        assertThat(serviceService.getServicesWithSystemVersion(2)).isEqualTo(List.of(fooFirst, barFirst));
    }
}
