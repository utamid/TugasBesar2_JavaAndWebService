
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

    private final static QName _InsertPurchase_QNAME = new QName("http://Marketplace/", "insertPurchase");
    private final static QName _InsertPurchaseResponse_QNAME = new QName("http://Marketplace/", "insertPurchaseResponse");
    private final static QName _ShowBuyerInfo_QNAME = new QName("http://Marketplace/", "showBuyerInfo");
    private final static QName _ShowBuyerInfoResponse_QNAME = new QName("http://Marketplace/", "showBuyerInfoResponse");
    private final static QName _ShowProductInfo_QNAME = new QName("http://Marketplace/", "showProductInfo");
    private final static QName _ShowProductInfoResponse_QNAME = new QName("http://Marketplace/", "showProductInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertPurchase }
     * 
     */
    public InsertPurchase createInsertPurchase() {
        return new InsertPurchase();
    }

    /**
     * Create an instance of {@link InsertPurchaseResponse }
     * 
     */
    public InsertPurchaseResponse createInsertPurchaseResponse() {
        return new InsertPurchaseResponse();
    }

    /**
     * Create an instance of {@link ShowBuyerInfo }
     * 
     */
    public ShowBuyerInfo createShowBuyerInfo() {
        return new ShowBuyerInfo();
    }

    /**
     * Create an instance of {@link ShowBuyerInfoResponse }
     * 
     */
    public ShowBuyerInfoResponse createShowBuyerInfoResponse() {
        return new ShowBuyerInfoResponse();
    }

    /**
     * Create an instance of {@link ShowProductInfo }
     * 
     */
    public ShowProductInfo createShowProductInfo() {
        return new ShowProductInfo();
    }

    /**
     * Create an instance of {@link ShowProductInfoResponse }
     * 
     */
    public ShowProductInfoResponse createShowProductInfoResponse() {
        return new ShowProductInfoResponse();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPurchase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "insertPurchase")
    public JAXBElement<InsertPurchase> createInsertPurchase(InsertPurchase value) {
        return new JAXBElement<InsertPurchase>(_InsertPurchase_QNAME, InsertPurchase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPurchaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "insertPurchaseResponse")
    public JAXBElement<InsertPurchaseResponse> createInsertPurchaseResponse(InsertPurchaseResponse value) {
        return new JAXBElement<InsertPurchaseResponse>(_InsertPurchaseResponse_QNAME, InsertPurchaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowBuyerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "showBuyerInfo")
    public JAXBElement<ShowBuyerInfo> createShowBuyerInfo(ShowBuyerInfo value) {
        return new JAXBElement<ShowBuyerInfo>(_ShowBuyerInfo_QNAME, ShowBuyerInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowBuyerInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "showBuyerInfoResponse")
    public JAXBElement<ShowBuyerInfoResponse> createShowBuyerInfoResponse(ShowBuyerInfoResponse value) {
        return new JAXBElement<ShowBuyerInfoResponse>(_ShowBuyerInfoResponse_QNAME, ShowBuyerInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowProductInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "showProductInfo")
    public JAXBElement<ShowProductInfo> createShowProductInfo(ShowProductInfo value) {
        return new JAXBElement<ShowProductInfo>(_ShowProductInfo_QNAME, ShowProductInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowProductInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Marketplace/", name = "showProductInfoResponse")
    public JAXBElement<ShowProductInfoResponse> createShowProductInfoResponse(ShowProductInfoResponse value) {
        return new JAXBElement<ShowProductInfoResponse>(_ShowProductInfoResponse_QNAME, ShowProductInfoResponse.class, null, value);
    }

}
