package finapp.domain.investmentStrategy;

import finapp.domain.investment.InvestmentService;
import finapp.domain.investmentStrategy.commands.CreateInvestmentStrategyCommand;
import finapp.domain.investmentStrategy.commands.CreateStrategyCategoryCommand;
import finapp.domain.investmentStrategy.models.MacroStrategy;
import finapp.domain.investmentStrategy.models.MacroStrategyBuilder;
import finapp.domain.investmentStrategy.models.Snapshot;
import finapp.domain.investmentStrategy.models.TickerPriceProvider;

import java.util.concurrent.CompletableFuture;

public class InvestmentStrategyService {

    private final InvestmentService investmentService;
    private final InvestmentStrategyRepository repository;
    private final InvestmentSnapshotRepository snapshotRepository;
    private final TickerPriceProvider tickerPriceProvider;

    public InvestmentStrategyService(final InvestmentService investmentService,
                                     final InvestmentStrategyRepository repository,
                                     final InvestmentSnapshotRepository snapshotRepository,
                                     final TickerPriceProvider tickerPriceProvider) {
        this.investmentService = investmentService;
        this.repository = repository;
        this.snapshotRepository = snapshotRepository;
        this.tickerPriceProvider = tickerPriceProvider;
    }

    public CompletableFuture<Void> createInvestmentStrategy(CreateInvestmentStrategyCommand command) {
        return investmentService.findInvestment(command.getTicker())
                .thenCompose(investment -> repository.createStrategyDefinition(command.toInvestmentStrategy()));
    }

    public CompletableFuture<Void> createMacroStrategy(CreateStrategyCategoryCommand command) {
        return repository.createCategoryDefinition(command.toStrategyCategory());
    }

    public CompletableFuture<MacroStrategy> getStrategy() {
        MacroStrategyBuilder builder = new MacroStrategyBuilder();
        builder.withTickerPriceProvider(tickerPriceProvider);
        return CompletableFuture.allOf(
            repository.getCategoryDefinitions().thenAccept(builder::withCategoryDefinitions),
            repository.getStrategyDefinitions().thenAccept(builder::withStrategyDefinitions),
            investmentService.getInvestments().thenAccept(builder::withInvestments)
        ).thenApply(v -> builder.build());
    }

    public void saveSnapshot(Snapshot snapshot) {
        snapshotRepository.saveSnapshot(snapshot);
    }

}
