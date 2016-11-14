
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

    private final static QName _Getitem_QNAME = new QName("http://Marketplace/", "getitem");
    private final static QName _GetitemResponse_QNAME = new QName("http://Marketplace/", "getitemResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Getitem }
     * 
     */
    public Getitem createGetitem() {
        return new Getitem();
    }

    /**
     * Create an instance of {@link GetitemResponse }
     * 
     */
    public GetitemResponse createGetitemResponse() {
        return new GetitemResponse();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Getitem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "getitem")
    public JAXBElement<Getitem> createGetitem(Getitem value) {
        return new JAXBElement<Getitem>(_Getitem_QNAME, Getitem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetitemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "getitemResponse")
    public JAXBElement<GetitemResponse> createGetitemResponse(GetitemResponse value) {
        return new JAXBElement<GetitemResponse>(_GetitemResponse_QNAME, GetitemResponse.class, null, value);
    }

}
