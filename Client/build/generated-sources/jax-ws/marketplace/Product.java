
package marketplace;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebService(name = "product", targetNamespace = "http://Marketplace/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Product {


    /**
     * 
     * @param idUser
     * @param token
     * @return
     *     returns marketplace.Map
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "yourProduct", targetNamespace = "http://Marketplace/", className = "marketplace.YourProduct")
    @ResponseWrapper(localName = "yourProductResponse", targetNamespace = "http://Marketplace/", className = "marketplace.YourProductResponse")
    @Action(input = "http://Marketplace/product/yourProductRequest", output = "http://Marketplace/product/yourProductResponse")
    public Map yourProduct(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "id_user", targetNamespace = "")
        String idUser);

}
