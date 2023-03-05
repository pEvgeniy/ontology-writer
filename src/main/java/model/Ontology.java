package model;

import lombok.Data;

import java.util.List;

@Data
public class Ontology {

    private String nameSpace;

    private List<OntologyClass> classes;

}
