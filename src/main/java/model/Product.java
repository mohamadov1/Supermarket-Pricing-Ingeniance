package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private int codeBarProdcut;
    private BigDecimal price;
    private int quantity;
    private BigDecimal weight;
    private String unit;

    public Product() {
    }

    public Product(BigDecimal price, int id) {
        this.price = price;
        this.codeBarProdcut = id;
    }

    public Product(BigDecimal price, String unit, int id) {
        this.price = price;
        this.unit = unit;
        this.codeBarProdcut = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCodeBarProdcut() {
        return codeBarProdcut;
    }

    public void setCodeBarProdcut(int codeBarProdcut) {
        this.codeBarProdcut = codeBarProdcut;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return codeBarProdcut == product.codeBarProdcut && Objects.equals(unit, product.unit) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeBarProdcut, unit, price);
    }
}
