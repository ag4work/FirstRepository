package utils.Mappers;

import entity.Contract;
import service.DTO.ContractDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 02.07.2015.
 */

public class ContractMapper {

    public static Contract DTOToEntity(ContractDTO contractDTO){
        if (contractDTO==null) return null;

        Contract contract = new Contract();

        contract.setContractId(contractDTO.getContractId());
        contract.setPhoneNumber(contractDTO.getPhoneNumber());
        contract.setBlocked(contractDTO.getBlocked());
        contract.setBlockedByStaff(contractDTO.getBlockedByStaff());
        contract.setBalance(contractDTO.getBalance());

        contract.setTariff(TariffMapper.DTOToEntity(
                contractDTO.getTariffDTO()));

        contract.setUser(UserMapper.DTOToEntity(contractDTO.getUserDTO()));
//        private List<OptionDTO> chosenOption;
        return contract;
    }

    public static ContractDTO EntityToDTO(Contract contract){
        if (contract==null) return null;

        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setContractId(contract.getContractId());
        contractDTO.setPhoneNumber(contract.getPhoneNumber());
        contractDTO.setBlocked(contract.getBlocked());
        contractDTO.setBlockedByStaff(contract.getBlockedByStaff());
        contractDTO.setBalance(contract.getBalance());

        contractDTO.setUserDTO(UserMapper.EntityToDTO(contract.getUser()));
        contractDTO.setTariffDTO(
                TariffMapper.EntityToDTO(contract.getTariff()));
//        private List<OptionDTO> chosenOption;
         return contractDTO;
    }
    public static Set<ContractDTO> EntitySetToDTOSet(Set<Contract> contracts){
        if (contracts==null) return null;

        Set<ContractDTO> contractDTOs = new HashSet<ContractDTO>();
        for (Contract contract : contracts){
            contractDTOs.add(ContractMapper.EntityToDTO(contract));
        }
        return contractDTOs;

    }
}
