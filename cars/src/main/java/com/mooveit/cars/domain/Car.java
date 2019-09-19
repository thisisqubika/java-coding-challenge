package com.mooveit.cars.domain;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/** Domain entity that models a car specification. */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(
    callSuper = true,
    exclude = {"parentModel", "subModels"})
public class Car extends AbstractPersistable<Long> {

  private String brand;
  private String modelName;
  private String type;
  private @Nullable String line;
  private Year productionYearFrom;
  /** When null indicates that a car is still in production */
  private @Nullable Year productionYearTo;

  @ManyToOne
  @JoinColumn(name = "engine_id")
  private Engine engine;

  @ManyToOne
  @JoinColumn(name = "wheels_id")
  private Wheels wheels;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_model_id")
  private @Nullable Car parentModel;

  @OneToMany(mappedBy = "parentModel", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Car> subModels = new ArrayList<>();

  @Builder(toBuilder = true)
  public Car(
          String brand,
          String modelName,
          String type,
          @Nullable String line,
          Year productionYearFrom,
          @Nullable Year productionYearTo,
          Engine engine,
          Wheels wheels,
          @Nullable Car parentModel,
          List<Car> subModels) {
    this.brand = brand;
    this.modelName = modelName;
    this.type = type;
    this.line = line;
    this.productionYearFrom = productionYearFrom;
    this.productionYearTo = productionYearTo;
    this.engine = engine;
    this.wheels = wheels;
    this.parentModel = parentModel;
      this.subModels = subModels == null ? new ArrayList<>() : subModels;
  }

  /**
   * Adds the subModel from the subModels list keeping the relationship with parent consistent. Call
   * this method in a transaction.
   *
   * @param subModel to add to subModels list
   */
  public void addSubModel(Car subModel) {
    subModels.add(subModel);
    subModel.setParentModel(this);
  }

  /**
   * Removes the subModel from the subModels list keeping the relationship with parent consistent.
   * Call this method in a transaction
   *
   * @param subModel to remove to subModels list
   */
  public void removeSubModel(Car subModel) {
    subModels.remove(subModel);
    subModel.setParentModel(null);
  }
}
