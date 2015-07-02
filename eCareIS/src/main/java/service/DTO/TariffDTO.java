package service.DTO;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by Alexey on 02.07.2015.
 */
public class TariffDTO {

    private Integer tariffId;
    @NotEmpty(message = "Название тарифа должно быть задано")
    @Length(min = 1, max = 50, message = "Длина названия тарифа должна составлять от 1 до 45 символов")
    private String title;
    private Integer price;
    private List<ContractDTO> contractHasThisTariff;
    private List<OptionDTO> possibleOption;

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

    public List<ContractDTO> getContractHasThisTariff() {
        return contractHasThisTariff;
    }

    public void setContractHasThisTariff(List<ContractDTO> contractHasThisTariff) {
        this.contractHasThisTariff = contractHasThisTariff;
    }

    public List<OptionDTO> getPossibleOption() {
        return possibleOption;
    }

    public void setPossibleOption(List<OptionDTO> possibleOption) {
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
