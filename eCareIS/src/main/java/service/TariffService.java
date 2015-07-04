package service;

import service.DTO.TariffDTO;

import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
public interface TariffService {

    public TariffDTO getTariffById(Integer tariffId);
    public Set<TariffDTO> getAllTariffs();
    public void addTariff(TariffDTO tariffDTO);
//    public void removeTariff(Integer tariffId);

}
