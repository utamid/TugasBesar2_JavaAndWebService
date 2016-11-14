
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

    private final static QName _Like_QNAME = new QName("http://Marketplace/", "like");
    private final static QName _LikeResponse_QNAME = new QName("http://Marketplace/", "likeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Like_Type }
     * 
     */
    public Like_Type createLike_Type() {
        return new Like_Type();
    }

    /**
     * Create an instance of {@link LikeResponse }
     * 
     */
    public LikeResponse createLikeResponse() {
        return new LikeResponse();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Like_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "like")
    public JAXBElement<Like_Type> createLike(Like_Type value) {
        return new JAXBElement<Like_Type>(_Like_QNAME, Like_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LikeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "likeResponse")
    public JAXBElement<LikeResponse> createLikeResponse(LikeResponse value) {
        return new JAXBElement<LikeResponse>(_LikeResponse_QNAME, LikeResponse.class, null, value);
    }

}
