package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class GeoJSON {
    private String type;
    private List<Feature> features;
}
