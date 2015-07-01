package entity;



import entity.Option;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alexey on 01.07.2015.
 */
@Entity
public class Tariff {
    private Integer tariffId;
    private String title;
    private Integer price;
    private List<Contract> contractHasThisTariff;
    private List<Option> possibleOption;

    @Id
    @Column(name = "tariff_id")
    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        if (price != null ? !price.equals(tariff.price) : tariff.price != null) return false;
        if (tariffId != null ? !tariffId.equals(tariff.tariffId) : tariff.tariffId != null) return false;
        if (title != null ? !title.equals(tariff.title) : tariff.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tariffId != null ? tariffId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tariffByTariffId")
    public List<Contract> getContractHasThisTariff() {
        return contractHasThisTariff;
    }

    public void setContractHasThisTariff(List<Contract> contractHasThisTariff) {
        this.contractHasThisTariff = contractHasThisTariff;
    }

    @ManyToMany
    @JoinTable(name = "tariff_possible_option", catalog = "ecareis", schema = "", joinColumns = @JoinColumn(name = "tariff_id", referencedColumnName = "tariff_id"), inverseJoinColumns = @JoinColumn(name = "possible_option_id", referencedColumnName = "option_id"))
    public List<Option> getPossibleOption() {
        return possibleOption;
    }

    public void setPossibleOption(List<Option> possibleOption) {
        this.possibleOption = possibleOption;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "title='" + title + '\'' +
                ", tariffId=" + tariffId +
                '}';
    }
}
