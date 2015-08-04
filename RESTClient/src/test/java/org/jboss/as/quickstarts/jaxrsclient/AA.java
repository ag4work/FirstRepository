package org.jboss.as.quickstarts.jaxrsclient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Alexey on 01.08.2015.
 */
@XmlRootElement(name = "AA")
public class AA {

    private String title;
    private Integer price;

    public AA() {
    }

    public AA(String title, Integer price) {
        this.title = title;
        this.price = price;
    }
    @XmlElement
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    @XmlElement
    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AA{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}

