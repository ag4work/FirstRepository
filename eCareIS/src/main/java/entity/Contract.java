package entity;

import javax.persistence.*;

/**
 * Created by Alexey on 23.06.2015.
 */
@Entity
public class Contract {
    private Integer contractId;
    private Long phoneNumber;
    private Integer tariffId;
    private Boolean blocked;
    private Boolean blockedByStaff;
    private Integer balance;
    private User user;

    @Id
    @Column(name = "contract_id")
    @GeneratedValue
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
    @Column(name = "tariff_id")
    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
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
        if (tariffId != null ? !tariffId.equals(contract.tariffId) : contract.tariffId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contractId != null ? contractId.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (tariffId != null ? tariffId.hashCode() : 0);
        result = 31 * result + (blocked != null ? blocked.hashCode() : 0);
        result = 31 * result + (blockedByStaff != null ? blockedByStaff.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", phoneNumber=" + phoneNumber +
                ", tariffId=" + tariffId +
                ", blocked=" + blocked +
                ", blockedByStaff=" + blockedByStaff +
                ", balance=" + balance +
                '}';
    }
}
