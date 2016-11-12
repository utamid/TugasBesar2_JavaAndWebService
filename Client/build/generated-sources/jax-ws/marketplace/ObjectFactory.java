
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

    private final static QName _ViewCatalog_QNAME = new QName("http://Marketplace/", "viewCatalog");
    private final static QName _ViewCatalogResponse_QNAME = new QName("http://Marketplace/", "viewCatalogResponse");
    private final static QName _ViewCatalogSearch_QNAME = new QName("http://Marketplace/", "viewCatalogSearch");
    private final static QName _ViewCatalogSearchResponse_QNAME = new QName("http://Marketplace/", "viewCatalogSearchResponse");
    private final static QName _YourProduct_QNAME = new QName("http://Marketplace/", "yourProduct");
    private final static QName _YourProductResponse_QNAME = new QName("http://Marketplace/", "yourProductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ViewCatalog }
     * 
     */
    public ViewCatalog createViewCatalog() {
        return new ViewCatalog();
    }

    /**
     * Create an instance of {@link ViewCatalogResponse }
     * 
     */
    public ViewCatalogResponse createViewCatalogResponse() {
        return new ViewCatalogResponse();
    }

    /**
     * Create an instance of {@link ViewCatalogSearch }
     * 
     */
    public ViewCatalogSearch createViewCatalogSearch() {
        return new ViewCatalogSearch();
    }

    /**
     * Create an instance of {@link ViewCatalogSearchResponse }
     * 
     */
    public ViewCatalogSearchResponse createViewCatalogSearchResponse() {
        return new ViewCatalogSearchResponse();
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
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCatalog }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "viewCatalog")
    public JAXBElement<ViewCatalog> createViewCatalog(ViewCatalog value) {
        return new JAXBElement<ViewCatalog>(_ViewCatalog_QNAME, ViewCatalog.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCatalogResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "viewCatalogResponse")
    public JAXBElement<ViewCatalogResponse> createViewCatalogResponse(ViewCatalogResponse value) {
        return new JAXBElement<ViewCatalogResponse>(_ViewCatalogResponse_QNAME, ViewCatalogResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCatalogSearch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "viewCatalogSearch")
    public JAXBElement<ViewCatalogSearch> createViewCatalogSearch(ViewCatalogSearch value) {
        return new JAXBElement<ViewCatalogSearch>(_ViewCatalogSearch_QNAME, ViewCatalogSearch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCatalogSearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "viewCatalogSearchResponse")
    public JAXBElement<ViewCatalogSearchResponse> createViewCatalogSearchResponse(ViewCatalogSearchResponse value) {
        return new JAXBElement<ViewCatalogSearchResponse>(_ViewCatalogSearchResponse_QNAME, ViewCatalogSearchResponse.class, null, value);
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
