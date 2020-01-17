package store.business.util;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 * 
 * This class create a new client in the file clients.xml and collect information about client in this file like the name, the surname or the address
 * @author Elias
 *
 */

public class Client {
	
	private int id_client;
	
	public Client() {
		

	}
	
	 /**
	    *Create a new client in the file clients.xml
	    * 
	    * @param adresse_client, nom_client, prenom_client
	    *The adress of the client
	    *The name of the client
	    *The surname of the client
	    * 
	    *@throws ErreurOuvertureXML
	    *If clients.xml cannot be found, close the program    
	    */
	
	public void modif_xml(String adresse_client, String nom_client, String prenom_client) {
				
		get_maxIdClient();
		id_client+=1;
	
		File fileXML = new File("./files/clients.xml");
		if (!fileXML.exists()) {
			try {
				throw new ErreurOuvertureXML("Can't find clients.xml, unable to start program");
			} catch (ErreurOuvertureXML e) {
				e.printStackTrace();
			}
			}
		
		 try {

			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		      DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
		        
		        Document doc = docBuilder.parse(fileXML);
		        Element racine = doc.getDocumentElement();

		        Element nom = doc.createElement(nom_client);
		        racine.appendChild(nom);
		        
		        Element id = doc.createElement("id");
		        id.appendChild(doc.createTextNode(""+id_client));
		        nom.appendChild(id);
		 
		        Element prenom = doc.createElement("prenom");
		        prenom.appendChild(doc.createTextNode(prenom_client));
		        nom.appendChild(prenom);
		 
		        Element mobile = doc.createElement("adresse");
		        mobile.appendChild(doc.createTextNode(adresse_client));
		       	nom.appendChild(mobile);

		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        Transformer transformer = transformerFactory.newTransformer();
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        DOMSource source = new DOMSource(doc);
		        StreamResult resultat = new StreamResult(fileXML);
		 
		        transformer.transform(source, resultat);
		 
		     } catch (ParserConfigurationException pce) {
		         pce.printStackTrace();
		     } catch (TransformerException tfe) {
		         tfe.printStackTrace();
		     } catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}
	
	 /**
	    *Collect the identifiant of the last client in the file produits.xml
	    * 
	    *
	    *@throws ErreurOuvertureXML
	    *If clients.xml cannot be found, close the program    
	    */
	
	public void get_maxIdClient() {

		File fileXML = new File("./files/clients.xml");
		if (!fileXML.exists()) {
			try {
				throw new ErreurOuvertureXML("Can't find clients.xml, unable to start program");
			} catch (ErreurOuvertureXML e) {
				e.printStackTrace();
			}
			}
		
	     try {
	    	 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         Document xml = builder.parse(fileXML);
	         XPathFactory xpf = XPathFactory.newInstance();
	         XPath path = xpf.newXPath();
	         factory.setNamespaceAware(true);
	         XPathExpression expr;
	         
	      	 expr = path.compile("count(/client/*)");
	         	         
	         Object result = expr.evaluate(xml, XPathConstants.NUMBER);
	         double verifNode_id = (double) result;
	         if (verifNode_id > 0) 
	         {
		         id_client = (int) verifNode_id;
	         }
	         
	         else {
	        	 id_client = 0;
	         }
	         
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
	     
 }
	 /**
	    *Collect an information about a client in the file cliens.xml
	    * 
	    * @param client_name, information_client
	    *The name of the client
	    *The information about the client that we need
	    * 
	    * @return A string which is an information in the XML about a client      
	    *@throws ErreurOuvertureXML
	    *If clients.xml cannot be found, close the program    
	    */
	
	
	public String get_informationClient(String client_name,String information_client) {

		File fileXML = new File("./files/clients.xml");
		if (!fileXML.exists()) {
			try {
				throw new ErreurOuvertureXML("Can't find clients.xml, unable to start program");
			} catch (ErreurOuvertureXML e) {
				e.printStackTrace();
			}
			}
	     try {
	    	 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         Document xml = builder.parse(fileXML);
	         XPathFactory xpf = XPathFactory.newInstance();
	         XPath path = xpf.newXPath();
	         factory.setNamespaceAware(true);
	         XPathExpression expr;

	      	 expr = path.compile("//"+client_name+"/"+information_client+"/text()");
	         
	         
	         Object result = expr.evaluate(xml, XPathConstants.STRING);
	         String nodes = (String) result;
	         
	        String Name = nodes;
		    return Name;
	         
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
	    return null;
}
	
	 /**
	    *Verify if a client exist in the file clients.xml
	    * 
	    * @param client_name
	    *The name of the client
	    * 
	    * @return A boolean, true if the client exist in the file, else false
	    *@throws ErreurOuvertureXML
	    *If clients.xml cannot be found, close the program    
	    */
	
	public boolean vérification_existenceClient(String client_name) {
		
		 boolean verifNode_id = false;
			File fileXML = new File("./files/clients.xml");
			if (!fileXML.exists()) {
				try {
					throw new ErreurOuvertureXML("Can't find clients.xml, unable to start program");
				} catch (ErreurOuvertureXML e) {
					e.printStackTrace();
				}
				}
	     try {
	    	 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         Document xml = builder.parse(fileXML);
	         XPathFactory xpf = XPathFactory.newInstance();
	         XPath path = xpf.newXPath();
	         factory.setNamespaceAware(true);
	         XPathExpression expr;
	         
	      	 expr = path.compile("//"+client_name);
	         
	         Object result = expr.evaluate(xml, XPathConstants.BOOLEAN);
	         verifNode_id = (boolean) result;	         
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }

	     return verifNode_id;
}
	
}
