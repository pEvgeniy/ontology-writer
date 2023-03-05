package model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OntologyClass {

    private String ontClassName;

    private List<OntologyClass> subClasses;

    private OntologyClass disjointWith;

    private String objProperty;

    private String datatypeProperty;

    private List<Individuals> individuals;

//    TODO - add addEquivalentClass field


}
