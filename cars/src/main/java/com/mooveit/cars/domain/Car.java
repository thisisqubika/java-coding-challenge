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
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString(
    callSuper = true,
    exclude = {"parentModel", "subModels"})
public class Car extends AbstractPersistable<Long> {

  private String brand;
  private String modelName;
  private String type;
  private @Nullable String line;
  private @Nullable
  Year productionYearFrom;
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
