package finapp.domain.investmentStrategy.models;

import java.math.BigDecimal;

public interface TickerPriceProvider {
    BigDecimal getMarketPriceOf(String ticker);
}
