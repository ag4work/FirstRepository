package service.DTO;

import java.util.Set;

/**
 * Created by Alexey on 02.07.2015.
 */
public class OptionDTO {

    private Integer optionId;
    private String title;
    private Integer monthlyCost;
    private Integer activationCharge;
    private Set<OptionDTO> dependentOption;
    private Set<OptionDTO> requiredOption;
    private Set<OptionDTO> inconsistentOption;
    private Set<TariffDTO> tariffHasThisOption;
    private Set<ContractDTO> contracts;

    public OptionDTO() {
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Integer monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public Integer getActivationCharge() {
        return activationCharge;
    }

    public void setActivationCharge(Integer activationCharge) {
        this.activationCharge = activationCharge;
    }

    public Set<OptionDTO> getDependentOption() {
        return dependentOption;
    }

    public void setDependentOption(Set<OptionDTO> dependentOption) {
        this.dependentOption = dependentOption;
    }

    public Set<OptionDTO> getRequiredOption() {
        return requiredOption;
    }

    public void setRequiredOption(Set<OptionDTO> requiredOption) {
        this.requiredOption = requiredOption;
    }

    public Set<OptionDTO> getInconsistentOption() {
        return inconsistentOption;
    }

    public void setInconsistentOption(Set<OptionDTO> inconsistentOption) {
        this.inconsistentOption = inconsistentOption;
    }

    public Set<TariffDTO> getTariffHasThisOption() {
        return tariffHasThisOption;
    }

    public void setTariffHasThisOption(Set<TariffDTO> tariffHasThisOption) {
        this.tariffHasThisOption = tariffHasThisOption;
    }

    public Set<ContractDTO> getContracts() {
        return contracts;
    }

    public void setContracts(Set<ContractDTO> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "OptionDTO{" +
                "optionId=" + optionId +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionDTO optionDTO = (OptionDTO) o;

        if (!title.equals(optionDTO.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
