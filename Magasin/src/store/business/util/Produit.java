package store.business.util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class collect information about product in the file produits.xml
 * 
 * 
 * @author Elias
 *
 */
public class Produit {
	
	private List<String> name_produit = new ArrayList<String>();
	 
	public Produit() {
		
		
	}
	 
	/**
     *Collect the product list in the file produits.xml
     * 
     * @param produit 
     *The type of the product selected in the graphical interface (DVDs, Livres, Tous, Jeux-vidéos)
     * 
     * @return
     *A list of product        
     *@throws ErreurOuvertureXML
     *If produits.xml cannot be found, close the program    
     */
   public List<String> get_name(String produit) {

		File fileXML = new File("./files/produits.xml");
		if (!fileXML.exists()) {
			try {
				throw new ErreurOuvertureXML("Can't find produits.xml, unable to start program");
			} catch (ErreurOuvertureXML e) {
				e.printStackTrace();
			}
			
			}
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	     try {
	    	 
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         Document xml = builder.parse(fileXML);
	         XPathFactory xpf = XPathFactory.newInstance();
	         XPath path = xpf.newXPath();
	         factory.setNamespaceAware(true);
	         XPathExpression expr;
	         
	       if (produit.equals("Tous")) {
	        	 
	       	 expr = path.compile("/Produit/*/Nom/@name");
	        }
	         
	         else {
	        	 
	      	 expr = path.compile("/Produit/"+produit+"/Nom/@name");
	         }
	         
	         Object result = expr.evaluate(xml, XPathConstants.NODESET);
	         NodeList nodes = (NodeList) result;
	         for (int i = 0; i < nodes.getLength(); i++) {
	        	 name_produit.add(nodes.item(i).getNodeValue());
	         }
	         return name_produit;
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
	     return name_produit;
   }
   
   /**
    *Collect an information about a product in the file produits.xml
    * 
    * @param type_produit, produit, information
    *The type of the product selected in the graphical interface (DVDs, Livres, Tous, Jeux-vidéos)
    *The name of the product 
    *The information that we need
    * 
    * @return A string which is an information in the XML about a product      
    *@throws ErreurOuvertureXML
    *If produits.xml cannot be found, close the program    
    */
   
   
   public String get_information(String type_produit, String produit,String information) {

	   File fileXML = new File("./files/produits.xml");
		if (!fileXML.exists()) {
			try {
				throw new ErreurOuvertureXML("Can't find produits.xml, unable to start program");
			} catch (ErreurOuvertureXML e) {
				e.printStackTrace();
			}
			}
		
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	     try {
	    	 
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         Document xml = builder.parse(fileXML);
	         XPathFactory xpf = XPathFactory.newInstance();
	         XPath path = xpf.newXPath();
	         factory.setNamespaceAware(true);
	         XPathExpression expr;

	      	 expr = path.compile("/Produit"+type_produit+"Nom[@name='"+produit+"']/"+information);
	         Object result = expr.evaluate(xml, XPathConstants.STRING);
	         String nodes = (String) result;
		     return nodes;
	         
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
	    
	     return "";
}
   /**
    *Collect the description of a product in the file produits.xml
    *     
    *@throws ErreurOuvertureXML
    *If produits.xml cannot be found, close the program    
    */
	 public String get_description(String produit) {

		   File fileXML = new File("./files/produits.xml");
			if (!fileXML.exists()) {
				try {
					throw new ErreurOuvertureXML("Can't find produits.xml, unable to start program");
				} catch (ErreurOuvertureXML e) {
					e.printStackTrace();
				}
				}
			
		   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		     try {
		    	 
		         DocumentBuilder builder = factory.newDocumentBuilder();
		         Document xml = builder.parse(fileXML);
		         XPathFactory xpf = XPathFactory.newInstance();
		         XPath path = xpf.newXPath();
		         factory.setNamespaceAware(true);
		         XPathExpression expr;

		         expr = path.compile("//Nom[@name='"+produit+"']/description");
		         Object result = expr.evaluate(xml, XPathConstants.STRING);
		         String nodes = (String) result;
			     return nodes;
		         
		      } catch (ParserConfigurationException e) {
		         e.printStackTrace();
		      } catch (SAXException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (XPathExpressionException e) {
		         e.printStackTrace();
		      }
		    
		     return "";
	}
 	}