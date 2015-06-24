package service;

import DAO.ContractDAO;
import DAO.ContractDAOImpl;
import DAO.EntityManagerFactorySingleton;
import entity.Contract;

import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
public class ContractServiceImpl implements ContractService{
    ContractDAO contractDAO = new ContractDAOImpl(EntityManagerFactorySingleton.getInstance());

    @Override
    public Contract getContract(Integer id) {
        return contractDAO.getById(id);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractDAO.getAll();
    }
}
