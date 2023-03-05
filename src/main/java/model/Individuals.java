package model;

import lombok.Data;

import java.util.Map;

@Data
public class Individuals {

    private String name;

    /**
     * String - DatatypeProperty. Example: has_color
     * String - name. Example: red
     */
    private Map<String, String> property;
}
