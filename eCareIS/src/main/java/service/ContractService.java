package service;

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


}
