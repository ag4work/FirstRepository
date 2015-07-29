package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DTO.OptionDTO;

@Service
public class CartService {
    @Autowired
    OptionService optionService;

    public void addOptionWithAllRequired(OptionDTO optionDTO, Cart cart){
        cart.getOptionDTOset().addAll(optionService.getRequiredOptionTree(
                optionDTO.getOptionId()));
    }

    public boolean isOptionConsistentWithOptionsInCart(OptionDTO optionDTO, Cart cart){
        return optionService.isOptionIncludingAllRequiredConsistentWithSet(
                optionDTO.getOptionId(), cart.getOptionDTOset());
    }
}
