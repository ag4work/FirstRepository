package entity;

/**
 * Created by Alexey on 18.06.2015.
 */
public class Tariff {

    private int tariffId;
    private String title;
    private int price;

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        if (price != tariff.price) return false;
        if (tariffId != tariff.tariffId) return false;
        if (title != null ? !title.equals(tariff.title) : tariff.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tariffId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "tariffId=" + tariffId +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}