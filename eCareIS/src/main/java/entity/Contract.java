package entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alexey on 01.07.2015.
 */
@Entity
public class Contract {
    private Integer contractId;
    private Long phoneNumber;
    private Boolean blocked;
    private Boolean blockedByStaff;
    private Integer balance;
    private Tariff tariffByTariffId;
    private User userByClientId;
    private List<Option> chosenOption;

    @Id
    @GeneratedValue
    @Column(name = "contract_id")
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    @Basic
    @Column(name = "phone_number")
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "blocked")
    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Basic
    @Column(name = "blocked_by_staff")
    public Boolean getBlockedByStaff() {
        return blockedByStaff;
    }

    public void setBlockedByStaff(Boolean blockedByStaff) {
        this.blockedByStaff = blockedByStaff;
    }

    @Basic
    @Column(name = "balance")
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        if (balance != null ? !balance.equals(contract.balance) : contract.balance != null) return false;
        if (blocked != null ? !blocked.equals(contract.blocked) : contract.blocked != null) return false;
        if (blockedByStaff != null ? !blockedByStaff.equals(contract.blockedByStaff) : contract.blockedByStaff != null)
            return false;
        if (contractId != null ? !contractId.equals(contract.contractId) : contract.contractId != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(contract.phoneNumber) : contract.phoneNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contractId != null ? contractId.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (blocked != null ? blocked.hashCode() : 0);
        result = 31 * result + (blockedByStaff != null ? blockedByStaff.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tariff_id", referencedColumnName = "tariff_id")
    public Tariff getTariffByTariffId() {
        return tariffByTariffId;
    }

    public void setTariffByTariffId(Tariff tariffByTariffId) {
        this.tariffByTariffId = tariffByTariffId;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "user_id")
    public User getUserByClientId() {
        return userByClientId;
    }

    public void setUserByClientId(User userByClientId) {
        this.userByClientId = userByClientId;
    }

    @ManyToMany
    @JoinTable(name = "contract_chosen_option", catalog = "ecareis", schema = "", joinColumns = @JoinColumn(name = "contract_id", referencedColumnName = "contract_id"), inverseJoinColumns = @JoinColumn(name = "chosen_option_id", referencedColumnName = "option_id"))
    public List<Option> getChosenOption() {
        return chosenOption;
    }

    public void setChosenOption(List<Option> chosenOption) {
        this.chosenOption = chosenOption;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "phoneNumber=" + phoneNumber +
                '}';
    }
}
