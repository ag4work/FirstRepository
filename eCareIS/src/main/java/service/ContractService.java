package service;

import entity.Contract;
import service.DTO.ContractDTO;

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
}
