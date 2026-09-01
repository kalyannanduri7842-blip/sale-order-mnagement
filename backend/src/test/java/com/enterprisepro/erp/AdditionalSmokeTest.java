package com.enterprisepro.erp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AdditionalSmokeTest {
    @Test
    void serviceSummaryWorks() {
        var service = new com.enterprisepro.erp.analytics.EnterpriseForecastEngine();
        assertEquals(1.0, service.revenueModel1(0.0, 0), 0.0001);
        assertEquals(105.917, service.revenueModel2(100.0, 1), 0.0001);
    }
}
