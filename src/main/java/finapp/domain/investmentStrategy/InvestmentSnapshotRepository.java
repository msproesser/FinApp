package finapp.domain.investmentStrategy;

import finapp.domain.investmentStrategy.models.Snapshot;

import java.util.concurrent.CompletableFuture;

public interface InvestmentSnapshotRepository {

    CompletableFuture<Void> saveSnapshot(Snapshot snapshot);

}
