package service;

import service.DTO.OptionDTO;
import service.DTO.TariffDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 08.07.2015.
 */
public class Cart {

    private TariffDTO tariffDTO;
    private Set<OptionDTO> optionDTOset = new HashSet<OptionDTO>();
    private Integer contractId;

    public Integer getTotalPayment(){
        Integer sumPayment = 0;
        for(OptionDTO optionDTO : optionDTOset)
            sumPayment=sumPayment+optionDTO.getActivationCharge();
        return sumPayment;
    }

    public TariffDTO getTariffDTO() {
        return tariffDTO;
    }

    public void setTariffDTO(TariffDTO tariffDTO) {
        this.tariffDTO = tariffDTO;
    }

    public Set<OptionDTO> getOptionDTOset() {
        return optionDTOset;
    }

    public void setOptionDTOset(Set<OptionDTO> optionDTOset) {
        this.optionDTOset = optionDTOset;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }


}
