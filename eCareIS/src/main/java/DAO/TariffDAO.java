package DAO;

import entity.Tariff;

import java.util.List;

/**
 * Created by Alexey on 18.06.2015.
 */
public interface TariffDAO {
    List<Tariff> findAll();

    void create(Tariff tariff);
}
