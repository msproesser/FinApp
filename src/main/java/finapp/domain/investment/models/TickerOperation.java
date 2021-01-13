package finapp.domain.investment.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TickerOperation {

    private final String ticker;

    private final LocalDate operationDate;

    private final BigDecimal unitPrice;

    private final Integer quantity;

    private final TickerOperationType tickerOperationType;

    public TickerOperation(final String ticker, final LocalDate operationDate, final BigDecimal unitPrice, final Integer quantity, final TickerOperationType tickerOperationType) {
        this.ticker = ticker;
        this.operationDate = operationDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.tickerOperationType = tickerOperationType;
    }

    public String getTicker() {
        return ticker;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public TickerOperationType getTickerOperationType() {
        return tickerOperationType;
    }

    public BigDecimal getOperationPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
