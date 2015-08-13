package dao;

import entity.Contract;

import java.util.List;

/**
 * Created by Alexey on 01.07.2015.
 */
public interface ContractDAO extends DAOOperations<Contract> {

    public Contract getContractByPhonenumber(Long number);
    public List<Contract> getContracts(Integer page, Integer contractsPerPage);
    public Long getContractCount();

}
