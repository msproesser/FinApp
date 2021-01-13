package finapp.domain.investment.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Investment {

    private final String ticker;
    private final List<TickerOperation> operations = new ArrayList<>();
    private final List<PassiveIncome> incomes = new ArrayList<>();

    private BigDecimal totalValue;
    private Integer quantity;
    private BigDecimal totalIncome;

    public Investment(final String ticker) {
        this.ticker = ticker;
    }

    public void addOperation(TickerOperation operation) {
        operations.add(operation);
    }

    public void addPassiveIncome(PassiveIncome passiveIncome) {
        incomes.add(passiveIncome);
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getTotalPrice() {
        if (totalValue == null) {
            totalValue = operations.stream().map(operation -> {
                if (TickerOperationType.BUY.equals(operation.getTickerOperationType())) {
                    return operation.getOperationPrice();
                }
                return operation.getOperationPrice().negate();
            }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        }
        return totalValue;
    }

    public BigDecimal getTotalIncome() {
        if (totalIncome == null) {
            totalIncome = incomes.stream().map(PassiveIncome::getTotalValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return totalIncome;
    }

    public Integer getQuantity() {
        if (quantity == null) {
            quantity = operations.stream().map(operation -> {
                if (TickerOperationType.BUY.equals(operation.getTickerOperationType())) {
                    return operation.getQuantity();
                }
                return - operation.getQuantity();
            }).reduce(0, Integer::sum);
        }
        return quantity;
    }

    public BigDecimal getAveragePrice() {
        if (getQuantity().equals(0)) {
            return BigDecimal.ZERO;
        }
        return getTotalPrice().divide(BigDecimal.valueOf(getQuantity()), RoundingMode.UNNECESSARY);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Investment that = (Investment) o;
        return ticker.equals(that.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker);
    }
}
