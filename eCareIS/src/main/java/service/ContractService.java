package service;

import entity.Contract;
import service.DTO.ContractDTO;
import service.DTO.OptionDTO;

import java.util.List;
import java.util.Set;

/**
 * Created by Alexey on 24.06.2015.
 */
public interface ContractService {
    public ContractDTO getContract(Integer id);
    public Set<ContractDTO> getAllContracts();
    public void add(ContractDTO contractDto);
    public Set<ContractDTO> getContractsByUserId(Integer userId);
    public Set<Long> getFreeNumberSet(int setSize);
    public void blockByStaff(Integer contractId);
    public void unblockByStaff(Integer contractId);
    public ContractDTO getContractByPhonenumber(Long phonenumber);
    public Set<ContractDTO> getContractsByTariff(Integer tariffId);
    public void applyCart(Cart cart, Integer contractId);
    public void removeOptionWithAllDependent(Integer optionId, Integer contractId);
    public Set<OptionDTO> getContractOptionsWithSets(Integer contractId);
}
