
package marketplace;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "sales", targetNamespace = "http://Marketplace/", wsdlLocation = "http://localhost:30228/Marketplace/sales?wsdl")
public class Sales_Service
    extends Service
{

    private final static URL SALES_WSDL_LOCATION;
    private final static WebServiceException SALES_EXCEPTION;
    private final static QName SALES_QNAME = new QName("http://Marketplace/", "sales");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:30228/Marketplace/sales?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SALES_WSDL_LOCATION = url;
        SALES_EXCEPTION = e;
    }

    public Sales_Service() {
        super(__getWsdlLocation(), SALES_QNAME);
    }

    public Sales_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SALES_QNAME, features);
    }

    public Sales_Service(URL wsdlLocation) {
        super(wsdlLocation, SALES_QNAME);
    }

    public Sales_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SALES_QNAME, features);
    }

    public Sales_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Sales_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Sales
     */
    @WebEndpoint(name = "salesPort")
    public Sales getSalesPort() {
        return super.getPort(new QName("http://Marketplace/", "salesPort"), Sales.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Sales
     */
    @WebEndpoint(name = "salesPort")
    public Sales getSalesPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Marketplace/", "salesPort"), Sales.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SALES_EXCEPTION!= null) {
            throw SALES_EXCEPTION;
        }
        return SALES_WSDL_LOCATION;
    }

}
