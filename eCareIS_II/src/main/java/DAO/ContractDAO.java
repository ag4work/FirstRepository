package dao;

import entity.Contract;

/**
 * Created by Alexey on 01.07.2015.
 */
public interface ContractDAO extends DAOOperations<Contract> {

    public Contract getContractByPhonenumber(Long number);

}
