package finapp.domain.investment;

import finapp.domain.investment.commands.AddPassiveIncomeCommand;
import finapp.domain.investment.commands.OperateTickerCommand;
import finapp.domain.investment.models.Investment;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class InvestmentService {

    private final InvestmentRepository repository;

    public InvestmentService(final InvestmentRepository repository) {
        this.repository = repository;
    }

    public CompletableFuture<Void> operateTicker(OperateTickerCommand command) {
        return repository.insertOperation(command.toTickerOperation());
    }

    public CompletableFuture<Void> addPassiveIncome(AddPassiveIncomeCommand command) {
        return repository.insertIncome(command.toPassiveIncome());
    }

    public CompletableFuture<Set<Investment>> getInvestments() {
        return repository.getInvestments();
    }

    public CompletableFuture<Investment> findInvestment(final String ticker) {
        return repository.findByTicker(ticker)
                .thenCompose(investment -> CompletableFuture.completedFuture(investment.orElse(new Investment(ticker))));
    }
}
