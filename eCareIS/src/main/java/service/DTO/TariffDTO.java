package service.DTO;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;


/**
 * Created by Alexey on 02.07.2015.
 */
public class TariffDTO {

    private Integer tariffId;
    @NotEmpty(message = "Название тарифа должно быть задано")
    @Length(min = 1, max = 50, message = "Длина названия тарифа должна составлять от 1 до 45 символов")
    private String title;
    private Integer price;
    private Set<ContractDTO> contractHasThisTariff;
    private Set<OptionDTO> possibleOption;

    public TariffDTO() {
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<ContractDTO> getContractHasThisTariff() {
        return contractHasThisTariff;
    }

    public void setContractHasThisTariff(Set<ContractDTO> contractHasThisTariff) {
        this.contractHasThisTariff = contractHasThisTariff;
    }

    public Set<OptionDTO> getPossibleOption() {
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
}
