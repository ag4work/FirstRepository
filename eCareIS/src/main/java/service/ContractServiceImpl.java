package service;


import DAO.*;
import entity.Contract;
import entity.User;
import service.DTO.ContractDTO;
import utils.Constants;
import utils.Mappers.ContractMapper;
import utils.EntityManagerFactorySingleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Alexey on 24.06.2015.
 */
public class ContractServiceImpl implements ContractService{
    ContractDAO contractDAO = new ContractDAOImpl(EntityManagerFactorySingleton.getInstance());
    TariffDAO tariffDAO = new TariffDAOImpl(EntityManagerFactorySingleton.getInstance());
    UserDAO userDAO = new UserDAOImpl(EntityManagerFactorySingleton.getInstance());

    @Override
    public ContractDTO getContract(Integer id) {
        return ContractMapper.EntityToDTOWithSet(contractDAO.get(id));
    }

    @Override
    public Set<ContractDTO> getAllContracts() {
        return ContractMapper.EntitySetToDTOSet(
                new HashSet<Contract>(contractDAO.getAll()));
    }

    @Override
    public void add(ContractDTO contractDto) {
        //contractDto

       Contract contract = ContractMapper.DTOToEntity(contractDto);
       contract.setBlockedByStaff(false);
       contract.setBlocked(false);
       contract.setTariff(tariffDAO.get(Constants.DEFAULT_TARIFF_ID));
       contractDAO.add(contract);

    }
    public Set<ContractDTO> getContractsByUserId(Integer userId){
        if (userId==null) return Collections.emptySet();
        User user = userDAO.get(userId);
        Set<Contract> contracts = user.getContractOfUser();
        return ContractMapper.EntitySetToDTOSet(contracts);
    }

    private static Long getFreeNumber(){
        //TODO: make a check that the number is unique
        return Constants.DEFAULT_PHONE_CODE*10000000L+(long)(Math.random()*10000000);
    }

    public Set<Long> getFreeNumberSet(int setSize){
        Set<Long> freeNumberSet = new HashSet<Long>();
        for (int i=0; i<setSize;i++){
            freeNumberSet.add(getFreeNumber());
        }
        return freeNumberSet;
    }

}
