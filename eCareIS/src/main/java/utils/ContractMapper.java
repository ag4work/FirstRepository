package utils;

import entity.Contract;
import entity.User;
import service.DTO.ContractDTO;

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
        contract.setUserByClientId(UserMapper.DTOToEntity(contractDTO.getUserByClientId()));

   //     contract.setTariffByTariffId(new TariffDTO());
        //
//        private TariffDTO tariffByTariffId;
//        private UserDTO userByClientId;
//        private List<OptionDTO> chosenOption;


        return contract;
    }

    public static ContractDTO EntityToDTO(Contract contract){
        if (contract==null) return null;

        ContractDTO contractDTO = new ContractDTO();


        return contractDTO;
    }
}
