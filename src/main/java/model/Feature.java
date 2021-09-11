package model;

import lombok.Data;

@Data
public class Feature {
    private final String type = "Feature";
    private Properties properties;
    private Geo geometry;
}
