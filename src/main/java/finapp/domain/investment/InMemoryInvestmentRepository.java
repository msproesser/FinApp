package finapp.domain.investment;

import finapp.domain.investment.models.Investment;
import finapp.domain.investment.models.PassiveIncome;
import finapp.domain.investment.models.TickerOperation;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class InMemoryInvestmentRepository implements InvestmentRepository {

    private final Map<String, Investment> investments = new HashMap<>();

    @Override
    public CompletableFuture<Void> insertOperation(final TickerOperation tickerOperation) {
        final Investment investment = investments.getOrDefault(tickerOperation.getTicker(), new Investment(tickerOperation.getTicker()));
        investment.addOperation(tickerOperation);
        investments.put(investment.getTicker(), investment);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> insertIncome(final PassiveIncome passiveIncome) {
        final Investment investment = investments.getOrDefault(passiveIncome.getTicker(), new Investment(passiveIncome.getTicker()));
        investment.addPassiveIncome(passiveIncome);
        investments.put(investment.getTicker(), investment);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Set<Investment>> getInvestments() {
        return CompletableFuture.completedFuture(new HashSet<>(investments.values()));
    }

    @Override
    public CompletableFuture<Optional<Investment>> findByTicker(final String ticker) {
        return CompletableFuture.completedFuture(Optional.ofNullable(investments.get(ticker)));
    }
}
