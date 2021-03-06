
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
@WebService(name = "addProduct", targetNamespace = "http://Marketplace/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AddProduct {


    /**
     * 
     * @param sellerId
     * @param price
     * @param description
     * @param photo
     * @param prodname
     * @param token
     * @return
     *     returns marketplace.Map
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertnewproduct", targetNamespace = "http://Marketplace/", className = "marketplace.Insertnewproduct")
    @ResponseWrapper(localName = "insertnewproductResponse", targetNamespace = "http://Marketplace/", className = "marketplace.InsertnewproductResponse")
    @Action(input = "http://Marketplace/addProduct/insertnewproductRequest", output = "http://Marketplace/addProduct/insertnewproductResponse")
    public Map insertnewproduct(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "prodname", targetNamespace = "")
        String prodname,
        @WebParam(name = "price", targetNamespace = "")
        int price,
        @WebParam(name = "description", targetNamespace = "")
        String description,
        @WebParam(name = "photo", targetNamespace = "")
        String photo,
        @WebParam(name = "seller_id", targetNamespace = "")
        int sellerId);

}
