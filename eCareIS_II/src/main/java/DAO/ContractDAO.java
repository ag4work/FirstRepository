package DAO;

import entity.Contract;

import javax.persistence.Query;

/**
 * Created by Alexey on 01.07.2015.
 */
public interface ContractDAO extends DAOOperations<Contract> {

    public Contract getContractByPhonenumber(Long number);

}
