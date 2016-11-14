
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

    private final static QName _Deleteproduct_QNAME = new QName("http://Marketplace/", "deleteproduct");
    private final static QName _DeleteproductResponse_QNAME = new QName("http://Marketplace/", "deleteproductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Deleteproduct }
     * 
     */
    public Deleteproduct createDeleteproduct() {
        return new Deleteproduct();
    }

    /**
     * Create an instance of {@link DeleteproductResponse }
     * 
     */
    public DeleteproductResponse createDeleteproductResponse() {
        return new DeleteproductResponse();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Deleteproduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "deleteproduct")
    public JAXBElement<Deleteproduct> createDeleteproduct(Deleteproduct value) {
        return new JAXBElement<Deleteproduct>(_Deleteproduct_QNAME, Deleteproduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteproductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "deleteproductResponse")
    public JAXBElement<DeleteproductResponse> createDeleteproductResponse(DeleteproductResponse value) {
        return new JAXBElement<DeleteproductResponse>(_DeleteproductResponse_QNAME, DeleteproductResponse.class, null, value);
    }

}
