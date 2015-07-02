package service;

import entity.Contract;
import service.DTO.ContractDTO;

import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
public interface ContractService {
    public Contract getContract(Integer id);
    public List<Contract> getAllContracts();
    public void add(ContractDTO contractDto);


}
