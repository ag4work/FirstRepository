package service;


import dao.*;
import entity.Contract;
import entity.Option;
import entity.Tariff;
import entity.User;
import exceptions.BlockedByStaffException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DTO.ContractDTO;
import service.DTO.OptionDTO;
import utils.Constants;
import utils.Mappers.ContractMapper;
import utils.Mappers.OptionMapper;

import javax.persistence.EntityNotFoundException;
import java.util.*;


/**
 * The class implements a business layer routines for contracts.
 * It interacts with dao with IDs (or entity)
 * and return a DTOs to presentation.
 */
@Service
public class ContractServiceImpl implements ContractService{

    Logger logger = Logger.getLogger(ContractServiceImpl.class);

    @Autowired
    ContractDAO contractDAO;

    @Autowired
    TariffDAO tariffDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    OptionDAO optionDAO;

    @Autowired
    OptionService optionService;

    @Autowired
    CartService cartService;

    /**
     * This method return ContractDTO object by ContractId
     * @param id ContractId
     * @return ContractDTO
     */

    @Override
    public ContractDTO getContract(Integer id) {
        return ContractMapper.EntityToDTOWithSet(contractDAO.get(id));
    }

    /**
     * Returns the set of all Contracts
     * @return ContractDTO
     */
    @Override
    public Set<ContractDTO> getAllContracts() {
        return ContractMapper.EntitySetToDTOSet(
                new HashSet<Contract>(contractDAO.getAll()));
    }

    /**
     * Add a new contract. contractDto object mapped to dao entity "contract"
     * @param contractDto contractDto object
     */
    @Override
    @Transactional
    public void add(ContractDTO contractDto) {
       Contract contract = ContractMapper.DTOToEntity(contractDto);
       contract.setBlockedByStaff(false);
       contract.setBlocked(false);
       contract.setTariff(tariffDAO.get(Constants.DEFAULT_TARIFF_ID));
       contractDAO.add(contract);

    }

    /**
     * returns a set of contact which are connected to a specific user
     * @param userId userId
     * @return set of contractDTO
     */
    @Override
    public Set<ContractDTO> getContractsByUserId(Integer userId){
        if (userId==null) return Collections.emptySet();
        User user = userDAO.get(userId);
        Set<Contract> contracts = user.getContractOfUser();
        return ContractMapper.EntitySetToDTOSet(contracts);
    }

    /**
     * The method which generates a new number until it's not such existed number already.
     * (Actually now there is a bit chances of cycle will make even two iterations)
     * @return 10 signs number in Long format
     */
    private Long getFreeNumber(){
        boolean exist;
        Long number;
        do {
            exist = false;
            number = Constants.DEFAULT_PHONE_CODE * 10000000L + (long) (Math.random() * 10000000);
            if (getContractByPhonenumber(number)!=null){
                exist = true;
            }
        } while (exist);
        return number;
    }

    /**
     * Return a list of free numbers
     * @param setSize size of list
     * @return set of numbers. Each number is represented as long
     */
    @Override
    public Set<Long> getFreeNumberSet(int setSize){
        Set<Long> freeNumberSet = new HashSet<Long>();
        for (int i=0; i<setSize;i++){

            freeNumberSet.add(getFreeNumber());
        }
        return freeNumberSet;
    }

    /**
     * Method sets property of contract that is was blocked by employee
     * @param contractId contract Id
     */
    @Override
    @Transactional
    public void blockByStaff(Integer contractId) {
        Contract contract = contractDAO.get(contractId);
        contract.setBlocked(true);
        contract.setBlockedByStaff(true);
        contractDAO.update(contract);
    }

    /**
     * Method sets property of contract that is was unblocked by employee
     * @param contractId contract Id
     */
    @Override
    @Transactional
    public void unblockByStaff(Integer contractId) {
        Contract contract = contractDAO.get(contractId);
        contract.setBlocked(false);
        contract.setBlockedByStaff(false);
        contractDAO.update(contract);
    }

    /**
     * Method sets property of contract that is was blocked by client
     * @param contractId contract Id
     */
    @Override
    @Transactional
    public void blockByClient(Integer contractId){
        Contract contract = contractDAO.get(contractId);
        contract.setBlocked(true);
        contractDAO.update(contract);
    }

