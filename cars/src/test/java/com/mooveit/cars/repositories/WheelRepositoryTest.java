package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.WheelEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WheelRepositoryTest {

    @DisplayName("a Wheel can be created")
    @Test
    void aWheelCanBeCreated() {
        WheelEntity wheelEntity = new WheelEntity("R16", "ALLOY");
        WheelRepository wheelRepository = mock(WheelRepository.class);
        when(wheelRepository.findById(wheelEntity.getId())).thenReturn(Optional.of(wheelEntity));

        Optional<WheelEntity> wheelOptional = wheelRepository.findById(wheelEntity.getId());
        assertThat(wheelOptional.isPresent()).isTrue();
        assertThat(wheelOptional.get().getSize()).isEqualTo(wheelEntity.getSize());
        assertThat(wheelOptional.get().getType()).isEqualTo(wheelEntity.getType());
    }


    @DisplayName("when search a Wheel by size and type then return a Wheel")
    @Test
    public void whenSearchAWheel_thenFindBySizeAndType() {
        WheelEntity wheelEntity = new WheelEntity("R16", "ALLOY");
        WheelRepository wheelRepository = mock(WheelRepository.class);
        when(wheelRepository.findBySizeAndTypeAndIsActiveTrue(wheelEntity.getSize(), wheelEntity.getType()))
                .thenReturn(Optional.of(wheelEntity));

        Optional<WheelEntity> wheelOptional = wheelRepository.findBySizeAndTypeAndIsActiveTrue(wheelEntity.getSize(), wheelEntity.getType());
        assertThat(wheelOptional.isPresent()).isTrue();
        assertThat(wheelOptional.get().getSize()).isEqualTo(wheelEntity.getSize());
        assertThat(wheelOptional.get().getType()).isEqualTo(wheelEntity.getType());
    }


    @DisplayName("when search a Wheel that doest not exist then return empty object")
    @Test
    public void whenSearchAWheelThatDoesNotExist_thenReturnEmptyObject() {
        WheelRepository wheelRepository = mock(WheelRepository.class);
        when(wheelRepository.findById(100L)).thenReturn(Optional.empty());
        Optional<WheelEntity> wheelOptional = wheelRepository.findById(100L);
        assertThat(wheelOptional.isPresent()).isFalse();
    }


    @DisplayName("when findAll then return a Wheel's list")
    @Test
    public void whenFindAll_thenReturnAWheelList() {
        WheelRepository wheelRepository = mock(WheelRepository.class);
        WheelEntity wheelEntity = new WheelEntity("R16", "ALLOY");
        List<WheelEntity> wheelsList = new ArrayList<>();
        wheelsList.add(wheelEntity);
        when(wheelRepository.findAll()).thenReturn(wheelsList);

        List<WheelEntity> foundWheelsList = wheelRepository.findAll();
        assertThat(foundWheelsList).isNotNull();
        assertThat(foundWheelsList.size()).isGreaterThanOrEqualTo(0);
    }
}
