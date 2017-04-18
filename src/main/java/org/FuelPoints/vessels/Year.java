package org.FuelPoints.vessels;

import org.FuelPoints.utilities.HasId;

public class Year implements HasId {
  String value;

  public Year(String value) {
    this.value = value;
  }

  public String getId() {
    return value;
  };

  public void setId(String value) {
    this.value = value;
  }
}
