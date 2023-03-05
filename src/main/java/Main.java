import model.Individuals;
import model.Ontology;
import model.OntologyClass;
import service.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Ontology wineAndCheeseOntology = createOntology();

        Parser parser = new Parser(wineAndCheeseOntology);
        parser.createOwlFile();
    }

    private static Ontology createOntology() {
        Ontology ontology = new Ontology();
        ontology.setNameSpace("http://www.nti.ru/wine-ontology#");

        OntologyClass cheese = new OntologyClass();

        cheese.setOntClassName("Cheese");

        List<OntologyClass> ontologyClasses = List.of(wineClassGenerator(), cheeseClassGenerator());
        ontology.setClasses(ontologyClasses);

        return ontology;
    }

    private static OntologyClass wineClassGenerator() {
        OntologyClass wine = new OntologyClass();
        wine.setOntClassName("Wine");

        OntologyClass redWine = new OntologyClass();
        OntologyClass whiteWine = new OntologyClass();

        redWine.setOntClassName("RedWine");
        whiteWine.setOntClassName("WhiteWine");

        whiteWine.setDisjointWith(redWine);

        whiteWine.setObjProperty("has_color");
        redWine.setObjProperty("has_color");

        List<OntologyClass> wineSubClasses = List.of(redWine, whiteWine);
        wine.setSubClasses(wineSubClasses);

        wine.setIndividuals(wineIndividualsGenerator());

        return wine;
    }

    private static OntologyClass cheeseClassGenerator() {
        OntologyClass cheese = new OntologyClass();
        cheese.setOntClassName("Cheese");

        OntologyClass youngCheese = new OntologyClass();
        OntologyClass oldCheese = new OntologyClass();

        youngCheese.setOntClassName("YoungCheese");
        oldCheese.setOntClassName("OldCheese");

        youngCheese.setDisjointWith(oldCheese);

        youngCheese.setObjProperty("goes_with");
        oldCheese.setObjProperty("goes_with");

        List<OntologyClass> cheeseSubClasses = List.of(youngCheese, oldCheese);
        cheese.setSubClasses(cheeseSubClasses);
        return cheese;
    }

    private static List<Individuals> wineIndividualsGenerator() {
        Individuals abrauDurso = new Individuals();
        abrauDurso.setName("AbrauDurso");
        Map<String, String> abrauDursoMap = Map.of("has_next", "red");
        abrauDurso.setProperty(abrauDursoMap);

        Individuals usWine1974 = new Individuals();
        usWine1974.setName("usWine1974");
        Map<String, String> usWine1974Map = Map.of("has_next", "white");
        usWine1974.setProperty(usWine1974Map);

        Individuals beaulieuVineyard = new Individuals();
        beaulieuVineyard.setName("beaulieuVineyard");
        Map<String, String> beaulieuVineyardMap = Map.of("has_next", "white");
        beaulieuVineyard.setProperty(beaulieuVineyardMap);

        return List.of(abrauDurso, usWine1974, beaulieuVineyard);
    }
}
