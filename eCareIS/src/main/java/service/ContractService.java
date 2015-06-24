package service;

import entity.Contract;

import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
public interface ContractService {
    public Contract getContract(Integer id);
    public List<Contract> getAllContracts();
}
