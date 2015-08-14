package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DTO.ContractDTO;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;

import java.util.Set;

@Service
@Transactional
public class CartService {

    @Autowired
    OptionService optionService;

    @Autowired
    ContractService contractService;

    public Integer getTotalPaymentForCart(Cart cart) {
        if (cart == null) return 0;
        ContractDTO contractDTO = contractService.getContract(cart.getContractId());
        Set<OptionDTO> contractOptions = contractDTO.getChosenOption();
        Set<OptionDTO> cartOptions = cart.getOptionDTOset();
        Integer sumPayment = 0;
        for (OptionDTO optionDTO : cartOptions) {
            if (!contractOptions.contains(optionDTO)) {
                sumPayment = sumPayment + optionDTO.getActivationCharge();
            }
        }
        return sumPayment;
    }

    public void addOptionWithAllRequired(OptionDTO optionDTO, Cart cart){
        cart.getOptionDTOset().addAll(optionService.getRequiredOptionTree(
                optionDTO.getOptionId()));
    }

    public boolean isOptionConsistentWithOptionsInCart(OptionDTO optionDTO, Cart cart){
        return optionService.isOptionIncludingAllRequiredConsistentWithSet(
                optionDTO.getOptionId(), cart.getOptionDTOset());
    }

    public void fillWithNewOrder(OptionDTO optionDTO, TariffDTO tariffDTO, Integer contractId, Cart cart) {
            addOptionWithAllRequired(optionDTO, cart);
            cart.setTariffDTO(tariffDTO);
            cart.setContractId(contractId);
    }
}
