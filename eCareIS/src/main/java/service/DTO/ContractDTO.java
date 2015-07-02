package service.DTO;
import java.util.List;

/**
 * Created by Alexey on 02.07.2015.
 */
public class ContractDTO {
    private Integer contractId;
    private Long phoneNumber;
    private Boolean blocked;
    private Boolean blockedByStaff;
    private Integer balance;
    private TariffDTO tariffByTariffId;
    private UserDTO userByClientId;
    private List<OptionDTO> chosenOption;

    public ContractDTO() {
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Boolean getBlockedByStaff() {
        return blockedByStaff;
    }

    public void setBlockedByStaff(Boolean blockedByStaff) {
        this.blockedByStaff = blockedByStaff;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public TariffDTO getTariffByTariffId() {
        return tariffByTariffId;
    }

    public void setTariffByTariffId(TariffDTO tariffByTariffId) {
        this.tariffByTariffId = tariffByTariffId;
    }

    public UserDTO getUserByClientId() {
        return userByClientId;
    }

    public void setUserByClientId(UserDTO userByClientId) {
        this.userByClientId = userByClientId;
    }

    public List<OptionDTO> getChosenOption() {
        return chosenOption;
    }

    public void setChosenOption(List<OptionDTO> chosenOption) {
        this.chosenOption = chosenOption;
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "contractId=" + contractId +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
