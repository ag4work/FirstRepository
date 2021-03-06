package entity;



import javax.persistence.*;
import java.util.Set;

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
    private Set<Option> dependentOption;
    private Set<Option> requiredOption;
    private Set<Option> inconsistentOption;
    private Set<Tariff> tariffHasThisOption;
//    private Set<Contract> contracts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dependent_option", catalog = "ecareis", schema = "",
            joinColumns = @JoinColumn(name = "src_option_id", referencedColumnName = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "dependent_option_id", referencedColumnName = "option_id"))
    public Set<Option> getDependentOption() {
        return dependentOption;
    }

    public void setDependentOption(Set<Option> dependentOption) {
        this.dependentOption = dependentOption;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dependent_option", catalog = "ecareis", schema = "",
            joinColumns = @JoinColumn(name = "dependent_option_id", referencedColumnName = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "src_option_id", referencedColumnName = "option_id"))
    public Set<Option> getRequiredOption() {
        return requiredOption;
    }

    public void setRequiredOption(Set<Option> requiredOption) {
        this.requiredOption = requiredOption;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "inconsistent_option", catalog = "ecareis", schema = "", joinColumns = @JoinColumn(name = "src_option_id", referencedColumnName = "option_id"), inverseJoinColumns = @JoinColumn(name = "inconsistent_option_id", referencedColumnName = "option_id"))
    public Set<Option> getInconsistentOption() {
        return inconsistentOption;
    }

    public void setInconsistentOption(Set<Option> inconsistentOption) {
        this.inconsistentOption = inconsistentOption;
    }

    @ManyToMany(mappedBy = "possibleOption")
    public Set<Tariff> getTariffHasThisOption() {
        return tariffHasThisOption;
    }

    public void setTariffHasThisOption(Set<Tariff> tariffHasThisOption) {
        this.tariffHasThisOption = tariffHasThisOption;
    }

//    @ManyToMany(mappedBy = "chosenOption")
//    public Set<Contract> getContracts() {
//        return contracts;
//    }

//    public void setContracts(Set<Contract> contracts) {
//        this.contracts = contracts;
//    }

    @Override
    public String toString() {
        return "Option{" +
                "title='" + title + '\'' +
                ", optionId=" + optionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (!title.equals(option.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
