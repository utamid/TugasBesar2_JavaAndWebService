
package marketplace;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for map complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="map"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listitem" type="{http://Marketplace/}item" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listpurchase" type="{http://Marketplace/}itempurchase" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listuser" type="{http://Marketplace/}user" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="obj" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "map", propOrder = {
    "listitem",
    "listpurchase",
    "listuser",
    "obj",
    "status",
    "token"
})
public class Map {

    @XmlElement(nillable = true)
    protected List<Item> listitem;
    @XmlElement(nillable = true)
    protected List<Itempurchase> listpurchase;
    @XmlElement(nillable = true)
    protected List<User> listuser;
    protected int obj;
    protected String status;
    protected String token;

    /**
     * Gets the value of the listitem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listitem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListitem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * 
     * 
     */
    public List<Item> getListitem() {
        if (listitem == null) {
            listitem = new ArrayList<Item>();
        }
        return this.listitem;
    }

    /**
     * Gets the value of the listpurchase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listpurchase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListpurchase().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Itempurchase }
     * 
     * 
     */
    public List<Itempurchase> getListpurchase() {
        if (listpurchase == null) {
            listpurchase = new ArrayList<Itempurchase>();
        }
        return this.listpurchase;
    }

    /**
     * Gets the value of the listuser property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listuser property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListuser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getListuser() {
        if (listuser == null) {
            listuser = new ArrayList<User>();
        }
        return this.listuser;
    }

    /**
     * Gets the value of the obj property.
     * 
     */
    public int getObj() {
        return obj;
    }

    /**
     * Sets the value of the obj property.
     * 
     */
    public void setObj(int value) {
        this.obj = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

}
