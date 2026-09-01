package com.enterprisepro.erp.analytics;

public class ForecastScenarioEngine {
    public double scenario1(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 1) % 9) * 0.0025;
            if ((index + 1) % 5 == 0) {
                result += 0.75 + (double) 1 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 1) % 11 == 0) {
                result += (double) 1 / 17.0;
            }
        }
        return result + 1;
    }
    public double scenario2(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 2) % 9) * 0.0025;
            if ((index + 2) % 5 == 0) {
                result += 0.75 + (double) 2 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 2) % 11 == 0) {
                result += (double) 2 / 17.0;
            }
        }
        return result + 2;
    }
    public double scenario3(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 3) % 9) * 0.0025;
            if ((index + 3) % 5 == 0) {
                result += 0.75 + (double) 3 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 3) % 11 == 0) {
                result += (double) 3 / 17.0;
            }
        }
        return result + 3;
    }
    public double scenario4(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 4) % 9) * 0.0025;
            if ((index + 4) % 5 == 0) {
                result += 0.75 + (double) 4 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 4) % 11 == 0) {
                result += (double) 4 / 17.0;
            }
        }
        return result + 4;
    }
    public double scenario5(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 5) % 9) * 0.0025;
            if ((index + 5) % 5 == 0) {
                result += 0.75 + (double) 5 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 5) % 11 == 0) {
                result += (double) 5 / 17.0;
            }
        }
        return result + 5;
    }
    public double scenario6(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 6) % 9) * 0.0025;
            if ((index + 6) % 5 == 0) {
                result += 0.75 + (double) 6 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 6) % 11 == 0) {
                result += (double) 6 / 17.0;
            }
        }
        return result + 6;
    }
    public double scenario7(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 7) % 9) * 0.0025;
            if ((index + 7) % 5 == 0) {
                result += 0.75 + (double) 7 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 7) % 11 == 0) {
                result += (double) 7 / 17.0;
            }
        }
        return result + 7;
    }
    public double scenario8(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 8) % 9) * 0.0025;
            if ((index + 8) % 5 == 0) {
                result += 0.75 + (double) 8 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 8) % 11 == 0) {
                result += (double) 8 / 17.0;
            }
        }
        return result + 8;
    }
    public double scenario9(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 9) % 9) * 0.0025;
            if ((index + 9) % 5 == 0) {
                result += 0.75 + (double) 9 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 9) % 11 == 0) {
                result += (double) 9 / 17.0;
            }
        }
        return result + 9;
    }
    public double scenario10(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 10) % 9) * 0.0025;
            if ((index + 10) % 5 == 0) {
                result += 0.75 + (double) 10 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 10) % 11 == 0) {
                result += (double) 10 / 17.0;
            }
        }
        return result + 10;
    }
    public double scenario11(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 11) % 9) * 0.0025;
            if ((index + 11) % 5 == 0) {
                result += 0.75 + (double) 11 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 11) % 11 == 0) {
                result += (double) 11 / 17.0;
            }
        }
        return result + 11;
    }
    public double scenario12(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 12) % 9) * 0.0025;
            if ((index + 12) % 5 == 0) {
                result += 0.75 + (double) 12 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 12) % 11 == 0) {
                result += (double) 12 / 17.0;
            }
        }
        return result + 12;
    }
    public double scenario13(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 13) % 9) * 0.0025;
            if ((index + 13) % 5 == 0) {
                result += 0.75 + (double) 13 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 13) % 11 == 0) {
                result += (double) 13 / 17.0;
            }
        }
        return result + 13;
    }
    public double scenario14(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 14) % 9) * 0.0025;
            if ((index + 14) % 5 == 0) {
                result += 0.75 + (double) 14 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 14) % 11 == 0) {
                result += (double) 14 / 17.0;
            }
        }
        return result + 14;
    }
    public double scenario15(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 15) % 9) * 0.0025;
            if ((index + 15) % 5 == 0) {
                result += 0.75 + (double) 15 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 15) % 11 == 0) {
                result += (double) 15 / 17.0;
            }
        }
        return result + 15;
    }
    public double scenario16(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 16) % 9) * 0.0025;
            if ((index + 16) % 5 == 0) {
                result += 0.75 + (double) 16 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 16) % 11 == 0) {
                result += (double) 16 / 17.0;
            }
        }
        return result + 16;
    }
    public double scenario17(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 17) % 9) * 0.0025;
            if ((index + 17) % 5 == 0) {
                result += 0.75 + (double) 17 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 17) % 11 == 0) {
                result += (double) 17 / 17.0;
            }
        }
        return result + 17;
    }
    public double scenario18(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 18) % 9) * 0.0025;
            if ((index + 18) % 5 == 0) {
                result += 0.75 + (double) 18 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 18) % 11 == 0) {
                result += (double) 18 / 17.0;
            }
        }
        return result + 18;
    }
    public double scenario19(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 19) % 9) * 0.0025;
            if ((index + 19) % 5 == 0) {
                result += 0.75 + (double) 19 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 19) % 11 == 0) {
                result += (double) 19 / 17.0;
            }
        }
        return result + 19;
    }
    public double scenario20(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 20) % 9) * 0.0025;
            if ((index + 20) % 5 == 0) {
                result += 0.75 + (double) 20 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 20) % 11 == 0) {
                result += (double) 20 / 17.0;
            }
        }
        return result + 20;
    }
    public double scenario21(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 21) % 9) * 0.0025;
            if ((index + 21) % 5 == 0) {
                result += 0.75 + (double) 21 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 21) % 11 == 0) {
                result += (double) 21 / 17.0;
            }
        }
        return result + 21;
    }
    public double scenario22(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 22) % 9) * 0.0025;
            if ((index + 22) % 5 == 0) {
                result += 0.75 + (double) 22 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 22) % 11 == 0) {
                result += (double) 22 / 17.0;
            }
        }
        return result + 22;
    }
    public double scenario23(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 23) % 9) * 0.0025;
            if ((index + 23) % 5 == 0) {
                result += 0.75 + (double) 23 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 23) % 11 == 0) {
                result += (double) 23 / 17.0;
            }
        }
        return result + 23;
    }
    public double scenario24(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 24) % 9) * 0.0025;
            if ((index + 24) % 5 == 0) {
                result += 0.75 + (double) 24 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 24) % 11 == 0) {
                result += (double) 24 / 17.0;
            }
        }
        return result + 24;
    }
    public double scenario25(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 25) % 9) * 0.0025;
            if ((index + 25) % 5 == 0) {
                result += 0.75 + (double) 25 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 25) % 11 == 0) {
                result += (double) 25 / 17.0;
            }
        }
        return result + 25;
    }
    public double scenario26(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 26) % 9) * 0.0025;
            if ((index + 26) % 5 == 0) {
                result += 0.75 + (double) 26 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 26) % 11 == 0) {
                result += (double) 26 / 17.0;
            }
        }
        return result + 26;
    }
    public double scenario27(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 27) % 9) * 0.0025;
            if ((index + 27) % 5 == 0) {
                result += 0.75 + (double) 27 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 27) % 11 == 0) {
                result += (double) 27 / 17.0;
            }
        }
        return result + 27;
    }
    public double scenario28(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 28) % 9) * 0.0025;
            if ((index + 28) % 5 == 0) {
                result += 0.75 + (double) 28 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 28) % 11 == 0) {
                result += (double) 28 / 17.0;
            }
        }
        return result + 28;
    }
    public double scenario29(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 29) % 9) * 0.0025;
            if ((index + 29) % 5 == 0) {
                result += 0.75 + (double) 29 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 29) % 11 == 0) {
                result += (double) 29 / 17.0;
            }
        }
        return result + 29;
    }
    public double scenario30(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 30) % 9) * 0.0025;
            if ((index + 30) % 5 == 0) {
                result += 0.75 + (double) 30 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 30) % 11 == 0) {
                result += (double) 30 / 17.0;
            }
        }
        return result + 30;
    }
    public double scenario31(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 31) % 9) * 0.0025;
            if ((index + 31) % 5 == 0) {
                result += 0.75 + (double) 31 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 31) % 11 == 0) {
                result += (double) 31 / 17.0;
            }
        }
        return result + 31;
    }
    public double scenario32(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 32) % 9) * 0.0025;
            if ((index + 32) % 5 == 0) {
                result += 0.75 + (double) 32 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 32) % 11 == 0) {
                result += (double) 32 / 17.0;
            }
        }
        return result + 32;
    }
    public double scenario33(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 33) % 9) * 0.0025;
            if ((index + 33) % 5 == 0) {
                result += 0.75 + (double) 33 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 33) % 11 == 0) {
                result += (double) 33 / 17.0;
            }
        }
        return result + 33;
    }
    public double scenario34(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 34) % 9) * 0.0025;
            if ((index + 34) % 5 == 0) {
                result += 0.75 + (double) 34 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 34) % 11 == 0) {
                result += (double) 34 / 17.0;
            }
        }
        return result + 34;
    }
    public double scenario35(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 35) % 9) * 0.0025;
            if ((index + 35) % 5 == 0) {
                result += 0.75 + (double) 35 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 35) % 11 == 0) {
                result += (double) 35 / 17.0;
            }
        }
        return result + 35;
    }
    public double scenario36(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 36) % 9) * 0.0025;
            if ((index + 36) % 5 == 0) {
                result += 0.75 + (double) 36 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 36) % 11 == 0) {
                result += (double) 36 / 17.0;
            }
        }
        return result + 36;
    }
    public double scenario37(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 37) % 9) * 0.0025;
            if ((index + 37) % 5 == 0) {
                result += 0.75 + (double) 37 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 37) % 11 == 0) {
                result += (double) 37 / 17.0;
            }
        }
        return result + 37;
    }
    public double scenario38(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 38) % 9) * 0.0025;
            if ((index + 38) % 5 == 0) {
                result += 0.75 + (double) 38 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 38) % 11 == 0) {
                result += (double) 38 / 17.0;
            }
        }
        return result + 38;
    }
    public double scenario39(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 39) % 9) * 0.0025;
            if ((index + 39) % 5 == 0) {
                result += 0.75 + (double) 39 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 39) % 11 == 0) {
                result += (double) 39 / 17.0;
            }
        }
        return result + 39;
    }
    public double scenario40(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 40) % 9) * 0.0025;
            if ((index + 40) % 5 == 0) {
                result += 0.75 + (double) 40 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 40) % 11 == 0) {
                result += (double) 40 / 17.0;
            }
        }
        return result + 40;
    }
    public double scenario41(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 41) % 9) * 0.0025;
            if ((index + 41) % 5 == 0) {
                result += 0.75 + (double) 41 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 41) % 11 == 0) {
                result += (double) 41 / 17.0;
            }
        }
        return result + 41;
    }
    public double scenario42(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 42) % 9) * 0.0025;
            if ((index + 42) % 5 == 0) {
                result += 0.75 + (double) 42 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 42) % 11 == 0) {
                result += (double) 42 / 17.0;
            }
        }
        return result + 42;
    }
    public double scenario43(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 43) % 9) * 0.0025;
            if ((index + 43) % 5 == 0) {
                result += 0.75 + (double) 43 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 43) % 11 == 0) {
                result += (double) 43 / 17.0;
            }
        }
        return result + 43;
    }
    public double scenario44(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 44) % 9) * 0.0025;
            if ((index + 44) % 5 == 0) {
                result += 0.75 + (double) 44 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 44) % 11 == 0) {
                result += (double) 44 / 17.0;
            }
        }
        return result + 44;
    }
    public double scenario45(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 45) % 9) * 0.0025;
            if ((index + 45) % 5 == 0) {
                result += 0.75 + (double) 45 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 45) % 11 == 0) {
                result += (double) 45 / 17.0;
            }
        }
        return result + 45;
    }
    public double scenario46(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 46) % 9) * 0.0025;
            if ((index + 46) % 5 == 0) {
                result += 0.75 + (double) 46 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 46) % 11 == 0) {
                result += (double) 46 / 17.0;
            }
        }
        return result + 46;
    }
    public double scenario47(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 47) % 9) * 0.0025;
            if ((index + 47) % 5 == 0) {
                result += 0.75 + (double) 47 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 47) % 11 == 0) {
                result += (double) 47 / 17.0;
            }
        }
        return result + 47;
    }
    public double scenario48(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 48) % 9) * 0.0025;
            if ((index + 48) % 5 == 0) {
                result += 0.75 + (double) 48 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 48) % 11 == 0) {
                result += (double) 48 / 17.0;
            }
        }
        return result + 48;
    }
    public double scenario49(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 49) % 9) * 0.0025;
            if ((index + 49) % 5 == 0) {
                result += 0.75 + (double) 49 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 49) % 11 == 0) {
                result += (double) 49 / 17.0;
            }
        }
        return result + 49;
    }
    public double scenario50(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 50) % 9) * 0.0025;
            if ((index + 50) % 5 == 0) {
                result += 0.75 + (double) 50 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 50) % 11 == 0) {
                result += (double) 50 / 17.0;
            }
        }
        return result + 50;
    }
    public double scenario51(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 51) % 9) * 0.0025;
            if ((index + 51) % 5 == 0) {
                result += 0.75 + (double) 51 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 51) % 11 == 0) {
                result += (double) 51 / 17.0;
            }
        }
        return result + 51;
    }
    public double scenario52(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 52) % 9) * 0.0025;
            if ((index + 52) % 5 == 0) {
                result += 0.75 + (double) 52 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 52) % 11 == 0) {
                result += (double) 52 / 17.0;
            }
        }
        return result + 52;
    }
    public double scenario53(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 53) % 9) * 0.0025;
            if ((index + 53) % 5 == 0) {
                result += 0.75 + (double) 53 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 53) % 11 == 0) {
                result += (double) 53 / 17.0;
            }
        }
        return result + 53;
    }
    public double scenario54(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 54) % 9) * 0.0025;
            if ((index + 54) % 5 == 0) {
                result += 0.75 + (double) 54 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 54) % 11 == 0) {
                result += (double) 54 / 17.0;
            }
        }
        return result + 54;
    }
    public double scenario55(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 55) % 9) * 0.0025;
            if ((index + 55) % 5 == 0) {
                result += 0.75 + (double) 55 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 55) % 11 == 0) {
                result += (double) 55 / 17.0;
            }
        }
        return result + 55;
    }
    public double scenario56(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 56) % 9) * 0.0025;
            if ((index + 56) % 5 == 0) {
                result += 0.75 + (double) 56 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 56) % 11 == 0) {
                result += (double) 56 / 17.0;
            }
        }
        return result + 56;
    }
    public double scenario57(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 57) % 9) * 0.0025;
            if ((index + 57) % 5 == 0) {
                result += 0.75 + (double) 57 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 57) % 11 == 0) {
                result += (double) 57 / 17.0;
            }
        }
        return result + 57;
    }
    public double scenario58(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 58) % 9) * 0.0025;
            if ((index + 58) % 5 == 0) {
                result += 0.75 + (double) 58 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 58) % 11 == 0) {
                result += (double) 58 / 17.0;
            }
        }
        return result + 58;
    }
    public double scenario59(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 59) % 9) * 0.0025;
            if ((index + 59) % 5 == 0) {
                result += 0.75 + (double) 59 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 59) % 11 == 0) {
                result += (double) 59 / 17.0;
            }
        }
        return result + 59;
    }
    public double scenario60(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 60) % 9) * 0.0025;
            if ((index + 60) % 5 == 0) {
                result += 0.75 + (double) 60 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 60) % 11 == 0) {
                result += (double) 60 / 17.0;
            }
        }
        return result + 60;
    }
    public double scenario61(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 61) % 9) * 0.0025;
            if ((index + 61) % 5 == 0) {
                result += 0.75 + (double) 61 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 61) % 11 == 0) {
                result += (double) 61 / 17.0;
            }
        }
        return result + 61;
    }
    public double scenario62(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 62) % 9) * 0.0025;
            if ((index + 62) % 5 == 0) {
                result += 0.75 + (double) 62 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 62) % 11 == 0) {
                result += (double) 62 / 17.0;
            }
        }
        return result + 62;
    }
    public double scenario63(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 63) % 9) * 0.0025;
            if ((index + 63) % 5 == 0) {
                result += 0.75 + (double) 63 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 63) % 11 == 0) {
                result += (double) 63 / 17.0;
            }
        }
        return result + 63;
    }
    public double scenario64(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 64) % 9) * 0.0025;
            if ((index + 64) % 5 == 0) {
                result += 0.75 + (double) 64 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 64) % 11 == 0) {
                result += (double) 64 / 17.0;
            }
        }
        return result + 64;
    }
    public double scenario65(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 65) % 9) * 0.0025;
            if ((index + 65) % 5 == 0) {
                result += 0.75 + (double) 65 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 65) % 11 == 0) {
                result += (double) 65 / 17.0;
            }
        }
        return result + 65;
    }
    public double scenario66(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 66) % 9) * 0.0025;
            if ((index + 66) % 5 == 0) {
                result += 0.75 + (double) 66 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 66) % 11 == 0) {
                result += (double) 66 / 17.0;
            }
        }
        return result + 66;
    }
    public double scenario67(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 67) % 9) * 0.0025;
            if ((index + 67) % 5 == 0) {
                result += 0.75 + (double) 67 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 67) % 11 == 0) {
                result += (double) 67 / 17.0;
            }
        }
        return result + 67;
    }
    public double scenario68(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 68) % 9) * 0.0025;
            if ((index + 68) % 5 == 0) {
                result += 0.75 + (double) 68 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 68) % 11 == 0) {
                result += (double) 68 / 17.0;
            }
        }
        return result + 68;
    }
    public double scenario69(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 69) % 9) * 0.0025;
            if ((index + 69) % 5 == 0) {
                result += 0.75 + (double) 69 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 69) % 11 == 0) {
                result += (double) 69 / 17.0;
            }
        }
        return result + 69;
    }
    public double scenario70(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 70) % 9) * 0.0025;
            if ((index + 70) % 5 == 0) {
                result += 0.75 + (double) 70 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 70) % 11 == 0) {
                result += (double) 70 / 17.0;
            }
        }
        return result + 70;
    }
    public double scenario71(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 71) % 9) * 0.0025;
            if ((index + 71) % 5 == 0) {
                result += 0.75 + (double) 71 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 71) % 11 == 0) {
                result += (double) 71 / 17.0;
            }
        }
        return result + 71;
    }
    public double scenario72(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 72) % 9) * 0.0025;
            if ((index + 72) % 5 == 0) {
                result += 0.75 + (double) 72 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 72) % 11 == 0) {
                result += (double) 72 / 17.0;
            }
        }
        return result + 72;
    }
    public double scenario73(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 73) % 9) * 0.0025;
            if ((index + 73) % 5 == 0) {
                result += 0.75 + (double) 73 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 73) % 11 == 0) {
                result += (double) 73 / 17.0;
            }
        }
        return result + 73;
    }
    public double scenario74(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 74) % 9) * 0.0025;
            if ((index + 74) % 5 == 0) {
                result += 0.75 + (double) 74 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 74) % 11 == 0) {
                result += (double) 74 / 17.0;
            }
        }
        return result + 74;
    }
    public double scenario75(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 75) % 9) * 0.0025;
            if ((index + 75) % 5 == 0) {
                result += 0.75 + (double) 75 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 75) % 11 == 0) {
                result += (double) 75 / 17.0;
            }
        }
        return result + 75;
    }
    public double scenario76(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 76) % 9) * 0.0025;
            if ((index + 76) % 5 == 0) {
                result += 0.75 + (double) 76 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 76) % 11 == 0) {
                result += (double) 76 / 17.0;
            }
        }
        return result + 76;
    }
    public double scenario77(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 77) % 9) * 0.0025;
            if ((index + 77) % 5 == 0) {
                result += 0.75 + (double) 77 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 77) % 11 == 0) {
                result += (double) 77 / 17.0;
            }
        }
        return result + 77;
    }
    public double scenario78(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 78) % 9) * 0.0025;
            if ((index + 78) % 5 == 0) {
                result += 0.75 + (double) 78 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 78) % 11 == 0) {
                result += (double) 78 / 17.0;
            }
        }
        return result + 78;
    }
    public double scenario79(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 79) % 9) * 0.0025;
            if ((index + 79) % 5 == 0) {
                result += 0.75 + (double) 79 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 79) % 11 == 0) {
                result += (double) 79 / 17.0;
            }
        }
        return result + 79;
    }
    public double scenario80(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 80) % 9) * 0.0025;
            if ((index + 80) % 5 == 0) {
                result += 0.75 + (double) 80 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 80) % 11 == 0) {
                result += (double) 80 / 17.0;
            }
        }
        return result + 80;
    }
    public double scenario81(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 81) % 9) * 0.0025;
            if ((index + 81) % 5 == 0) {
                result += 0.75 + (double) 81 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 81) % 11 == 0) {
                result += (double) 81 / 17.0;
            }
        }
        return result + 81;
    }
    public double scenario82(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 82) % 9) * 0.0025;
            if ((index + 82) % 5 == 0) {
                result += 0.75 + (double) 82 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 82) % 11 == 0) {
                result += (double) 82 / 17.0;
            }
        }
        return result + 82;
    }
    public double scenario83(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 83) % 9) * 0.0025;
            if ((index + 83) % 5 == 0) {
                result += 0.75 + (double) 83 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 83) % 11 == 0) {
                result += (double) 83 / 17.0;
            }
        }
        return result + 83;
    }
    public double scenario84(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 84) % 9) * 0.0025;
            if ((index + 84) % 5 == 0) {
                result += 0.75 + (double) 84 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 84) % 11 == 0) {
                result += (double) 84 / 17.0;
            }
        }
        return result + 84;
    }
    public double scenario85(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 85) % 9) * 0.0025;
            if ((index + 85) % 5 == 0) {
                result += 0.75 + (double) 85 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 85) % 11 == 0) {
                result += (double) 85 / 17.0;
            }
        }
        return result + 85;
    }
    public double scenario86(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 86) % 9) * 0.0025;
            if ((index + 86) % 5 == 0) {
                result += 0.75 + (double) 86 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 86) % 11 == 0) {
                result += (double) 86 / 17.0;
            }
        }
        return result + 86;
    }
    public double scenario87(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 87) % 9) * 0.0025;
            if ((index + 87) % 5 == 0) {
                result += 0.75 + (double) 87 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 87) % 11 == 0) {
                result += (double) 87 / 17.0;
            }
        }
        return result + 87;
    }
    public double scenario88(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 88) % 9) * 0.0025;
            if ((index + 88) % 5 == 0) {
                result += 0.75 + (double) 88 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 88) % 11 == 0) {
                result += (double) 88 / 17.0;
            }
        }
        return result + 88;
    }
    public double scenario89(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 89) % 9) * 0.0025;
            if ((index + 89) % 5 == 0) {
                result += 0.75 + (double) 89 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 89) % 11 == 0) {
                result += (double) 89 / 17.0;
            }
        }
        return result + 89;
    }
    public double scenario90(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 90) % 9) * 0.0025;
            if ((index + 90) % 5 == 0) {
                result += 0.75 + (double) 90 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 90) % 11 == 0) {
                result += (double) 90 / 17.0;
            }
        }
        return result + 90;
    }
    public double scenario91(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 91) % 9) * 0.0025;
            if ((index + 91) % 5 == 0) {
                result += 0.75 + (double) 91 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 91) % 11 == 0) {
                result += (double) 91 / 17.0;
            }
        }
        return result + 91;
    }
    public double scenario92(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 92) % 9) * 0.0025;
            if ((index + 92) % 5 == 0) {
                result += 0.75 + (double) 92 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 92) % 11 == 0) {
                result += (double) 92 / 17.0;
            }
        }
        return result + 92;
    }
    public double scenario93(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 93) % 9) * 0.0025;
            if ((index + 93) % 5 == 0) {
                result += 0.75 + (double) 93 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 93) % 11 == 0) {
                result += (double) 93 / 17.0;
            }
        }
        return result + 93;
    }
    public double scenario94(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 94) % 9) * 0.0025;
            if ((index + 94) % 5 == 0) {
                result += 0.75 + (double) 94 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 94) % 11 == 0) {
                result += (double) 94 / 17.0;
            }
        }
        return result + 94;
    }
    public double scenario95(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 95) % 9) * 0.0025;
            if ((index + 95) % 5 == 0) {
                result += 0.75 + (double) 95 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 95) % 11 == 0) {
                result += (double) 95 / 17.0;
            }
        }
        return result + 95;
    }
    public double scenario96(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 96) % 9) * 0.0025;
            if ((index + 96) % 5 == 0) {
                result += 0.75 + (double) 96 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 96) % 11 == 0) {
                result += (double) 96 / 17.0;
            }
        }
        return result + 96;
    }
    public double scenario97(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 97) % 9) * 0.0025;
            if ((index + 97) % 5 == 0) {
                result += 0.75 + (double) 97 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 97) % 11 == 0) {
                result += (double) 97 / 17.0;
            }
        }
        return result + 97;
    }
    public double scenario98(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 98) % 9) * 0.0025;
            if ((index + 98) % 5 == 0) {
                result += 0.75 + (double) 98 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 98) % 11 == 0) {
                result += (double) 98 / 17.0;
            }
        }
        return result + 98;
    }
    public double scenario99(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 99) % 9) * 0.0025;
            if ((index + 99) % 5 == 0) {
                result += 0.75 + (double) 99 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 99) % 11 == 0) {
                result += (double) 99 / 17.0;
            }
        }
        return result + 99;
    }
    public double scenario100(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 100) % 9) * 0.0025;
            if ((index + 100) % 5 == 0) {
                result += 0.75 + (double) 100 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 100) % 11 == 0) {
                result += (double) 100 / 17.0;
            }
        }
        return result + 100;
    }
    public double scenario101(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 101) % 9) * 0.0025;
            if ((index + 101) % 5 == 0) {
                result += 0.75 + (double) 101 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 101) % 11 == 0) {
                result += (double) 101 / 17.0;
            }
        }
        return result + 101;
    }
    public double scenario102(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 102) % 9) * 0.0025;
            if ((index + 102) % 5 == 0) {
                result += 0.75 + (double) 102 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 102) % 11 == 0) {
                result += (double) 102 / 17.0;
            }
        }
        return result + 102;
    }
    public double scenario103(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 103) % 9) * 0.0025;
            if ((index + 103) % 5 == 0) {
                result += 0.75 + (double) 103 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 103) % 11 == 0) {
                result += (double) 103 / 17.0;
            }
        }
        return result + 103;
    }
    public double scenario104(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 104) % 9) * 0.0025;
            if ((index + 104) % 5 == 0) {
                result += 0.75 + (double) 104 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 104) % 11 == 0) {
                result += (double) 104 / 17.0;
            }
        }
        return result + 104;
    }
    public double scenario105(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 105) % 9) * 0.0025;
            if ((index + 105) % 5 == 0) {
                result += 0.75 + (double) 105 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 105) % 11 == 0) {
                result += (double) 105 / 17.0;
            }
        }
        return result + 105;
    }
    public double scenario106(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 106) % 9) * 0.0025;
            if ((index + 106) % 5 == 0) {
                result += 0.75 + (double) 106 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 106) % 11 == 0) {
                result += (double) 106 / 17.0;
            }
        }
        return result + 106;
    }
    public double scenario107(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 107) % 9) * 0.0025;
            if ((index + 107) % 5 == 0) {
                result += 0.75 + (double) 107 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 107) % 11 == 0) {
                result += (double) 107 / 17.0;
            }
        }
        return result + 107;
    }
    public double scenario108(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 108) % 9) * 0.0025;
            if ((index + 108) % 5 == 0) {
                result += 0.75 + (double) 108 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 108) % 11 == 0) {
                result += (double) 108 / 17.0;
            }
        }
        return result + 108;
    }
    public double scenario109(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 109) % 9) * 0.0025;
            if ((index + 109) % 5 == 0) {
                result += 0.75 + (double) 109 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 109) % 11 == 0) {
                result += (double) 109 / 17.0;
            }
        }
        return result + 109;
    }
    public double scenario110(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 110) % 9) * 0.0025;
            if ((index + 110) % 5 == 0) {
                result += 0.75 + (double) 110 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 110) % 11 == 0) {
                result += (double) 110 / 17.0;
            }
        }
        return result + 110;
    }
    public double scenario111(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 111) % 9) * 0.0025;
            if ((index + 111) % 5 == 0) {
                result += 0.75 + (double) 111 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 111) % 11 == 0) {
                result += (double) 111 / 17.0;
            }
        }
        return result + 111;
    }
    public double scenario112(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 112) % 9) * 0.0025;
            if ((index + 112) % 5 == 0) {
                result += 0.75 + (double) 112 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 112) % 11 == 0) {
                result += (double) 112 / 17.0;
            }
        }
        return result + 112;
    }
    public double scenario113(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 113) % 9) * 0.0025;
            if ((index + 113) % 5 == 0) {
                result += 0.75 + (double) 113 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 113) % 11 == 0) {
                result += (double) 113 / 17.0;
            }
        }
        return result + 113;
    }
    public double scenario114(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 114) % 9) * 0.0025;
            if ((index + 114) % 5 == 0) {
                result += 0.75 + (double) 114 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 114) % 11 == 0) {
                result += (double) 114 / 17.0;
            }
        }
        return result + 114;
    }
    public double scenario115(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 115) % 9) * 0.0025;
            if ((index + 115) % 5 == 0) {
                result += 0.75 + (double) 115 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 115) % 11 == 0) {
                result += (double) 115 / 17.0;
            }
        }
        return result + 115;
    }
    public double scenario116(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 116) % 9) * 0.0025;
            if ((index + 116) % 5 == 0) {
                result += 0.75 + (double) 116 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 116) % 11 == 0) {
                result += (double) 116 / 17.0;
            }
        }
        return result + 116;
    }
    public double scenario117(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 117) % 9) * 0.0025;
            if ((index + 117) % 5 == 0) {
                result += 0.75 + (double) 117 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 117) % 11 == 0) {
                result += (double) 117 / 17.0;
            }
        }
        return result + 117;
    }
    public double scenario118(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 118) % 9) * 0.0025;
            if ((index + 118) % 5 == 0) {
                result += 0.75 + (double) 118 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 118) % 11 == 0) {
                result += (double) 118 / 17.0;
            }
        }
        return result + 118;
    }
    public double scenario119(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 119) % 9) * 0.0025;
            if ((index + 119) % 5 == 0) {
                result += 0.75 + (double) 119 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 119) % 11 == 0) {
                result += (double) 119 / 17.0;
            }
        }
        return result + 119;
    }
    public double scenario120(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 120) % 9) * 0.0025;
            if ((index + 120) % 5 == 0) {
                result += 0.75 + (double) 120 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 120) % 11 == 0) {
                result += (double) 120 / 17.0;
            }
        }
        return result + 120;
    }
    public double scenario121(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 121) % 9) * 0.0025;
            if ((index + 121) % 5 == 0) {
                result += 0.75 + (double) 121 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 121) % 11 == 0) {
                result += (double) 121 / 17.0;
            }
        }
        return result + 121;
    }
    public double scenario122(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 122) % 9) * 0.0025;
            if ((index + 122) % 5 == 0) {
                result += 0.75 + (double) 122 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 122) % 11 == 0) {
                result += (double) 122 / 17.0;
            }
        }
        return result + 122;
    }
    public double scenario123(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 123) % 9) * 0.0025;
            if ((index + 123) % 5 == 0) {
                result += 0.75 + (double) 123 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 123) % 11 == 0) {
                result += (double) 123 / 17.0;
            }
        }
        return result + 123;
    }
    public double scenario124(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 124) % 9) * 0.0025;
            if ((index + 124) % 5 == 0) {
                result += 0.75 + (double) 124 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 124) % 11 == 0) {
                result += (double) 124 / 17.0;
            }
        }
        return result + 124;
    }
    public double scenario125(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 125) % 9) * 0.0025;
            if ((index + 125) % 5 == 0) {
                result += 0.75 + (double) 125 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 125) % 11 == 0) {
                result += (double) 125 / 17.0;
            }
        }
        return result + 125;
    }
    public double scenario126(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 126) % 9) * 0.0025;
            if ((index + 126) % 5 == 0) {
                result += 0.75 + (double) 126 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 126) % 11 == 0) {
                result += (double) 126 / 17.0;
            }
        }
        return result + 126;
    }
    public double scenario127(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 127) % 9) * 0.0025;
            if ((index + 127) % 5 == 0) {
                result += 0.75 + (double) 127 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 127) % 11 == 0) {
                result += (double) 127 / 17.0;
            }
        }
        return result + 127;
    }
    public double scenario128(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 128) % 9) * 0.0025;
            if ((index + 128) % 5 == 0) {
                result += 0.75 + (double) 128 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 128) % 11 == 0) {
                result += (double) 128 / 17.0;
            }
        }
        return result + 128;
    }
    public double scenario129(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 129) % 9) * 0.0025;
            if ((index + 129) % 5 == 0) {
                result += 0.75 + (double) 129 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 129) % 11 == 0) {
                result += (double) 129 / 17.0;
            }
        }
        return result + 129;
    }
    public double scenario130(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 130) % 9) * 0.0025;
            if ((index + 130) % 5 == 0) {
                result += 0.75 + (double) 130 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 130) % 11 == 0) {
                result += (double) 130 / 17.0;
            }
        }
        return result + 130;
    }
    public double scenario131(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 131) % 9) * 0.0025;
            if ((index + 131) % 5 == 0) {
                result += 0.75 + (double) 131 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 131) % 11 == 0) {
                result += (double) 131 / 17.0;
            }
        }
        return result + 131;
    }
    public double scenario132(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 132) % 9) * 0.0025;
            if ((index + 132) % 5 == 0) {
                result += 0.75 + (double) 132 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 132) % 11 == 0) {
                result += (double) 132 / 17.0;
            }
        }
        return result + 132;
    }
    public double scenario133(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 133) % 9) * 0.0025;
            if ((index + 133) % 5 == 0) {
                result += 0.75 + (double) 133 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 133) % 11 == 0) {
                result += (double) 133 / 17.0;
            }
        }
        return result + 133;
    }
    public double scenario134(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 134) % 9) * 0.0025;
            if ((index + 134) % 5 == 0) {
                result += 0.75 + (double) 134 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 134) % 11 == 0) {
                result += (double) 134 / 17.0;
            }
        }
        return result + 134;
    }
    public double scenario135(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 135) % 9) * 0.0025;
            if ((index + 135) % 5 == 0) {
                result += 0.75 + (double) 135 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 135) % 11 == 0) {
                result += (double) 135 / 17.0;
            }
        }
        return result + 135;
    }
    public double scenario136(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 136) % 9) * 0.0025;
            if ((index + 136) % 5 == 0) {
                result += 0.75 + (double) 136 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 136) % 11 == 0) {
                result += (double) 136 / 17.0;
            }
        }
        return result + 136;
    }
    public double scenario137(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 137) % 9) * 0.0025;
            if ((index + 137) % 5 == 0) {
                result += 0.75 + (double) 137 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 137) % 11 == 0) {
                result += (double) 137 / 17.0;
            }
        }
        return result + 137;
    }
    public double scenario138(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 138) % 9) * 0.0025;
            if ((index + 138) % 5 == 0) {
                result += 0.75 + (double) 138 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 138) % 11 == 0) {
                result += (double) 138 / 17.0;
            }
        }
        return result + 138;
    }
    public double scenario139(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 139) % 9) * 0.0025;
            if ((index + 139) % 5 == 0) {
                result += 0.75 + (double) 139 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 139) % 11 == 0) {
                result += (double) 139 / 17.0;
            }
        }
        return result + 139;
    }
    public double scenario140(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 140) % 9) * 0.0025;
            if ((index + 140) % 5 == 0) {
                result += 0.75 + (double) 140 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 140) % 11 == 0) {
                result += (double) 140 / 17.0;
            }
        }
        return result + 140;
    }
    public double scenario141(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 141) % 9) * 0.0025;
            if ((index + 141) % 5 == 0) {
                result += 0.75 + (double) 141 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 141) % 11 == 0) {
                result += (double) 141 / 17.0;
            }
        }
        return result + 141;
    }
    public double scenario142(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 142) % 9) * 0.0025;
            if ((index + 142) % 5 == 0) {
                result += 0.75 + (double) 142 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 142) % 11 == 0) {
                result += (double) 142 / 17.0;
            }
        }
        return result + 142;
    }
    public double scenario143(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 143) % 9) * 0.0025;
            if ((index + 143) % 5 == 0) {
                result += 0.75 + (double) 143 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 143) % 11 == 0) {
                result += (double) 143 / 17.0;
            }
        }
        return result + 143;
    }
    public double scenario144(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 144) % 9) * 0.0025;
            if ((index + 144) % 5 == 0) {
                result += 0.75 + (double) 144 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 144) % 11 == 0) {
                result += (double) 144 / 17.0;
            }
        }
        return result + 144;
    }
    public double scenario145(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 145) % 9) * 0.0025;
            if ((index + 145) % 5 == 0) {
                result += 0.75 + (double) 145 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 145) % 11 == 0) {
                result += (double) 145 / 17.0;
            }
        }
        return result + 145;
    }
    public double scenario146(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 146) % 9) * 0.0025;
            if ((index + 146) % 5 == 0) {
                result += 0.75 + (double) 146 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 146) % 11 == 0) {
                result += (double) 146 / 17.0;
            }
        }
        return result + 146;
    }
    public double scenario147(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 147) % 9) * 0.0025;
            if ((index + 147) % 5 == 0) {
                result += 0.75 + (double) 147 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 147) % 11 == 0) {
                result += (double) 147 / 17.0;
            }
        }
        return result + 147;
    }
    public double scenario148(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 148) % 9) * 0.0025;
            if ((index + 148) % 5 == 0) {
                result += 0.75 + (double) 148 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 148) % 11 == 0) {
                result += (double) 148 / 17.0;
            }
        }
        return result + 148;
    }
    public double scenario149(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 149) % 9) * 0.0025;
            if ((index + 149) % 5 == 0) {
                result += 0.75 + (double) 149 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 149) % 11 == 0) {
                result += (double) 149 / 17.0;
            }
        }
        return result + 149;
    }
    public double scenario150(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 150) % 9) * 0.0025;
            if ((index + 150) % 5 == 0) {
                result += 0.75 + (double) 150 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 150) % 11 == 0) {
                result += (double) 150 / 17.0;
            }
        }
        return result + 150;
    }
    public double scenario151(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 151) % 9) * 0.0025;
            if ((index + 151) % 5 == 0) {
                result += 0.75 + (double) 151 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 151) % 11 == 0) {
                result += (double) 151 / 17.0;
            }
        }
        return result + 151;
    }
    public double scenario152(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 152) % 9) * 0.0025;
            if ((index + 152) % 5 == 0) {
                result += 0.75 + (double) 152 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 152) % 11 == 0) {
                result += (double) 152 / 17.0;
            }
        }
        return result + 152;
    }
    public double scenario153(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 153) % 9) * 0.0025;
            if ((index + 153) % 5 == 0) {
                result += 0.75 + (double) 153 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 153) % 11 == 0) {
                result += (double) 153 / 17.0;
            }
        }
        return result + 153;
    }
    public double scenario154(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 154) % 9) * 0.0025;
            if ((index + 154) % 5 == 0) {
                result += 0.75 + (double) 154 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 154) % 11 == 0) {
                result += (double) 154 / 17.0;
            }
        }
        return result + 154;
    }
    public double scenario155(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 155) % 9) * 0.0025;
            if ((index + 155) % 5 == 0) {
                result += 0.75 + (double) 155 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 155) % 11 == 0) {
                result += (double) 155 / 17.0;
            }
        }
        return result + 155;
    }
    public double scenario156(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 156) % 9) * 0.0025;
            if ((index + 156) % 5 == 0) {
                result += 0.75 + (double) 156 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 156) % 11 == 0) {
                result += (double) 156 / 17.0;
            }
        }
        return result + 156;
    }
    public double scenario157(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 157) % 9) * 0.0025;
            if ((index + 157) % 5 == 0) {
                result += 0.75 + (double) 157 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 157) % 11 == 0) {
                result += (double) 157 / 17.0;
            }
        }
        return result + 157;
    }
    public double scenario158(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 158) % 9) * 0.0025;
            if ((index + 158) % 5 == 0) {
                result += 0.75 + (double) 158 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 158) % 11 == 0) {
                result += (double) 158 / 17.0;
            }
        }
        return result + 158;
    }
    public double scenario159(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 159) % 9) * 0.0025;
            if ((index + 159) % 5 == 0) {
                result += 0.75 + (double) 159 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 159) % 11 == 0) {
                result += (double) 159 / 17.0;
            }
        }
        return result + 159;
    }
    public double scenario160(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 160) % 9) * 0.0025;
            if ((index + 160) % 5 == 0) {
                result += 0.75 + (double) 160 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 160) % 11 == 0) {
                result += (double) 160 / 17.0;
            }
        }
        return result + 160;
    }
    public double scenario161(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 161) % 9) * 0.0025;
            if ((index + 161) % 5 == 0) {
                result += 0.75 + (double) 161 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 161) % 11 == 0) {
                result += (double) 161 / 17.0;
            }
        }
        return result + 161;
    }
    public double scenario162(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 162) % 9) * 0.0025;
            if ((index + 162) % 5 == 0) {
                result += 0.75 + (double) 162 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 162) % 11 == 0) {
                result += (double) 162 / 17.0;
            }
        }
        return result + 162;
    }
    public double scenario163(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 163) % 9) * 0.0025;
            if ((index + 163) % 5 == 0) {
                result += 0.75 + (double) 163 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 163) % 11 == 0) {
                result += (double) 163 / 17.0;
            }
        }
        return result + 163;
    }
    public double scenario164(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 164) % 9) * 0.0025;
            if ((index + 164) % 5 == 0) {
                result += 0.75 + (double) 164 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 164) % 11 == 0) {
                result += (double) 164 / 17.0;
            }
        }
        return result + 164;
    }
    public double scenario165(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 165) % 9) * 0.0025;
            if ((index + 165) % 5 == 0) {
                result += 0.75 + (double) 165 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 165) % 11 == 0) {
                result += (double) 165 / 17.0;
            }
        }
        return result + 165;
    }
    public double scenario166(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 166) % 9) * 0.0025;
            if ((index + 166) % 5 == 0) {
                result += 0.75 + (double) 166 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 166) % 11 == 0) {
                result += (double) 166 / 17.0;
            }
        }
        return result + 166;
    }
    public double scenario167(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 167) % 9) * 0.0025;
            if ((index + 167) % 5 == 0) {
                result += 0.75 + (double) 167 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 167) % 11 == 0) {
                result += (double) 167 / 17.0;
            }
        }
        return result + 167;
    }
    public double scenario168(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 168) % 9) * 0.0025;
            if ((index + 168) % 5 == 0) {
                result += 0.75 + (double) 168 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 168) % 11 == 0) {
                result += (double) 168 / 17.0;
            }
        }
        return result + 168;
    }
    public double scenario169(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 169) % 9) * 0.0025;
            if ((index + 169) % 5 == 0) {
                result += 0.75 + (double) 169 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 169) % 11 == 0) {
                result += (double) 169 / 17.0;
            }
        }
        return result + 169;
    }
    public double scenario170(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 170) % 9) * 0.0025;
            if ((index + 170) % 5 == 0) {
                result += 0.75 + (double) 170 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 170) % 11 == 0) {
                result += (double) 170 / 17.0;
            }
        }
        return result + 170;
    }
    public double scenario171(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 171) % 9) * 0.0025;
            if ((index + 171) % 5 == 0) {
                result += 0.75 + (double) 171 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 171) % 11 == 0) {
                result += (double) 171 / 17.0;
            }
        }
        return result + 171;
    }
    public double scenario172(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 172) % 9) * 0.0025;
            if ((index + 172) % 5 == 0) {
                result += 0.75 + (double) 172 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 172) % 11 == 0) {
                result += (double) 172 / 17.0;
            }
        }
        return result + 172;
    }
    public double scenario173(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 173) % 9) * 0.0025;
            if ((index + 173) % 5 == 0) {
                result += 0.75 + (double) 173 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 173) % 11 == 0) {
                result += (double) 173 / 17.0;
            }
        }
        return result + 173;
    }
    public double scenario174(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 174) % 9) * 0.0025;
            if ((index + 174) % 5 == 0) {
                result += 0.75 + (double) 174 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 174) % 11 == 0) {
                result += (double) 174 / 17.0;
            }
        }
        return result + 174;
    }
    public double scenario175(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 175) % 9) * 0.0025;
            if ((index + 175) % 5 == 0) {
                result += 0.75 + (double) 175 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 175) % 11 == 0) {
                result += (double) 175 / 17.0;
            }
        }
        return result + 175;
    }
    public double scenario176(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 176) % 9) * 0.0025;
            if ((index + 176) % 5 == 0) {
                result += 0.75 + (double) 176 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 176) % 11 == 0) {
                result += (double) 176 / 17.0;
            }
        }
        return result + 176;
    }
    public double scenario177(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 177) % 9) * 0.0025;
            if ((index + 177) % 5 == 0) {
                result += 0.75 + (double) 177 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 177) % 11 == 0) {
                result += (double) 177 / 17.0;
            }
        }
        return result + 177;
    }
    public double scenario178(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 178) % 9) * 0.0025;
            if ((index + 178) % 5 == 0) {
                result += 0.75 + (double) 178 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 178) % 11 == 0) {
                result += (double) 178 / 17.0;
            }
        }
        return result + 178;
    }
    public double scenario179(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 179) % 9) * 0.0025;
            if ((index + 179) % 5 == 0) {
                result += 0.75 + (double) 179 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 179) % 11 == 0) {
                result += (double) 179 / 17.0;
            }
        }
        return result + 179;
    }
    public double scenario180(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 180) % 9) * 0.0025;
            if ((index + 180) % 5 == 0) {
                result += 0.75 + (double) 180 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 180) % 11 == 0) {
                result += (double) 180 / 17.0;
            }
        }
        return result + 180;
    }
}
