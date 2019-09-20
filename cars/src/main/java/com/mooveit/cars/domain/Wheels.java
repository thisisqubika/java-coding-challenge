package com.mooveit.cars.domain;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/** Domain entity that models an engine. */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true)
public class Wheels extends AbstractPersistable<Long> {

  private int size;
  private Type type;

  public enum Type {
    STEEL,
    ALLOY,
    CHROME
  }
}
