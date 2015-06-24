package DAO;

import entity.Contract;

import java.util.List;

/**
 * Created by Alexey on 23.06.2015.
 */
public interface ContractDAO {
    void add(Contract contract);
    void update(Contract contract);
    void delete(Contract contract);
    Contract getById(Integer id);
    List<Contract> getAll();
}
