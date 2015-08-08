package utils.Mappers;

import entity.Tariff;
import service.DTO.TariffDTO;

/**
 * Created by Alexey on 03.07.2015.
 */
public class TariffMapper {

    private TariffMapper() {
    }

    public static Tariff DTOToEntity(TariffDTO tariffDTO){
        if (tariffDTO ==null) return null;
        Tariff tariff = new Tariff();

        tariff.setTariffId(tariffDTO.getTariffId());
        tariff.setTitle(tariffDTO.getTitle());
        tariff.setPrice(tariffDTO.getPrice());

        return tariff;
    }

    public static TariffDTO EntityToDTO(Tariff tariff){
        if (tariff ==null) return null;
        TariffDTO tariffDTO = new TariffDTO();

        tariffDTO.setTariffId(tariff.getTariffId());
        tariffDTO.setTitle(tariff.getTitle());
        tariffDTO.setPrice(tariff.getPrice());

        return tariffDTO;
    }

    public static TariffDTO EntityToDTOWithSets(Tariff tariff){
        TariffDTO tariffDTO = EntityToDTO(tariff);
        tariffDTO.setContractHasThisTariff(
                ContractMapper.EntitySetToDTOSet(
                        tariff.getContractHasThisTariff()));
        tariffDTO.setPossibleOption(OptionMapper.EntitySetToDTOSet(
                tariff.getPossibleOption()));
        return tariffDTO;
    }


}