    /**
     * Method sets property of contract that is was unblocked by client
     * @param contractId contract Id
     */
    @Override
    @Transactional
    public void unblockByClient(Integer contractId) throws BlockedByStaffException {
        Contract contract = contractDAO.get(contractId);
        if (contract.getBlockedByStaff()){
            throw new BlockedByStaffException();
        }
        contract.setBlocked(false);
        contractDAO.update(contract);
    }

    /**
     * Finds a contract by phonenumber
     * @param phonenumber phonenumber in Long format
     * @return ContractDTO
     */
    @Override
    public ContractDTO getContractByPhonenumber(Long phonenumber) {
        Contract contract = contractDAO.getContractByPhonenumber(phonenumber);
        if (contract!=null) {
            return ContractMapper.EntityToDTOWithSet(contract);
        } else {
            return null;
        }
    }

    /**
     * Return set of contracts which are connected to the specific tariff
     * @param tariffId tariffId
     * @return set of ContractDTO
     */
    @Override
    public Set<ContractDTO> getContractsByTariff(Integer tariffId) {
        Set<Contract> contracts = tariffDAO.getContractsByTariff(tariffId);
        return ContractMapper.EntitySetToDTOSetWithSets(contracts);
    }

    /**
     * The method takes shopping cart. Takes tariff and options from it
     * and connect them to the specific contract.
     * Balance is also to be reduced
     * @param cart shopping cart object
     * @param contractId contractId
     */
    @Override
    @Transactional
    public void applyCart(Cart cart, Integer contractId) {
        logger.info("trying to apply cart to contract with id "+ contractId);
        if (cart==null || contractId==null) return;
        Contract contract = contractDAO.get(contractId);
        Tariff newTariff = tariffDAO.get(cart.getTariffDTO().getTariffId());
        if (newTariff==null) {
            logger.info("It looks like tariff have already deleted");
            throw new EntityNotFoundException();
        }
        contract.setTariff(newTariff);
        contract.setBalance(contract.getBalance() - cartService.getTotalPaymentForCart(cart));
        Set<Option> newContractOptions = new HashSet<Option>();
        for (OptionDTO optionDTO : cart.getOptionDTOset())
            newContractOptions.add(optionDAO.get(optionDTO.getOptionId()));
        contract.setChosenOption(newContractOptions);
        contractDAO.update(contract);
        logger.info("cart applied to contract with id "+ contractId);
    }

    /**
     * The method takes the options, finds a tree of all dependent option for it,
     * and removes all of them from the contract
     * @param optionId optionId
     * @param contractId contractId
     */
    @Override
    @Transactional
    public void removeOptionWithAllDependent(Integer optionId, Integer contractId) {
        Set<OptionDTO> allDepOptions = optionService.getDependentOptionTree(optionId);
        Set<Option> optionsToDelete = new HashSet<Option>();
        for (OptionDTO optionDTO : allDepOptions)
            optionsToDelete.add(optionDAO.get(optionDTO.getOptionId()));
        Contract contract = contractDAO.get(contractId);
        contract.getChosenOption().removeAll(optionsToDelete);
        contractDAO.update(contract);
    }

    /**
     * Returns a set of options for the specific contact.
     * getContractOptionsWithSets - this "withSets" means that each returning
     * DTO objects will contain all possible sets like DependentOptions,
     * RrequiredOptions, InconsistentOptions
     * @param contractId
     * @return
     */
    @Override
    public Set<OptionDTO> getContractOptionsWithSets(Integer contractId) {
        Contract contract = contractDAO.get(contractId);
        return OptionMapper.EntitySetToDTOSetWithSets(
                contract.getChosenOption());
    }

    @Override
    public List<ContractDTO> getContracts(Integer page, Integer contractsPerPage) {

        List<ContractDTO> contractDTOs = new ArrayList<ContractDTO>();
        for (Contract contract : contractDAO.getContracts(page, contractsPerPage)){
            contractDTOs.add(ContractMapper.EntityToDTOWithSet(contract));
        }
        return contractDTOs;
    }

    @Override
    public Long getContractCount(){
        return contractDAO.getContractCount();
    }
}
