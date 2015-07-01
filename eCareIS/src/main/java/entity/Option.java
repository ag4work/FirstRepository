package entity;



import javax.persistence.*;
import java.util.List;

/**
 * Created by Alexey on 01.07.2015.
 */
@Entity
@Table(name = "options", schema = "", catalog = "ecareis")
public class Option {
    private Integer optionId;
    private String title;
    private Integer monthlyCost;
    private Integer activationCharge;
    private List<Option> dependentOption;
    private List<Option> inconsistentOption;
    private List<Tariff> tariffHasThisOption;
    private List<Contract> contractByOptionId;

    @Id
    @Column(name = "option_id")
    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
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
    @Column(name = "monthly_cost")
    public Integer getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Integer monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    @Basic
    @Column(name = "activation_charge")
    public Integer getActivationCharge() {
        return activationCharge;
    }

    public void setActivationCharge(Integer activationCharge) {
        this.activationCharge = activationCharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (activationCharge != null ? !activationCharge.equals(option.activationCharge) : option.activationCharge != null)
            return false;
        if (monthlyCost != null ? !monthlyCost.equals(option.monthlyCost) : option.monthlyCost != null) return false;
        if (optionId != null ? !optionId.equals(option.optionId) : option.optionId != null) return false;
        if (title != null ? !title.equals(option.title) : option.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = optionId != null ? optionId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (monthlyCost != null ? monthlyCost.hashCode() : 0);
        result = 31 * result + (activationCharge != null ? activationCharge.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(name = "dependent_option", catalog = "ecareis", schema = "", joinColumns = @JoinColumn(name = "src_option_id", referencedColumnName = "option_id"), inverseJoinColumns = @JoinColumn(name = "dependent_option_id", referencedColumnName = "option_id"))
    public List<Option> getDependentOption() {
        return dependentOption;
    }

    public void setDependentOption(List<Option> dependentOption) {
        this.dependentOption = dependentOption;
    }

    @ManyToMany
    @JoinTable(name = "inconsistent_option", catalog = "ecareis", schema = "", joinColumns = @JoinColumn(name = "src_option_id", referencedColumnName = "option_id"), inverseJoinColumns = @JoinColumn(name = "inconsistent_option_id", referencedColumnName = "option_id"))
    public List<Option> getInconsistentOption() {
        return inconsistentOption;
    }

    public void setInconsistentOption(List<Option> inconsistentOption) {
        this.inconsistentOption = inconsistentOption;
    }

    @ManyToMany(mappedBy = "possibleOption")
    public List<Tariff> getTariffHasThisOption() {
        return tariffHasThisOption;
    }

    public void setTariffHasThisOption(List<Tariff> tariffHasThisOption) {
        this.tariffHasThisOption = tariffHasThisOption;
    }

    @ManyToMany(mappedBy = "chosenOption")
    public List<Contract> getContractByOptionId() {
        return contractByOptionId;
    }

    public void setContractByOptionId(List<Contract> contractByOptionId) {
        this.contractByOptionId = contractByOptionId;
    }

    @Override
    public String toString() {
        return "Option{" +
                "title='" + title + '\'' +
                ", optionId=" + optionId +
                '}';
    }
}
