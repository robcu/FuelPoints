package org.FuelPoints.utilities.parsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.FuelPoints.utilities.parsers.JsonApiDataParser;

public class RootParser<T> {
    JsonApiDataParser<T> data;

    public RootParser(
            @JsonProperty("data") JsonApiDataParser<T> data) {
        this.data = data;
    }

    public JsonApiDataParser<T> getData() {
        return this.data;
    }
}