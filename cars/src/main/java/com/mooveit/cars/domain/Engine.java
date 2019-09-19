package com.mooveit.cars.domain;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/** Domain entity that models an engine. */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Engine extends AbstractPersistable<Long> {

  private int power;
  private Type type;

  @Builder(toBuilder = true)
  public Engine(int power, Type type) {
    this.power = power;
    this.type = type;
  }

  public enum Type {
    GAS
  }
}
