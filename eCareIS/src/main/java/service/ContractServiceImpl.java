package service;


import GenericBasedDAO.ContractDAO;
import GenericBasedDAO.ContractDAOImpl;
import entity.Contract;
import utils.EntityManagerFactorySingleton;

import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
public class ContractServiceImpl implements ContractService{
    ContractDAO contractDAO = new ContractDAOImpl(EntityManagerFactorySingleton.getInstance());

    @Override
    public Contract getContract(Integer id) {
        return contractDAO.get(id);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractDAO.getAll();
    }
}
