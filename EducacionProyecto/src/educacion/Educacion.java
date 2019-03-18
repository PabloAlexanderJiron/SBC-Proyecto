package educacion;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.rdf.model.RDFWriter;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;


public class Educacion {
	public static void main(String[] args) throws FileNotFoundException {
		
		Model model = ModelFactory.createDefaultModel();
		//ruta donde se va a aguardar el modelo
		File f= new File ("C:/Users/Jose Guarnizo/Desktop/NuevoProyectoSBC/proyecto.rdf");		
		FileOutputStream os = new FileOutputStream(f);
		
		
		BufferedReader br1 = null;
        BufferedReader br2 = null;
        
                
        Resource education = null;
        Resource provincia = null;
        Resource pais = null;
        Resource universidad = null;
        Resource escuelapolitecnica = null;
        Resource enrolmet = null;
        Resource educationf = null;
        Resource indata = null;
        Resource hasdata = null;
        Resource dimen = null;
        Resource di = null;
        Resource genero = null;
        Resource lugar = null;
        Resource nivel = null;
        Resource campo = null;
        Resource skosc = null;
        Resource dcatt = null;
        Resource bibob = null;
        Resource dctt = null;
        Resource urii = null;
        Resource distri = null;
        Resource inDat = null;
        Resource locat = null;
        
        
		//Fijar Prefijo para URI base de dos datos a crear 
        String dataPrefix = "http://example.org/data/";
        model.setNsPrefix("myData",dataPrefix);
        
        //Fijar prefijos de vocabularios incorporados en Jena
        String foaf = "http://xmlns.com/foaf/0.1/";
        model.setNsPrefix("foaf",foaf);              
        
        String dbo = "http://dbpedia.org/ontology/";
        model.setNsPrefix("dbo", dbo);
        Model dboModel = ModelFactory.createDefaultModel(); // MODELO PARA LA ONTOLOGIA
        dboModel.read(dbo);
        
        //prefijo ubicacion
        String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
        model.setNsPrefix("geo", geo);
        Model geoModel = ModelFactory.createDefaultModel(); // MODELO PARA LA ONTOLOGIA
        dboModel.read(geo);
        
        
        //prefijo schema
        String schema = "http://schema.org/";
        model.setNsPrefix("schema", schema);
        Model schemaModel = ModelFactory.createDefaultModel(); // MODELO PARA LA ONTOLOGIA
        dboModel.read(schema);
                                
        //prefijo skos
        String skos = "http://www.w3.org/2004/02/skos/core#";
        model.setNsPrefix("skos",skos);
        
        String dcat = "http://www.w3.org/ns/dcat#";
        model.setNsPrefix("dcat",dcat);
        
        String bibo = "http://purl.org/ontology/bibo/";
        model.setNsPrefix("bibo",bibo);
        
        String dct = "http://purl.org/dc/terms/";
        model.setNsPrefix("dct",dct);
        
        String owl = "http://www.w3.org/2002/07/owl#";
        model.setNsPrefix("owl",owl);
        
        String voc = "http://voc.odw.tw/";
        model.setNsPrefix("voc",voc);
        
        
                        
        Model dimension = ModelFactory.createDefaultModel();  // modelo para la ontologÃ­a
        dboModel.read(dbo) ; 
        
        Model enrolmentdata = ModelFactory.createDefaultModel();  // modelo para la ontologÃ­a
        dboModel.read(dbo) ;
        
        Model eduactionfields = ModelFactory.createDefaultModel();  // modelo para la ontologÃ­a
        dboModel.read(dbo) ; 
        
        Model hasData = ModelFactory.createDefaultModel();  // modelo para la ontologÃ­a
        dboModel.read(dbo) ; 
        
        Model inDataset = ModelFactory.createDefaultModel();  // modelo para la ontologÃ­a
        dboModel.read(dbo) ;                        

        //-------------------------------
        //Importar y leer el model creado en protege
        

        OntModel myModel = ModelFactory.createOntologyModel();
        
        //myModel.read("C:/Users/Jose Guarnizo/Desktop/NuevoSBC.owl", "RDF/XML");	
        //myModel.write(System.out, "RDF/XML");
        
        
        try {
        	
        	//DATA ESTADISTICAS MATRICULADOS
        	br1 = new BufferedReader(new FileReader("C:/Users/Jose Guarnizo/Desktop/NuevoProyectoSBC/estadisticas.csv"));
            String line1;
            String line2;
            br1.readLine();
            
            
            while ((line1 = br1.readLine()) != null) {
                String[] data1 = line1.split(";");
                
                String matricu = "EducacionInstitucional".replaceAll("\\s+", "").replaceAll("\"", "");
                String province = "Province".replaceAll("\\s+", "").replaceAll("\"", "");
                String paiss = "Country".replaceAll("\\s+", "").replaceAll("\"", "");
                String universidadd = "University".replaceAll("\\s+", "").replaceAll("\"", "");
                String schoolP = "SchoolPolitecnic".replaceAll("\\s+", "").replaceAll("\"", "");
                String enrrol = "EnrolmentData".replaceAll("\\s+", "").replaceAll("\"", "");
                String dimensioon = "Dimension".replaceAll("\\s+", "").replaceAll("\"", "");
                String data = "Dataset".replaceAll("\\s+", "").replaceAll("\"", "");
                String uri = "Uri".replaceAll("\\s+", "").replaceAll("\"", "");
                String dis = "Distribution".replaceAll("\\s+", "").replaceAll("\"", "");
                String in = "inDataset".replaceAll("\\s+", "").replaceAll("\"", "");
                String d = "dimension".replaceAll("\\s+", "").replaceAll("\"", "");
                String gen = "Gender".replaceAll("\\s+", "").replaceAll("\"", "");
                String skoss = "Concept".replaceAll("\\s+", "").replaceAll("\"", "");
                String has = "hasData".replaceAll("\\s+", "").replaceAll("\"", "");
                String le = "LevelOfEducation".replaceAll("\\s+", "").replaceAll("\"", "");
                
        		//System.out.println("Llaeg");
                //Modelando las Clases 
                
                //Educacion
                
                education = model.createResource(dataPrefix + matricu)                                                      		                		               
                		.addProperty(RDF.type,  myModel.getResource(dbo + "EducationalInstitution"))
                		.addProperty(FOAF.name, model.createLiteral(data1[3].replaceAll("\"", ""), "es"))
                		.addProperty(geoModel.getProperty(geo + "lat"), model.createTypedLiteral(data1[4], XSDDatatype.XSD))
                		.addProperty(geoModel.getProperty(geo + "long"), model.createTypedLiteral(data1[5], XSDDatatype.XSD))
                		.addProperty(geoModel.getProperty(schema + "alternateName"), model.createTypedLiteral(data1[0], XSDDatatype.XSD));                		
                
                
                //Provincia                
                provincia = model.createResource(dataPrefix + province)
                		.addProperty(RDF.type,  myModel.getResource(dbo + "Province"))
                		.addProperty(FOAF.name, model.createLiteral(data1[8].replaceAll("\"", ""), "es"));
                
                
                //Pais
                pais = model.createResource(dataPrefix + paiss)
                		.addProperty(RDF.type,  myModel.getResource(dbo + "Country"))
                        .addProperty(FOAF.name, "Ecuador");
                
                
                //Universidad
                universidad = model.createResource(dataPrefix + universidadd)
                		.addProperty(RDF.type,  myModel.getResource(dbo + "University"));
                
                
                //Escuela Politecnica
                escuelapolitecnica = model.createResource(dataPrefix + schoolP)                		
                		.addProperty(RDF.type, myModel.getResource(voc + "SchoolPolitecnic"))                		                		
                		.addProperty(RDFS.comment, "Escuelas Politécnicas de Educación superior")
                		.addProperty(RDFS.domain, education)
                		.addProperty(RDFS.label, "School Politecnic");
                		//.addProperty(FOAF.name, model.createLiteral(data1[3].replaceAll("\"", ""), "es"));                
                
                //EnrolmentData
                enrolmet = model.createResource(dataPrefix + enrrol)
                		.addProperty(RDF.type, myModel.getResource(voc + "EnrolmentData"))                		
                		.addProperty(RDFS.comment, "Apunta a las inscripciones que puede a ver dentro de una institución superior")
                		.addProperty(RDFS.label, "Enrolment Data")                		
                		.addProperty(RDFS.domain, education)
                		.addProperty(geoModel.getProperty(dbo + "publicationDate"), model.createTypedLiteral(data1[1], XSDDatatype.XSD))
                		.addProperty(RDFS.range, geoModel.getProperty(voc + "Dimension"))
                		.addProperty(RDF.value, model.createTypedLiteral(data1[6], XSDDatatype.XSDint));
                
                
                //Dimension
                dimen = model.createResource(dataPrefix + dimensioon)
                		.addProperty(RDF.type, myModel.getResource(voc + "Dimension"))                		
                		.addProperty(RDFS.comment, "Compuesto por varios factores que contiene una educación superior")
                		.addProperty(RDFS.label, "Dimension")                		
                		.addProperty(RDFS.domain, enrolmet)
                		.addProperty(RDFS.range, geoModel.getProperty(dcat + "Dataset"));
                
              //Dataset
                dcatt = model.createResource(dataPrefix + data)
                		.addProperty(RDF.type, myModel.getResource(dcat + "DataSet"))
                		.addProperty(geoModel.getProperty(dbo + "publicationDate"), XSDDatatype.XSD)
                		.addProperty(geoModel.getProperty(dct + "title"), XSDDatatype.XSD);
                
                //Uri
                urii = model.createResource(dataPrefix + uri)
                		.addProperty(RDF.type, myModel.getResource(bibo + "Uri"));
                
                
              //Distribucion
                distri = model.createResource(dataPrefix + dis)
                		.addProperty(RDF.type, myModel.getResource(dcat + "Distribution"))
                		.addProperty(geoModel.getProperty(dcat + "accessURL"), XSDDatatype.XSD);
                
                
              //Genero
                genero = model.createResource(dataPrefix + gen)                		
                		.addProperty(RDF.type, myModel.getResource(schema + "Gender"))
                		.addProperty(FOAF.name, model.createTypedLiteral(data1[9], XSDDatatype.XSD));
                                
                
               //Skos Concept
                 skosc = model.createResource(dataPrefix + skoss)
                        .addProperty(RDF.type, myModel.getResource(skos + "Concept"));
                 
               //Nivel
                 nivel = model.createResource(dataPrefix + le)
                         .addProperty(RDF.type, myModel.getResource(dbo + "LevelOfEducation"))
                         .addProperty(FOAF.name, model.createTypedLiteral(data1[7], XSDDatatype.XSD));
                 
                 
                 
                
                //-------------------------
                //Propiedades 
                
                //inDataset
                inDat = model.createProperty(dataPrefix + in)
                		.addProperty(RDF.type, myModel.getResource(voc + "inDataset"))
                		.addProperty(RDFS.comment, "Contenido del Dataset")
                		.addProperty(RDFS.label, "inDataset")                		
                		.addProperty(RDFS.domain, dimen)
                		.addProperty(RDFS.range, geoModel.getProperty(dcat + "Dataset"));
                
                //dimension
                di = model.createProperty(dataPrefix + d)
                		.addProperty(RDF.type, myModel.getResource(voc + "dimension"))
                		.addProperty(RDFS.comment, "Dimension de enrolment data")
                		.addProperty(RDFS.label, "dimension")                		
                		.addProperty(RDFS.domain, enrolmet)
                		.addProperty(RDFS.range, geoModel.getProperty(voc + "Dimension"));
                
                
                //hasdata
                hasdata = model.createProperty(dataPrefix + has)
                		.addProperty(RDF.type, myModel.getResource(voc + "hasData"))
                		.addProperty(RDFS.comment, "Obtención de Data contenido")
                		.addProperty(RDFS.label, "Has Data")                		
                		.addProperty(RDFS.domain, education)
                		.addProperty(RDFS.range, geoModel.getProperty(voc + "EnrolmentData"));
                
                
 
            }
            
            //DATA ESTADISTICAS CAMPOS EDUCACION            
        	br2 = new BufferedReader(new FileReader("C:/Users/Jose Guarnizo/Desktop/NuevoProyectoSBC/campoCC.csv"));            
            br2.readLine();
            
            while ((line2 = br2.readLine()) != null) {
                String[] data2 = line2.split(";");                
                String cam = "EducationFields".replaceAll("\\s+", "").replaceAll("\"", "");
                
                campo = model.createResource(dataPrefix + cam)
                		.addProperty(RDF.type, myModel.getResource(voc + "EducationFields"))
                		.addProperty(RDFS.comment, "Campos de educación o Areas que ofrece cada universidad")
                		.addProperty(RDFS.label, "Education Fields")                		                		
                		.addProperty(FOAF.name, model.createLiteral(data2[0].replaceAll("\"", ""), "es"));                                            		                		                    
            	 	
            	
            }     
            
            
            //Relaciones            
            model.add(education, myModel.getProperty(dbo + "province"), provincia);
            model.add(provincia, myModel.getProperty(dbo + "country"), pais);
            model.add(universidad, RDFS.subClassOf, education);
            model.add(escuelapolitecnica, RDFS.subClassOf, education);                       
            model.add(enrolmet, myModel.getProperty(voc + "dimension"), dimen);
            model.add(dimen, myModel.getProperty(voc + "inDataset"), dcatt);
            model.add(enrolmet, myModel.getProperty(dbo + "location"), provincia);
            model.add(genero, RDFS.subClassOf, dimen);
            model.add(campo, RDFS.subClassOf, dimen);
            model.add(education, myModel.getProperty(voc + "hasData"), enrolmet);
            model.add(nivel, RDFS.subClassOf, dimen);
            model.add(skosc, myModel.getProperty(owl + "equivalentClass"), campo);            
                                                                     		                                             		                		                               
        	
        }
        catch (Exception e) {
        	e.printStackTrace();
			
		}finally {
            try {
                if (br1 != null) {
                    br1.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        StmtIterator iter = model.listStatements();
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            Resource subject = stmt.getSubject();     // get the subject
            Property predicate = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();      // get the object

           // System.out.print(subject.toString());
            //System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
              //  System.out.print(" \"" + object.toString() + "\"");
            }

            //System.out.println(" .");
        }

        // now write the model in XML form to a file
        System.out.println("MODELO RDF EDUCATION------");
        //model.write(System.out, "N-TRIPLE");
        System.out.println("------");
        
        //en xml
        model.write(System.out, "RDF/XML");

        // Save to a file
        //RDFWriter writer = model.getWriter("N-TRIPLE"); //RDF/XML
        RDFWriter writer = model.getWriter("RDF/XML");
        
        writer.write(model, os, "");
        
       
        //Cerrar modelos
        dboModel.close();
        model.close();
        
        
		
	}

}
