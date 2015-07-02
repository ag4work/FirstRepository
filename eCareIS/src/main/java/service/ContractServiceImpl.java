package service;


import GenericBasedDAO.ContractDAO;
import GenericBasedDAO.ContractDAOImpl;
import GenericBasedDAO.TariffDAO;
import GenericBasedDAO.TariffDAOImpl;
import entity.Contract;
import service.DTO.ContractDTO;
import utils.Constants;
import utils.ContractMapper;
import utils.EntityManagerFactorySingleton;
import java.util.List;




/**
 * Created by Alexey on 24.06.2015.
 */
public class ContractServiceImpl implements ContractService{
    ContractDAO contractDAO = new ContractDAOImpl(EntityManagerFactorySingleton.getInstance());
    TariffDAO tariffDAO = new TariffDAOImpl(EntityManagerFactorySingleton.getInstance());
    @Override
    public Contract getContract(Integer id) {
        return contractDAO.get(id);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractDAO.getAll();
    }

    @Override
    public void add(ContractDTO contractDto) {
        //contractDto

       Contract contract = ContractMapper.DTOToEntity(contractDto);
       contract.setBlockedByStaff(false);
       contract.setBlocked(false);
       contract.setTariffByTariffId(tariffDAO.get(Constants.DEFAULT_TARIFF_ID));
       contractDAO.add(contract);

    }
}
