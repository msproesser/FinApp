package finapp.domain.investment.commands;

import finapp.domain.investment.models.TickerOperation;
import finapp.domain.investment.models.TickerOperationType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OperateTickerCommand {

    private final LocalDate operationDate;

    private final BigDecimal unitPrice;

    private final Integer quantity;

    private final String ticker;

    private final TickerOperationType operationType;

    public OperateTickerCommand(LocalDate operationDate, BigDecimal unitPrice, Integer quantity, String ticker, TickerOperationType operationType) {
        this.operationDate = operationDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.ticker = ticker;
        this.operationType = operationType;
    }

    public String getTicker() {
        return ticker;
    }

    public TickerOperation toTickerOperation() {
        return new TickerOperation(ticker, operationDate, unitPrice, quantity, operationType);
    }
}
