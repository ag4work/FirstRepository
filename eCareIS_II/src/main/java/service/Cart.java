package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 08.07.2015.
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = 1946122041951251207L;
    private TariffDTO tariffDTO;
    private Set<OptionDTO> optionDTOset = new HashSet<OptionDTO>();
    private Integer contractId;

//    @Autowired
//    CartService cartService;
//    CartService cartService = new CartService();

//    public Integer getTotalPayment(){
//        return cartService.getTotalPaymentForCart(this);
//    }

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
