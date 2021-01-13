package finapp.domain.investment;

import finapp.domain.investment.models.Investment;
import finapp.domain.investment.models.PassiveIncome;
import finapp.domain.investment.models.TickerOperation;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface InvestmentRepository {

    CompletableFuture<Void> insertOperation(TickerOperation tickerOperation);

    CompletableFuture<Void> insertIncome(PassiveIncome passiveIncome);

    CompletableFuture<Set<Investment>> getInvestments();

    CompletableFuture<Optional<Investment>> findByTicker(String ticker);
}
