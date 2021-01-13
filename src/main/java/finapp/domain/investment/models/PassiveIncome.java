package finapp.domain.investment.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PassiveIncome {
    private final LocalDate incomeDate;

    private final String ticker;

    private final Integer quantity;

    private final BigDecimal totalValue;

    private final PassiveIncomeType type;

    public PassiveIncome(final LocalDate incomeDate, final String ticker, final Integer quantity, final BigDecimal totalValue, final PassiveIncomeType type) {
        this.incomeDate = incomeDate;
        this.ticker = ticker;
        this.quantity = quantity;
        this.totalValue = totalValue;
        this.type = type;
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }
}
