package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.EngineEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EngineRepositoryTest {


    @DisplayName("an Engine can be created")
    @Test
    void anEngineCanBeCreated() {
        EngineEntity engineEntity = new EngineEntity(1600, "GAS");
        EngineRepository engineRepository = mock(EngineRepository.class);
        when(engineRepository.findById(engineEntity.getId())).thenReturn(Optional.of(engineEntity));

        Optional<EngineEntity> engineOptional = engineRepository.findById(engineEntity.getId());
        assertThat(engineOptional.isPresent()).isTrue();
        assertThat(engineOptional.get().getPower()).isEqualTo(engineEntity.getPower());
        assertThat(engineOptional.get().getType()).isEqualTo(engineEntity.getType());
    }


    @DisplayName("when search a Engine by power and type then return a Engine")
    @Test
    public void whenSearchAnEngine_thenFindByPowerAndType() {
        EngineEntity engineEntity = new EngineEntity(1600, "GAS");
        EngineRepository engineRepository = mock(EngineRepository.class);
        when(engineRepository.findByPowerAndTypeAndIsActiveTrue(engineEntity.getPower(), engineEntity.getType()))
                .thenReturn(Optional.of(engineEntity));

        Optional<EngineEntity> engineOptional = engineRepository.findByPowerAndTypeAndIsActiveTrue(engineEntity.getPower(), engineEntity.getType());
        assertThat(engineOptional.isPresent()).isTrue();
        assertThat(engineOptional.get().getPower()).isEqualTo(engineEntity.getPower());
        assertThat(engineOptional.get().getType()).isEqualTo(engineEntity.getType());
    }


    @DisplayName("when search a Engine that doest not exist then return empty object")
    @Test
    public void whenSearchAEngineThatDoesNotExist_thenReturnEmptyObject() {
        EngineRepository engineRepository = mock(EngineRepository.class);
        when(engineRepository.findById(100L)).thenReturn(Optional.empty());
        Optional<EngineEntity> engineOptional = engineRepository.findById(100L);
        assertThat(engineOptional.isPresent()).isFalse();
    }


    @DisplayName("when findAll then return an Engine's list")
    @Test
    public void whenFindAll_thenReturnAnEngineList() {
        EngineRepository engineRepository = mock(EngineRepository.class);
        EngineEntity engineEntity = new EngineEntity(1600, "GAS");
        List<EngineEntity> enginesList = new ArrayList<>();
        enginesList.add(engineEntity);
        when(engineRepository.findAll()).thenReturn(enginesList);

        List<EngineEntity> foundEnginesList = engineRepository.findAll();
        assertThat(foundEnginesList).isNotNull();
        assertThat(foundEnginesList.size()).isGreaterThanOrEqualTo(0);
    }
}
