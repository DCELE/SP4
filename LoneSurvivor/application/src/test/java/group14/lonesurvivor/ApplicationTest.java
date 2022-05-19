package group14.lonesurvivor;

import com.badlogic.gdx.utils.XmlReader.Element;
import group14.common.services.IPlugin;
import group14.common.services.IUpdate;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.w3c.dom.Document;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import group14.common.gameobjects.Node;

public class ApplicationTest extends NbTestCase {
    
    
    // the path out xml file
    private static final String UPDATES_XML = "/Users/muhamadsdu/Desktop/SP4/LoneSurvivor/netbeans_site/updates.xml";
       private Document doc;
       private Node module;

    // auto generated code
    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false). 
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }
// auto generated code
    public ApplicationTest(String n) {
        super(n);
    }
// auto generated code
    public void testApplication() throws InterruptedException, IOException, XPathExpressionException {
        
        setupModule();
         // SETUP
        List<IUpdate> update = new CopyOnWriteArrayList<>();
        List<IPlugin> plugin = new CopyOnWriteArrayList<>();
        

        waitForUpdate(plugin, update);

        // ASSERTS: Enemy loaded
        assertEquals("One plugins", 4, plugin.size());
        assertEquals("One processors", 4, update.size());

       removeModule();
        waitForUpdate(plugin, update);

        // ASSERTS: Enemy unloaded
        assertEquals("One plugins", 3, plugin.size());
        assertEquals("One processors", 3, update.size());
                
        Module();
        waitForUpdate(plugin, update);
       
        assertEquals("One plugins", 4, plugin.size());
        assertEquals("One processors", 4, update.size());

        
    }
    
   
    //wait so it does not update immediattely.
    public void waitForUpdate(List<IPlugin> plugin, List<IUpdate> update) throws InterruptedException, IOException {
        Thread.sleep(10000);
        plugin.clear();
        plugin.addAll(Lookup.getDefault().lookupAll(IPlugin.class));

        update.clear();
        update.addAll(Lookup.getDefault().lookupAll(IUpdate.class));
    }
    
    
    private void setupModule() throws XPathExpressionException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        
        try {
            builder = factory.newDocumentBuilder();
            this.doc = builder.parse(UPDATES_XML);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//module[@codenamebase=\"lonesurvivor.Survivor\"]");
            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            System.out.println(nl.getLength());
            Element e = (Element) nl.item(0);
            String name = e.getAttribute("codenamebase");
            this.module = new Node(name, true, nl.item(0), nl.item(0).getParentNode());

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            Exceptions.printStackTrace(ex);
        
        }
        
    }
    
    private void removeModule() throws FileNotFoundException{
        Node node = this.module.getNode().cloneNode(true);
        this.module.getParent().removeChild(this.module.getNode());
        this.module.setNode(node);
        reloadFile();
    }
            
    private void Module() throws FileNotFoundException  {
        this.module.setActive(true);

        Node node = this.module.getNode();
        this.module.getParent().appendChild(node);
        reloadFile();
        
    }  
    
    private void reloadFile() throws FileNotFoundException{
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new FileOutputStream(UPDATES_XML));
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            Exceptions.printStackTrace(e);
        } catch (TransformerException | FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
      
        }
    }
    

}
