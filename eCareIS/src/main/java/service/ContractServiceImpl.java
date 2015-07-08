package service;


import DAO.*;
import entity.Contract;
import entity.Option;
import entity.Tariff;
import entity.User;
import service.DTO.ContractDTO;
import service.DTO.OptionDTO;
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
    OptionDAO optionDAO = new OptionDAOImpl(EntityManagerFactorySingleton.getInstance());
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

    @Override
    public void blockByStaff(Integer contractId) {
        Contract contract = contractDAO.get(contractId);
        contract.setBlocked(true);
        contract.setBlockedByStaff(true);
        contractDAO.update(contract);
    }

    @Override
    public void unblockByStaff(Integer contractId) {
        Contract contract = contractDAO.get(contractId);
        contract.setBlocked(false);
        contract.setBlockedByStaff(false);
        contractDAO.update(contract);
    }

    @Override
    public ContractDTO getContractByPhonenumber(Long phonenumber) {
        Set<Contract> contracts = new HashSet<Contract>(contractDAO.getAll());
        for (Contract contract : contracts){
            if (contract.getPhoneNumber().equals(phonenumber))
                return ContractMapper.EntityToDTOWithSet(contract);
        }
        return null;
    }

    @Override
    public Set<ContractDTO> getContractsByTariff(Integer tariffId) {
        Set<Contract> contracts = tariffDAO.get(tariffId).
                getContractHasThisTariff();
        if (contracts==null){
            return Collections.EMPTY_SET;
        }
        return ContractMapper.EntitySetToDTOSet(contracts);
    }

    @Override
    public void applyCart(Cart cart, Integer contractId) {
        if (cart==null || contractId==null) return;
        Contract contract = contractDAO.get(contractId);
        Tariff newTariff = tariffDAO.get(cart.getTariffDTO().getTariffId());
        contract.setTariff(newTariff);
        contract.setBalance(contract.getBalance() - cart.getTotalPayment());
        Set<Option> newContractOptions = new HashSet<Option>();
        for (OptionDTO optionDTO : cart.getOptionDTOset())
            newContractOptions.add(optionDAO.get(optionDTO.getOptionId()));
        contract.setChosenOption(newContractOptions);
        contractDAO.update(contract);
    }

}
