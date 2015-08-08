package controllers_mvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ContractService;
import service.DTO.ContractDTO;
import service.DTO.TariffDTO;
import service.TariffService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@RestController
public class Rest{

    @Autowired
    TariffService tariffService;

    @Autowired
    ContractService contractService;


    @RequestMapping("/rest/tariffs")
    public Set<TariffDTO> getAllTariffs(){
        //todo think of field "contractHasThisTariff" in each tariff
        Set<TariffDTO> tariffs = tariffService.getAllTariffs();
        for (TariffDTO tariff : tariffs){
            tariff.setPossibleOption(Collections.EMPTY_SET);
            tariff.setContractHasThisTariff(Collections.EMPTY_SET);
        }
        return tariffs;
    }


    @RequestMapping("/rest/contractsByTariff/tariff/{tariffId}")
    public Set<ContractDTO> getContractrByTariffId(@PathVariable Integer tariffId){
        return contractService.getContractsByTariff(tariffId);
    }

}

