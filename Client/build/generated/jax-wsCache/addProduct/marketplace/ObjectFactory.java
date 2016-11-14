
package marketplace;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the marketplace package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Insertnewproduct_QNAME = new QName("http://Marketplace/", "insertnewproduct");
    private final static QName _InsertnewproductResponse_QNAME = new QName("http://Marketplace/", "insertnewproductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Insertnewproduct }
     * 
     */
    public Insertnewproduct createInsertnewproduct() {
        return new Insertnewproduct();
    }

    /**
     * Create an instance of {@link InsertnewproductResponse }
     * 
     */
    public InsertnewproductResponse createInsertnewproductResponse() {
        return new InsertnewproductResponse();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link Itempurchase }
     * 
     */
    public Itempurchase createItempurchase() {
        return new Itempurchase();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Insertnewproduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "insertnewproduct")
    public JAXBElement<Insertnewproduct> createInsertnewproduct(Insertnewproduct value) {
        return new JAXBElement<Insertnewproduct>(_Insertnewproduct_QNAME, Insertnewproduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertnewproductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "insertnewproductResponse")
    public JAXBElement<InsertnewproductResponse> createInsertnewproductResponse(InsertnewproductResponse value) {
        return new JAXBElement<InsertnewproductResponse>(_InsertnewproductResponse_QNAME, InsertnewproductResponse.class, null, value);
    }

}
