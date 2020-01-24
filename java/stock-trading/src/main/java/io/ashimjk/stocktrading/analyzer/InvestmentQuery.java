package io.ashimjk.stocktrading.analyzer;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InvestmentQuery implements Comparable<InvestmentQuery> {

    private String stockId;
    private LocalDateTime queryTime;
    private Integer priority;
    private String investor;

    /**
     * Only used with ArrayList needs to prioritized the query
     */
    @Override
    public int compareTo(InvestmentQuery other) {
        final int priorityCompare = priority.compareTo(other.priority);

        if (priorityCompare != 0) {
            return priorityCompare;
        }

        return queryTime.compareTo(other.queryTime);
    }

}
