package store.business.util;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *This class has the task of save the information of the transaction in the file transactions.xml
 * 
 * 
 * 
 * @author Elias
 *
 */
public class Transaction {

	private int id_transaction;
	
	 /**
	    *Save the transaction in the file transactions.xml
	    * 
	    * @param id_client, nb_produit
	    *The identifiant of the client
	    *The number of product that the client bought
	    * 
	    *@throws ErreurOuvertureXML
	    *If transactions.xml cannot be found, close the program    
	    */
	
public void modif_xml(int id_client, int nb_produit) {
		
	File fileXML = new File("./files/transactions.xml");
	if (!fileXML.exists()) {
		try {
			throw new ErreurOuvertureXML("Can't find transactions.xml, unable to start program");
		} catch (ErreurOuvertureXML e) {
			e.printStackTrace();
		}
		}
	
	get_maxIdTransaction();
	id_transaction+=1;
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
		 try {
			 
		        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
		 
		        Document doc = docBuilder.parse(fileXML);
		        Element racine = doc.getDocumentElement();
		 
		 
		        // identifiant client
		        Element id = doc.createElement("Identifiant_client");
		        id.appendChild(doc.createTextNode(""+id_client));
		        racine.appendChild(id);
		 
		        // identifiant transaction
		        Element nom = doc.createElement("Identifiant_transaction");
		        nom.appendChild(doc.createTextNode(""+id_transaction));
		        racine.appendChild(nom);
		 
		        // nombre de produit
		        Element prenom = doc.createElement("Nombre_produit");
		        prenom.appendChild(doc.createTextNode(""+nb_produit));
		        racine.appendChild(prenom);
		 
		        //date
		        Element mobile = doc.createElement("Date");
		        mobile.appendChild(doc.createTextNode(format.format(date)));
		       	racine.appendChild(mobile);

		        // write the content into xml file
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
 *Collect the identifiant of the last transaction in the file transactions.xml
 *     
 *@throws ErreurOuvertureXML
 *If transactions.xml cannot be found, close the program    
 */

public void get_maxIdTransaction() {

	File fileXML = new File("./files/transactions.xml");
	if (!fileXML.exists()) {
		try {
			throw new ErreurOuvertureXML("Can't find transactions.xml, unable to start program");
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
        
    
       	 
     	 expr = path.compile("count(//transaction/Identifiant_transaction)");
        
        
        Object result = expr.evaluate(xml, XPathConstants.NUMBER);
        double verifNode_id = (double) result;
        if (verifNode_id > 0) {
       	 expr = path.compile("//Identifiant_transaction[last()]/text()");
       	 result = expr.evaluate(xml, XPathConstants.NODESET);
       	 NodeList  nodes2 = (NodeList) result;
	     id_transaction = Integer.parseInt(nodes2.item(0).getNodeValue());
        }
        
        else {
       	 id_transaction = 0;
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
}
