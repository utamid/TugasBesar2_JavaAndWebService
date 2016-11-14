
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

    private final static QName _Edittodb_QNAME = new QName("http://Marketplace/", "edittodb");
    private final static QName _EdittodbResponse_QNAME = new QName("http://Marketplace/", "edittodbResponse");
    private final static QName _Showproduct_QNAME = new QName("http://Marketplace/", "showproduct");
    private final static QName _ShowproductResponse_QNAME = new QName("http://Marketplace/", "showproductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Edittodb }
     * 
     */
    public Edittodb createEdittodb() {
        return new Edittodb();
    }

    /**
     * Create an instance of {@link EdittodbResponse }
     * 
     */
    public EdittodbResponse createEdittodbResponse() {
        return new EdittodbResponse();
    }

    /**
     * Create an instance of {@link Showproduct }
     * 
     */
    public Showproduct createShowproduct() {
        return new Showproduct();
    }

    /**
     * Create an instance of {@link ShowproductResponse }
     * 
     */
    public ShowproductResponse createShowproductResponse() {
        return new ShowproductResponse();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Edittodb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "edittodb")
    public JAXBElement<Edittodb> createEdittodb(Edittodb value) {
        return new JAXBElement<Edittodb>(_Edittodb_QNAME, Edittodb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EdittodbResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "edittodbResponse")
    public JAXBElement<EdittodbResponse> createEdittodbResponse(EdittodbResponse value) {
        return new JAXBElement<EdittodbResponse>(_EdittodbResponse_QNAME, EdittodbResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Showproduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "showproduct")
    public JAXBElement<Showproduct> createShowproduct(Showproduct value) {
        return new JAXBElement<Showproduct>(_Showproduct_QNAME, Showproduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowproductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "showproductResponse")
    public JAXBElement<ShowproductResponse> createShowproductResponse(ShowproductResponse value) {
        return new JAXBElement<ShowproductResponse>(_ShowproductResponse_QNAME, ShowproductResponse.class, null, value);
    }

}
