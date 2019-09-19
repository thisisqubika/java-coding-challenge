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
public class Wheels extends AbstractPersistable<Long> {

  private int size;
  private Type type;

  @Builder(toBuilder = true)
  public Wheels(int size, Type type) {
    this.size = size;
    this.type = type;
  }

  public enum Type {
    STEEL,
    ALLOY,
    CHROME
  }
}
