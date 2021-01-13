package finapp.domain.investmentStrategy.commands;

import finapp.domain.investment.models.Investment;
import finapp.domain.investmentStrategy.models.StrategyDefinition;

public class CreateInvestmentStrategyCommand {

    private final String id;
    private final String ticker;
    private final String category;
    private final Integer score;

    public CreateInvestmentStrategyCommand(final String id, final String ticker, final String category, final Integer score) {
        this.id = id;
        this.ticker = ticker;
        this.category = category;
        this.score = score;
    }

    public String getTicker() {
        return ticker;
    }

    public StrategyDefinition toInvestmentStrategy() {
        return new StrategyDefinition(id, ticker, category, score);
    }
}
