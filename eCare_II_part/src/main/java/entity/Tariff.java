package entity;



import javax.persistence.*;
import java.util.Set;

/**
 * Created by Alexey on 01.07.2015.
 */
@Entity
public class Tariff {
    private Integer tariffId;
    private String title;
    private Integer price;
    private Set<Contract> contractHasThisTariff;
    private Set<Option> possibleOption;

    @Id
    @GeneratedValue
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tariff")
    public Set<Contract> getContractHasThisTariff() {
        return contractHasThisTariff;
    }

    public void setContractHasThisTariff(Set<Contract> contractHasThisTariff) {
        this.contractHasThisTariff = contractHasThisTariff;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tariff_possible_option", catalog = "ecareis", schema = "", joinColumns = @JoinColumn(name = "tariff_id", referencedColumnName = "tariff_id"), inverseJoinColumns = @JoinColumn(name = "possible_option_id", referencedColumnName = "option_id"))
    public Set<Option> getPossibleOption() {
        return possibleOption;
    }

    public void setPossibleOption(Set<Option> possibleOption) {
        this.possibleOption = possibleOption;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "title='" + title + '\'' +
                ", tariffId=" + tariffId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        if (!title.equals(tariff.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
