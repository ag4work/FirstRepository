package service;

import entity.Contract;
import exceptions.BlockedByStaffException;
import org.springframework.stereotype.Service;
import service.DTO.ContractDTO;
import service.DTO.OptionDTO;

import java.util.List;
import java.util.Set;

/**
 * The class implements a business layer routines for contracts.
 * It interacts with dao with IDs (or entity)
 * and return a DTOs to presentation.
 */
@Service
public interface ContractService {

    /**
     * This method return ContractDTO object by ContractId
     * @param id ContractId
     * @return ContractDTO
     */
    public ContractDTO getContract(Integer id);

    /**
     * Returns the set of all Contracts
     * @return ContractDTO
     */
    public Set<ContractDTO> getAllContracts();

    /**
     * Add a new contract. contractDto object mapped to dao entity "contract"
     * @param contractDto contractDto object
     */
    public void add(ContractDTO contractDto);

    /**
     * returns a set of contact which are connected to a specific user
     * @param userId userId
     * @return set of contractDTO
     */
    public Set<ContractDTO> getContractsByUserId(Integer userId);

    /**
     * Returns a list of free numbers
     * @param setSize size of list
     * @return set of numbers. Each number is represented as long
     */
    public Set<Long> getFreeNumberSet(int setSize);

    /**
     * Method sets property of contract that is was blocked by employee
     * @param contractId contract Id
     */
    public void blockByStaff(Integer contractId);

    /**
     * Method sets property of contract that is was unblocked by employee
     * @param contractId contract Id
     */
    public void unblockByStaff(Integer contractId);

    /**
     * Method sets property of contract that is was blocked by client
     * @param contractId contract Id
     */
    public void blockByClient(Integer contractId);

    /**
     * Method sets property of contract that is was unblocked by client
     * @param contractId contract Id
     */
    public void unblockByClient(Integer contractId) throws BlockedByStaffException;

    /**
     * Finds a contract by phonenumber
     * @param phonenumber phonenumber in Long format
     * @return ContractDTO
     */
    public ContractDTO getContractByPhonenumber(Long phonenumber);

    /**
     * Return set of contracts which are connected to the specific tariff
     * @param tariffId tariffId
     * @return set of ContractDTO
     */
    public Set<ContractDTO> getContractsByTariff(Integer tariffId);

    /**
     * The method takes shopping cart. Takes tariff and options from it
     * and connect them to the specific contract.
     * Balance is also to be reduced
     * @param cart shopping cart object
     * @param contractId contractId
     */
    public void applyCart(Cart cart, Integer contractId);

    /**
     * The method takes the options, finds a tree of all dependent option for it,
     * and removes all of them from the contract
     * @param optionId optionId
     * @param contractId contractId
     */
    public void removeOptionWithAllDependent(Integer optionId, Integer contractId);

    /**
     * Returns a set of options for the specific contact.
     * getContractOptionsWithSets - this "withSets" means that each returning
     * DTO objects will contain all possible sets like DependentOptions,
     * RrequiredOptions, InconsistentOptions
     * @param contractId
     * @return
     */
    public Set<OptionDTO> getContractOptionsWithSets(Integer contractId);

    /**
     * Returns the amount of all contracts
     * @return amount of all contracts
     */
    public Long getContractCount();

    /**
     * Returns bunches of Contracts. Made for pagination.
     * @param page page
     * @param contractsPerPage  number of contracts per page
     * @return list of ContractDTO
     */
    public List<ContractDTO> getContracts(Integer page, Integer contractsPerPage);

}
