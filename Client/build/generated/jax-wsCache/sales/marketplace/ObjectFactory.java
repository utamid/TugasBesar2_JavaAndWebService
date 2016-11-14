
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

    private final static QName _ViewSales_QNAME = new QName("http://Marketplace/", "viewSales");
    private final static QName _ViewSalesResponse_QNAME = new QName("http://Marketplace/", "viewSalesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ViewSales }
     * 
     */
    public ViewSales createViewSales() {
        return new ViewSales();
    }

    /**
     * Create an instance of {@link ViewSalesResponse }
     * 
     */
    public ViewSalesResponse createViewSalesResponse() {
        return new ViewSalesResponse();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewSales }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "viewSales")
    public JAXBElement<ViewSales> createViewSales(ViewSales value) {
        return new JAXBElement<ViewSales>(_ViewSales_QNAME, ViewSales.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewSalesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "viewSalesResponse")
    public JAXBElement<ViewSalesResponse> createViewSalesResponse(ViewSalesResponse value) {
        return new JAXBElement<ViewSalesResponse>(_ViewSalesResponse_QNAME, ViewSalesResponse.class, null, value);
    }

}
