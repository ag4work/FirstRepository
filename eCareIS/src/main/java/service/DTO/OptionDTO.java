package service.DTO;

import java.util.List;

/**
 * Created by Alexey on 02.07.2015.
 */
public class OptionDTO {

    private Integer optionId;
    private String title;
    private Integer monthlyCost;
    private Integer activationCharge;
    private List<OptionDTO> dependentOption;
    private List<OptionDTO> inconsistentOption;
    private List<TariffDTO> tariffHasThisOption;
    private List<ContractDTO> contractByOptionId;

    public OptionDTO(Integer optionId) {
        this.optionId = optionId;
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

    public List<OptionDTO> getDependentOption() {
        return dependentOption;
    }

    public void setDependentOption(List<OptionDTO> dependentOption) {
        this.dependentOption = dependentOption;
    }

    public List<OptionDTO> getInconsistentOption() {
        return inconsistentOption;
    }

    public void setInconsistentOption(List<OptionDTO> inconsistentOption) {
        this.inconsistentOption = inconsistentOption;
    }

    public List<TariffDTO> getTariffHasThisOption() {
        return tariffHasThisOption;
    }

    public void setTariffHasThisOption(List<TariffDTO> tariffHasThisOption) {
        this.tariffHasThisOption = tariffHasThisOption;
    }

    public List<ContractDTO> getContractByOptionId() {
        return contractByOptionId;
    }

    public void setContractByOptionId(List<ContractDTO> contractByOptionId) {
        this.contractByOptionId = contractByOptionId;
    }

    @Override
    public String toString() {
        return "OptionDTO{" +
                "optionId=" + optionId +
                ", title='" + title + '\'' +
                '}';
    }
}
