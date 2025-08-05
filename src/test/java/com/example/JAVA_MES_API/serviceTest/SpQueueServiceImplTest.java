package com.example.JAVA_MES_API.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SpExecutionEvent;
import com.example.JAVA_MES_API.api.entity.SpExecutionQueue;
import com.example.JAVA_MES_API.api.entity.SpMapping;
import com.example.JAVA_MES_API.api.repository.SpExecutionQueueRepository;
import com.example.JAVA_MES_API.api.repository.SpMappingRepository;
import com.example.JAVA_MES_API.api.service.QueueStatusService;
import com.example.JAVA_MES_API.api.service.SpQueueServiceImpl;

import jakarta.persistence.EntityManager;

class SpQueueServiceImplTest {

    @Mock
    private SpExecutionQueueRepository spExecutionQueueRepository;
    @Mock
    private SpMappingRepository spMappingRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private QueueStatusService queueStatusService;
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private SpQueueServiceImpl spQueueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void SuccesCreateAndPublish() {
        // given
        SignRequestDto dto = new SignRequestDto();
        dto.setSignCd("WORK_ORDER");
        dto.setKey1("ORDER123");

        SpMapping mapping = new SpMapping();
        mapping.setSpCd("WORK_ORDER");
        mapping.setSpSchema("schema1");
        mapping.setSpName("sp_work_order");

        when(spMappingRepository.findById("WORK_ORDER")).thenReturn(Optional.of(mapping));

        SpExecutionQueue savedQueue = new SpExecutionQueue();
        savedQueue.setQueId(1L);
        when(spExecutionQueueRepository.save(any())).thenReturn(savedQueue);

        long queId = spQueueService.createAndPublish(dto);

        assertEquals(1L, queId);
        verify(spExecutionQueueRepository, times(1)).save(any());
        verify(applicationEventPublisher, times(1)).publishEvent(any(SpExecutionEvent.class));
    }

    @Test
    void FailCreateAndPublish() {

        SignRequestDto dto = new SignRequestDto();
        dto.setSignCd("INVALID");
        when(spMappingRepository.findById("INVALID")).thenReturn(Optional.empty());


        assertThrows(IllegalArgumentException.class, () -> spQueueService.createAndPublish(dto));
        verify(applicationEventPublisher, never()).publishEvent(any());
    }

    @Test
    void FailCallSP() {
        // given
        SpExecutionQueue queue = new SpExecutionQueue();
        queue.setQueId(1L);
        queue.setSpName("sp_test");
        queue.setExecParams("'param'");
        queue.setStatus("READY");
        queue.setCnt(0);

        when(spExecutionQueueRepository.findById(1L)).thenReturn(Optional.of(queue));
        doThrow(new RuntimeException("SP Error"))
                .when(entityManager).createNativeQuery(anyString());

        SpExecutionEvent event = new SpExecutionEvent(new Object(), 1L);

        assertThrows(RuntimeException.class, () -> spQueueService.callSP(event));

        assertEquals("FAIL", queue.getStatus());
        assertEquals(1, queue.getCnt());
        verify(queueStatusService, times(1)).UpdateQueStatus(queue);
    }
}
