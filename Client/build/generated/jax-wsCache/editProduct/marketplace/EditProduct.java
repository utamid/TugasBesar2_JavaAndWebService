
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
@WebService(name = "editProduct", targetNamespace = "http://Marketplace/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EditProduct {


    /**
     * 
     * @param idProduct
     * @param token
     * @return
     *     returns marketplace.Map
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "showproduct", targetNamespace = "http://Marketplace/", className = "marketplace.Showproduct")
    @ResponseWrapper(localName = "showproductResponse", targetNamespace = "http://Marketplace/", className = "marketplace.ShowproductResponse")
    @Action(input = "http://Marketplace/editProduct/showproductRequest", output = "http://Marketplace/editProduct/showproductResponse")
    public Map showproduct(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "id_product", targetNamespace = "")
        String idProduct);

    /**
     * 
     * @param price
     * @param description
     * @param prodname
     * @param prodId
     * @param token
     * @return
     *     returns marketplace.Map
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "edittodb", targetNamespace = "http://Marketplace/", className = "marketplace.Edittodb")
    @ResponseWrapper(localName = "edittodbResponse", targetNamespace = "http://Marketplace/", className = "marketplace.EdittodbResponse")
    @Action(input = "http://Marketplace/editProduct/edittodbRequest", output = "http://Marketplace/editProduct/edittodbResponse")
    public Map edittodb(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "prod_id", targetNamespace = "")
        String prodId,
        @WebParam(name = "prodname", targetNamespace = "")
        String prodname,
        @WebParam(name = "description", targetNamespace = "")
        String description,
        @WebParam(name = "price", targetNamespace = "")
        int price);

}
