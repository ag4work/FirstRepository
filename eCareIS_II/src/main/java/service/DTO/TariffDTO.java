package service.DTO;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;


/**
 * Created by Alexey on 02.07.2015.
 */
@XmlRootElement(name = "tariff")
public class TariffDTO implements Serializable {
    private static final long serialVersionUID = 1905122041951781207L;
    private Integer tariffId;
    @NotEmpty(message = "Название тарифа должно быть задано")
    @Length(min = 1, max = 50, message = "Длина названия тарифа должна составлять от 1 до 45 символов")
    private String title;
    private Integer price;
//    private Set<ContractDTO> contractHasThisTariff;
    private Set<OptionDTO> possibleOption;

    public TariffDTO() {
    }

    @XmlAttribute
    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }
    @XmlAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @XmlAttribute
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

//    public Set<ContractDTO> getContractHasThisTariff() {
//        return contractHasThisTariff;
//    }

//    public void setContractHasThisTariff(Set<ContractDTO> contractHasThisTariff) {
//        this.contractHasThisTariff = contractHasThisTariff;
//    }

    public Set<OptionDTO> getPossibleOption() {
        if (possibleOption==null) return Collections.EMPTY_SET;
        return possibleOption;
    }

    public void setPossibleOption(Set<OptionDTO> possibleOption) {
        this.possibleOption = possibleOption;
    }

    @Override
    public String toString() {
        return "TariffDTO{" +
                "title='" + title + '\'' +
                ", tariffId=" + tariffId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TariffDTO tariffDTO = (TariffDTO) o;

        if (!title.equals(tariffDTO.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
