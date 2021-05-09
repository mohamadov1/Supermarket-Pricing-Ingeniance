package model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class Product {

    private int codeBarProdcut;
    private BigDecimal price;
    private int quantity;
    private BigDecimal weight;
    private String unit;

    public Product(BigDecimal price, int codeBarProdcut) {
        this.price = price;
        this.codeBarProdcut = codeBarProdcut;
    }

    public Product(BigDecimal price, String unit, int codeBarProdcut) {
        this.price = price;
        this.unit = unit;
        this.codeBarProdcut = codeBarProdcut;
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
