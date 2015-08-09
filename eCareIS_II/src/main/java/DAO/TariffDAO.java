package dao;


import entity.Contract;
import entity.Tariff;

import java.util.Set;

/**
 * Created by Alexey on 01.07.2015.
 */

public interface TariffDAO extends DAOOperations<Tariff>  {

    public Set<Contract> getContractsByTariff(Integer tariffId);

}

