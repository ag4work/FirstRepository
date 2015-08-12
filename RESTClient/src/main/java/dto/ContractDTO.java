package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Created by Alexey on 02.07.2015.
 */
@XmlRootElement(name = "contract")
public class ContractDTO {
    private Integer contractId;
    private Long phoneNumber;
    private Boolean blocked;
    private Boolean blockedByStaff;
    private Integer balance;
    private TariffDTO tariffDTO;
    private UserDTO userDTO;
    private Set<OptionDTO> chosenOption;

    public ContractDTO() {
    }
    @XmlElement
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
    @XmlElement
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @XmlElement
    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }
    @XmlElement
    public Boolean getBlockedByStaff() {
        return blockedByStaff;
    }

    public void setBlockedByStaff(Boolean blockedByStaff) {
        this.blockedByStaff = blockedByStaff;
    }
    @XmlElement
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public TariffDTO getTariffDTO() {
        return tariffDTO;
    }

    public void setTariffDTO(TariffDTO tariffDTO) {
        this.tariffDTO = tariffDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Set<OptionDTO> getChosenOption() {
        return chosenOption;
    }

    public void setChosenOption(Set<OptionDTO> chosenOption) {
        this.chosenOption = chosenOption;
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "contractId=" + contractId +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractDTO that = (ContractDTO) o;

        if (!phoneNumber.equals(that.phoneNumber)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return phoneNumber.hashCode();
    }
}
