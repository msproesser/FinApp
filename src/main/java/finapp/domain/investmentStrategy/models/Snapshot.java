package finapp.domain.investmentStrategy.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Snapshot {

    private final LocalDate snapshotDay;
    private final Map<String, BigDecimal> snapshotEntry;

    public Snapshot(final LocalDate snapshotDay, final Map<String, BigDecimal> snapshotEntry) {
        this.snapshotDay = snapshotDay;
        this.snapshotEntry = snapshotEntry;
    }

    public Snapshot merge(Snapshot other) {
        if (snapshotDay.isEqual(other.snapshotDay)) {
            snapshotEntry.putAll(other.snapshotEntry);
        }
        return this;
    }
}
