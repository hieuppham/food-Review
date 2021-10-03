package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Geo {
    private final String type = "Point";
    private double[] coordinates;
}
