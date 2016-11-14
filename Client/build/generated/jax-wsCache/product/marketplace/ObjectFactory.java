
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

    private final static QName _YourProduct_QNAME = new QName("http://Marketplace/", "yourProduct");
    private final static QName _YourProductResponse_QNAME = new QName("http://Marketplace/", "yourProductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link YourProduct }
     * 
     */
    public YourProduct createYourProduct() {
        return new YourProduct();
    }

    /**
     * Create an instance of {@link YourProductResponse }
     * 
     */
    public YourProductResponse createYourProductResponse() {
        return new YourProductResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link YourProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "yourProduct")
    public JAXBElement<YourProduct> createYourProduct(YourProduct value) {
        return new JAXBElement<YourProduct>(_YourProduct_QNAME, YourProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YourProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "yourProductResponse")
    public JAXBElement<YourProductResponse> createYourProductResponse(YourProductResponse value) {
        return new JAXBElement<YourProductResponse>(_YourProductResponse_QNAME, YourProductResponse.class, null, value);
    }

}
