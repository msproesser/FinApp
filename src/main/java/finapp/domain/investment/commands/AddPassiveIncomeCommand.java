package finapp.domain.investment.commands;

import finapp.domain.investment.models.PassiveIncome;
import finapp.domain.investment.models.PassiveIncomeType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddPassiveIncomeCommand {

    private final LocalDate incomeDate;

    private final String ticker;

    private final Integer quantity;

    private final BigDecimal value;

    private final PassiveIncomeType type;

    public AddPassiveIncomeCommand(LocalDate incomeDate, String ticker, Integer quantity, BigDecimal value, PassiveIncomeType type) {
        this.incomeDate = incomeDate;
        this.ticker = ticker;
        this.quantity = quantity;
        this.value = value;
        this.type = type;
    }

    public String getTicker() {
        return ticker;
    }

    public PassiveIncome toPassiveIncome() {
        return new PassiveIncome(incomeDate, ticker, quantity, value, type);
    }
}
