
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

    private final static QName _ViewPurchases_QNAME = new QName("http://Marketplace/", "viewPurchases");
    private final static QName _ViewPurchasesResponse_QNAME = new QName("http://Marketplace/", "viewPurchasesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ViewPurchases }
     * 
     */
    public ViewPurchases createViewPurchases() {
        return new ViewPurchases();
    }

    /**
     * Create an instance of {@link ViewPurchasesResponse }
     * 
     */
    public ViewPurchasesResponse createViewPurchasesResponse() {
        return new ViewPurchasesResponse();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewPurchases }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "viewPurchases")
    public JAXBElement<ViewPurchases> createViewPurchases(ViewPurchases value) {
        return new JAXBElement<ViewPurchases>(_ViewPurchases_QNAME, ViewPurchases.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewPurchasesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "viewPurchasesResponse")
    public JAXBElement<ViewPurchasesResponse> createViewPurchasesResponse(ViewPurchasesResponse value) {
        return new JAXBElement<ViewPurchasesResponse>(_ViewPurchasesResponse_QNAME, ViewPurchasesResponse.class, null, value);
    }

}
