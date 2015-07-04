package service;

import entity.Contract;
import service.DTO.ContractDTO;

import java.util.List;
import java.util.Set;

/**
 * Created by Alexey on 24.06.2015.
 */
public interface ContractService {
    public Contract getContract(Integer id);
    public Set<Contract> getAllContracts();
    public void add(ContractDTO contractDto);
    public Set<ContractDTO> getContractsByUserId(Integer userId);


}
