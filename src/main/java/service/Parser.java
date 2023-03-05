package service;

import lombok.extern.slf4j.Slf4j;
import model.Ontology;
import model.OntologyClass;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.apache.jena.ontology.OntModelSpec.OWL_MEM;

@Slf4j
public class Parser {

    private Ontology ontology;
    private Model model;
    private OntModel ontModel;
    private final String nameSpace;


    public Parser(Ontology ontology) {
        this.ontology = ontology;
        this.nameSpace = ontology.getNameSpace();
    }

    public void createOwlFile() {
        model = ModelFactory.createDefaultModel();
        ontModel = ModelFactory.createOntologyModel(OWL_MEM, model);

        fillClasses(ontology);

        try {
            OutputStream outputStream = new FileOutputStream("src/main/resources/wine_and_cheese_v1.owl");
            ontModel.write(outputStream, "RDF/XML-ABBREV");
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillClasses(Ontology ontology) {
        for (OntologyClass aClass : ontology.getClasses()) {

            if (aClass.getOntClassName() != null) {
                ontModel.createClass(nameSpace + aClass.getOntClassName());
            } else {
                log.error("Class name is null!");
                throw new RuntimeException("Class name is null!");
            }

            if (aClass.getSubClasses() != null) {
                addSubClasses(ontModel.getOntClass(nameSpace + aClass.getOntClassName()), aClass.getSubClasses());
                log.info("All classes filled");
            }
        }
    }

    private void addSubClasses(OntClass ontClass, List<OntologyClass> classesToBeAdded) {
        if (classesToBeAdded != null) {
            for (OntologyClass aClass : classesToBeAdded) {
                ontClass.addSubClass(ontModel.createClass(nameSpace + aClass.getOntClassName()));
                addSubClasses(ontModel.getOntClass(aClass.getOntClassName()), aClass.getSubClasses());
            }
        }
    }
}
