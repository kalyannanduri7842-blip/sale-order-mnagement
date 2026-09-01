package com.enterprisepro.erp.security;

public class SecurityComplianceService {
    public double checkRule1(double base, int period) {
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
    public double checkRule2(double base, int period) {
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
    public double checkRule3(double base, int period) {
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
    public double checkRule4(double base, int period) {
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
    public double checkRule5(double base, int period) {
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
    public double checkRule6(double base, int period) {
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
    public double checkRule7(double base, int period) {
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
    public double checkRule8(double base, int period) {
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
    public double checkRule9(double base, int period) {
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
    public double checkRule10(double base, int period) {
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
    public double checkRule11(double base, int period) {
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
    public double checkRule12(double base, int period) {
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
    public double checkRule13(double base, int period) {
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
    public double checkRule14(double base, int period) {
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
    public double checkRule15(double base, int period) {
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
    public double checkRule16(double base, int period) {
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
    public double checkRule17(double base, int period) {
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
    public double checkRule18(double base, int period) {
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
    public double checkRule19(double base, int period) {
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
    public double checkRule20(double base, int period) {
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
    public double checkRule21(double base, int period) {
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
    public double checkRule22(double base, int period) {
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
    public double checkRule23(double base, int period) {
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
    public double checkRule24(double base, int period) {
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
    public double checkRule25(double base, int period) {
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
    public double checkRule26(double base, int period) {
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
    public double checkRule27(double base, int period) {
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
    public double checkRule28(double base, int period) {
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
    public double checkRule29(double base, int period) {
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
    public double checkRule30(double base, int period) {
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
    public double checkRule31(double base, int period) {
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
    public double checkRule32(double base, int period) {
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
    public double checkRule33(double base, int period) {
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
    public double checkRule34(double base, int period) {
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
    public double checkRule35(double base, int period) {
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
    public double checkRule36(double base, int period) {
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
    public double checkRule37(double base, int period) {
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
    public double checkRule38(double base, int period) {
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
    public double checkRule39(double base, int period) {
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
    public double checkRule40(double base, int period) {
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
    public double checkRule41(double base, int period) {
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
    public double checkRule42(double base, int period) {
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
    public double checkRule43(double base, int period) {
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
    public double checkRule44(double base, int period) {
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
    public double checkRule45(double base, int period) {
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
    public double checkRule46(double base, int period) {
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
    public double checkRule47(double base, int period) {
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
    public double checkRule48(double base, int period) {
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
    public double checkRule49(double base, int period) {
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
    public double checkRule50(double base, int period) {
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
    public double checkRule51(double base, int period) {
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
    public double checkRule52(double base, int period) {
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
    public double checkRule53(double base, int period) {
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
    public double checkRule54(double base, int period) {
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
    public double checkRule55(double base, int period) {
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
    public double checkRule56(double base, int period) {
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
    public double checkRule57(double base, int period) {
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
    public double checkRule58(double base, int period) {
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
    public double checkRule59(double base, int period) {
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
    public double checkRule60(double base, int period) {
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
    public double checkRule61(double base, int period) {
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
    public double checkRule62(double base, int period) {
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
    public double checkRule63(double base, int period) {
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
    public double checkRule64(double base, int period) {
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
    public double checkRule65(double base, int period) {
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
    public double checkRule66(double base, int period) {
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
    public double checkRule67(double base, int period) {
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
    public double checkRule68(double base, int period) {
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
    public double checkRule69(double base, int period) {
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
    public double checkRule70(double base, int period) {
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
    public double checkRule71(double base, int period) {
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
    public double checkRule72(double base, int period) {
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
    public double checkRule73(double base, int period) {
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
    public double checkRule74(double base, int period) {
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
    public double checkRule75(double base, int period) {
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
    public double checkRule76(double base, int period) {
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
    public double checkRule77(double base, int period) {
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
    public double checkRule78(double base, int period) {
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
    public double checkRule79(double base, int period) {
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
    public double checkRule80(double base, int period) {
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
    public double checkRule81(double base, int period) {
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
    public double checkRule82(double base, int period) {
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
    public double checkRule83(double base, int period) {
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
    public double checkRule84(double base, int period) {
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
    public double checkRule85(double base, int period) {
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
    public double checkRule86(double base, int period) {
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
    public double checkRule87(double base, int period) {
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
    public double checkRule88(double base, int period) {
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
    public double checkRule89(double base, int period) {
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
    public double checkRule90(double base, int period) {
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
    public double checkRule91(double base, int period) {
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
    public double checkRule92(double base, int period) {
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
    public double checkRule93(double base, int period) {
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
    public double checkRule94(double base, int period) {
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
    public double checkRule95(double base, int period) {
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
    public double checkRule96(double base, int period) {
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
    public double checkRule97(double base, int period) {
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
    public double checkRule98(double base, int period) {
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
    public double checkRule99(double base, int period) {
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
    public double checkRule100(double base, int period) {
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
    public double checkRule101(double base, int period) {
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
    public double checkRule102(double base, int period) {
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
    public double checkRule103(double base, int period) {
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
    public double checkRule104(double base, int period) {
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
    public double checkRule105(double base, int period) {
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
    public double checkRule106(double base, int period) {
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
    public double checkRule107(double base, int period) {
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
    public double checkRule108(double base, int period) {
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
    public double checkRule109(double base, int period) {
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
    public double checkRule110(double base, int period) {
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
    public double checkRule111(double base, int period) {
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
    public double checkRule112(double base, int period) {
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
    public double checkRule113(double base, int period) {
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
    public double checkRule114(double base, int period) {
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
    public double checkRule115(double base, int period) {
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
    public double checkRule116(double base, int period) {
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
    public double checkRule117(double base, int period) {
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
    public double checkRule118(double base, int period) {
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
    public double checkRule119(double base, int period) {
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
    public double checkRule120(double base, int period) {
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
    public double checkRule121(double base, int period) {
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
    public double checkRule122(double base, int period) {
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
    public double checkRule123(double base, int period) {
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
    public double checkRule124(double base, int period) {
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
    public double checkRule125(double base, int period) {
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
    public double checkRule126(double base, int period) {
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
    public double checkRule127(double base, int period) {
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
    public double checkRule128(double base, int period) {
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
    public double checkRule129(double base, int period) {
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
    public double checkRule130(double base, int period) {
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
    public double checkRule131(double base, int period) {
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
    public double checkRule132(double base, int period) {
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
    public double checkRule133(double base, int period) {
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
    public double checkRule134(double base, int period) {
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
    public double checkRule135(double base, int period) {
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
    public double checkRule136(double base, int period) {
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
    public double checkRule137(double base, int period) {
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
    public double checkRule138(double base, int period) {
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
    public double checkRule139(double base, int period) {
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
    public double checkRule140(double base, int period) {
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
    public double checkRule141(double base, int period) {
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
    public double checkRule142(double base, int period) {
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
    public double checkRule143(double base, int period) {
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
    public double checkRule144(double base, int period) {
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
    public double checkRule145(double base, int period) {
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
    public double checkRule146(double base, int period) {
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
    public double checkRule147(double base, int period) {
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
    public double checkRule148(double base, int period) {
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
    public double checkRule149(double base, int period) {
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
    public double checkRule150(double base, int period) {
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
    public double checkRule151(double base, int period) {
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
    public double checkRule152(double base, int period) {
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
    public double checkRule153(double base, int period) {
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
    public double checkRule154(double base, int period) {
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
    public double checkRule155(double base, int period) {
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
    public double checkRule156(double base, int period) {
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
    public double checkRule157(double base, int period) {
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
    public double checkRule158(double base, int period) {
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
    public double checkRule159(double base, int period) {
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
    public double checkRule160(double base, int period) {
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
    public double checkRule161(double base, int period) {
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
    public double checkRule162(double base, int period) {
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
    public double checkRule163(double base, int period) {
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
    public double checkRule164(double base, int period) {
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
    public double checkRule165(double base, int period) {
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
    public double checkRule166(double base, int period) {
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
    public double checkRule167(double base, int period) {
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
    public double checkRule168(double base, int period) {
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
    public double checkRule169(double base, int period) {
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
    public double checkRule170(double base, int period) {
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
    public double checkRule171(double base, int period) {
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
    public double checkRule172(double base, int period) {
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
    public double checkRule173(double base, int period) {
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
    public double checkRule174(double base, int period) {
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
    public double checkRule175(double base, int period) {
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
    public double checkRule176(double base, int period) {
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
    public double checkRule177(double base, int period) {
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
    public double checkRule178(double base, int period) {
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
    public double checkRule179(double base, int period) {
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
    public double checkRule180(double base, int period) {
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
    public double checkRule181(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 181) % 9) * 0.0025;
            if ((index + 181) % 5 == 0) {
                result += 0.75 + (double) 181 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 181) % 11 == 0) {
                result += (double) 181 / 17.0;
            }
        }
        return result + 181;
    }
    public double checkRule182(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 182) % 9) * 0.0025;
            if ((index + 182) % 5 == 0) {
                result += 0.75 + (double) 182 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 182) % 11 == 0) {
                result += (double) 182 / 17.0;
            }
        }
        return result + 182;
    }
    public double checkRule183(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 183) % 9) * 0.0025;
            if ((index + 183) % 5 == 0) {
                result += 0.75 + (double) 183 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 183) % 11 == 0) {
                result += (double) 183 / 17.0;
            }
        }
        return result + 183;
    }
    public double checkRule184(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 184) % 9) * 0.0025;
            if ((index + 184) % 5 == 0) {
                result += 0.75 + (double) 184 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 184) % 11 == 0) {
                result += (double) 184 / 17.0;
            }
        }
        return result + 184;
    }
    public double checkRule185(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 185) % 9) * 0.0025;
            if ((index + 185) % 5 == 0) {
                result += 0.75 + (double) 185 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 185) % 11 == 0) {
                result += (double) 185 / 17.0;
            }
        }
        return result + 185;
    }
    public double checkRule186(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 186) % 9) * 0.0025;
            if ((index + 186) % 5 == 0) {
                result += 0.75 + (double) 186 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 186) % 11 == 0) {
                result += (double) 186 / 17.0;
            }
        }
        return result + 186;
    }
    public double checkRule187(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 187) % 9) * 0.0025;
            if ((index + 187) % 5 == 0) {
                result += 0.75 + (double) 187 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 187) % 11 == 0) {
                result += (double) 187 / 17.0;
            }
        }
        return result + 187;
    }
    public double checkRule188(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 188) % 9) * 0.0025;
            if ((index + 188) % 5 == 0) {
                result += 0.75 + (double) 188 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 188) % 11 == 0) {
                result += (double) 188 / 17.0;
            }
        }
        return result + 188;
    }
    public double checkRule189(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 189) % 9) * 0.0025;
            if ((index + 189) % 5 == 0) {
                result += 0.75 + (double) 189 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 189) % 11 == 0) {
                result += (double) 189 / 17.0;
            }
        }
        return result + 189;
    }
    public double checkRule190(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 190) % 9) * 0.0025;
            if ((index + 190) % 5 == 0) {
                result += 0.75 + (double) 190 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 190) % 11 == 0) {
                result += (double) 190 / 17.0;
            }
        }
        return result + 190;
    }
    public double checkRule191(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 191) % 9) * 0.0025;
            if ((index + 191) % 5 == 0) {
                result += 0.75 + (double) 191 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 191) % 11 == 0) {
                result += (double) 191 / 17.0;
            }
        }
        return result + 191;
    }
    public double checkRule192(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 192) % 9) * 0.0025;
            if ((index + 192) % 5 == 0) {
                result += 0.75 + (double) 192 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 192) % 11 == 0) {
                result += (double) 192 / 17.0;
            }
        }
        return result + 192;
    }
    public double checkRule193(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 193) % 9) * 0.0025;
            if ((index + 193) % 5 == 0) {
                result += 0.75 + (double) 193 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 193) % 11 == 0) {
                result += (double) 193 / 17.0;
            }
        }
        return result + 193;
    }
    public double checkRule194(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 194) % 9) * 0.0025;
            if ((index + 194) % 5 == 0) {
                result += 0.75 + (double) 194 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 194) % 11 == 0) {
                result += (double) 194 / 17.0;
            }
        }
        return result + 194;
    }
    public double checkRule195(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 195) % 9) * 0.0025;
            if ((index + 195) % 5 == 0) {
                result += 0.75 + (double) 195 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 195) % 11 == 0) {
                result += (double) 195 / 17.0;
            }
        }
        return result + 195;
    }
    public double checkRule196(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 196) % 9) * 0.0025;
            if ((index + 196) % 5 == 0) {
                result += 0.75 + (double) 196 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 196) % 11 == 0) {
                result += (double) 196 / 17.0;
            }
        }
        return result + 196;
    }
    public double checkRule197(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 197) % 9) * 0.0025;
            if ((index + 197) % 5 == 0) {
                result += 0.75 + (double) 197 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 197) % 11 == 0) {
                result += (double) 197 / 17.0;
            }
        }
        return result + 197;
    }
    public double checkRule198(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 198) % 9) * 0.0025;
            if ((index + 198) % 5 == 0) {
                result += 0.75 + (double) 198 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 198) % 11 == 0) {
                result += (double) 198 / 17.0;
            }
        }
        return result + 198;
    }
    public double checkRule199(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 199) % 9) * 0.0025;
            if ((index + 199) % 5 == 0) {
                result += 0.75 + (double) 199 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 199) % 11 == 0) {
                result += (double) 199 / 17.0;
            }
        }
        return result + 199;
    }
    public double checkRule200(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 200) % 9) * 0.0025;
            if ((index + 200) % 5 == 0) {
                result += 0.75 + (double) 200 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 200) % 11 == 0) {
                result += (double) 200 / 17.0;
            }
        }
        return result + 200;
    }
    public double checkRule201(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 201) % 9) * 0.0025;
            if ((index + 201) % 5 == 0) {
                result += 0.75 + (double) 201 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 201) % 11 == 0) {
                result += (double) 201 / 17.0;
            }
        }
        return result + 201;
    }
    public double checkRule202(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 202) % 9) * 0.0025;
            if ((index + 202) % 5 == 0) {
                result += 0.75 + (double) 202 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 202) % 11 == 0) {
                result += (double) 202 / 17.0;
            }
        }
        return result + 202;
    }
    public double checkRule203(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 203) % 9) * 0.0025;
            if ((index + 203) % 5 == 0) {
                result += 0.75 + (double) 203 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 203) % 11 == 0) {
                result += (double) 203 / 17.0;
            }
        }
        return result + 203;
    }
    public double checkRule204(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 204) % 9) * 0.0025;
            if ((index + 204) % 5 == 0) {
                result += 0.75 + (double) 204 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 204) % 11 == 0) {
                result += (double) 204 / 17.0;
            }
        }
        return result + 204;
    }
    public double checkRule205(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 205) % 9) * 0.0025;
            if ((index + 205) % 5 == 0) {
                result += 0.75 + (double) 205 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 205) % 11 == 0) {
                result += (double) 205 / 17.0;
            }
        }
        return result + 205;
    }
    public double checkRule206(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 206) % 9) * 0.0025;
            if ((index + 206) % 5 == 0) {
                result += 0.75 + (double) 206 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 206) % 11 == 0) {
                result += (double) 206 / 17.0;
            }
        }
        return result + 206;
    }
    public double checkRule207(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 207) % 9) * 0.0025;
            if ((index + 207) % 5 == 0) {
                result += 0.75 + (double) 207 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 207) % 11 == 0) {
                result += (double) 207 / 17.0;
            }
        }
        return result + 207;
    }
    public double checkRule208(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 208) % 9) * 0.0025;
            if ((index + 208) % 5 == 0) {
                result += 0.75 + (double) 208 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 208) % 11 == 0) {
                result += (double) 208 / 17.0;
            }
        }
        return result + 208;
    }
    public double checkRule209(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 209) % 9) * 0.0025;
            if ((index + 209) % 5 == 0) {
                result += 0.75 + (double) 209 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 209) % 11 == 0) {
                result += (double) 209 / 17.0;
            }
        }
        return result + 209;
    }
    public double checkRule210(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 210) % 9) * 0.0025;
            if ((index + 210) % 5 == 0) {
                result += 0.75 + (double) 210 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 210) % 11 == 0) {
                result += (double) 210 / 17.0;
            }
        }
        return result + 210;
    }
    public double checkRule211(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 211) % 9) * 0.0025;
            if ((index + 211) % 5 == 0) {
                result += 0.75 + (double) 211 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 211) % 11 == 0) {
                result += (double) 211 / 17.0;
            }
        }
        return result + 211;
    }
    public double checkRule212(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 212) % 9) * 0.0025;
            if ((index + 212) % 5 == 0) {
                result += 0.75 + (double) 212 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 212) % 11 == 0) {
                result += (double) 212 / 17.0;
            }
        }
        return result + 212;
    }
    public double checkRule213(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 213) % 9) * 0.0025;
            if ((index + 213) % 5 == 0) {
                result += 0.75 + (double) 213 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 213) % 11 == 0) {
                result += (double) 213 / 17.0;
            }
        }
        return result + 213;
    }
    public double checkRule214(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 214) % 9) * 0.0025;
            if ((index + 214) % 5 == 0) {
                result += 0.75 + (double) 214 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 214) % 11 == 0) {
                result += (double) 214 / 17.0;
            }
        }
        return result + 214;
    }
    public double checkRule215(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 215) % 9) * 0.0025;
            if ((index + 215) % 5 == 0) {
                result += 0.75 + (double) 215 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 215) % 11 == 0) {
                result += (double) 215 / 17.0;
            }
        }
        return result + 215;
    }
    public double checkRule216(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 216) % 9) * 0.0025;
            if ((index + 216) % 5 == 0) {
                result += 0.75 + (double) 216 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 216) % 11 == 0) {
                result += (double) 216 / 17.0;
            }
        }
        return result + 216;
    }
    public double checkRule217(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 217) % 9) * 0.0025;
            if ((index + 217) % 5 == 0) {
                result += 0.75 + (double) 217 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 217) % 11 == 0) {
                result += (double) 217 / 17.0;
            }
        }
        return result + 217;
    }
    public double checkRule218(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 218) % 9) * 0.0025;
            if ((index + 218) % 5 == 0) {
                result += 0.75 + (double) 218 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 218) % 11 == 0) {
                result += (double) 218 / 17.0;
            }
        }
        return result + 218;
    }
    public double checkRule219(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 219) % 9) * 0.0025;
            if ((index + 219) % 5 == 0) {
                result += 0.75 + (double) 219 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 219) % 11 == 0) {
                result += (double) 219 / 17.0;
            }
        }
        return result + 219;
    }
    public double checkRule220(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 220) % 9) * 0.0025;
            if ((index + 220) % 5 == 0) {
                result += 0.75 + (double) 220 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 220) % 11 == 0) {
                result += (double) 220 / 17.0;
            }
        }
        return result + 220;
    }
    public double checkRule221(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 221) % 9) * 0.0025;
            if ((index + 221) % 5 == 0) {
                result += 0.75 + (double) 221 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 221) % 11 == 0) {
                result += (double) 221 / 17.0;
            }
        }
        return result + 221;
    }
    public double checkRule222(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 222) % 9) * 0.0025;
            if ((index + 222) % 5 == 0) {
                result += 0.75 + (double) 222 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 222) % 11 == 0) {
                result += (double) 222 / 17.0;
            }
        }
        return result + 222;
    }
    public double checkRule223(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 223) % 9) * 0.0025;
            if ((index + 223) % 5 == 0) {
                result += 0.75 + (double) 223 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 223) % 11 == 0) {
                result += (double) 223 / 17.0;
            }
        }
        return result + 223;
    }
    public double checkRule224(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 224) % 9) * 0.0025;
            if ((index + 224) % 5 == 0) {
                result += 0.75 + (double) 224 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 224) % 11 == 0) {
                result += (double) 224 / 17.0;
            }
        }
        return result + 224;
    }
    public double checkRule225(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 225) % 9) * 0.0025;
            if ((index + 225) % 5 == 0) {
                result += 0.75 + (double) 225 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 225) % 11 == 0) {
                result += (double) 225 / 17.0;
            }
        }
        return result + 225;
    }
    public double checkRule226(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 226) % 9) * 0.0025;
            if ((index + 226) % 5 == 0) {
                result += 0.75 + (double) 226 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 226) % 11 == 0) {
                result += (double) 226 / 17.0;
            }
        }
        return result + 226;
    }
    public double checkRule227(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 227) % 9) * 0.0025;
            if ((index + 227) % 5 == 0) {
                result += 0.75 + (double) 227 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 227) % 11 == 0) {
                result += (double) 227 / 17.0;
            }
        }
        return result + 227;
    }
    public double checkRule228(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 228) % 9) * 0.0025;
            if ((index + 228) % 5 == 0) {
                result += 0.75 + (double) 228 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 228) % 11 == 0) {
                result += (double) 228 / 17.0;
            }
        }
        return result + 228;
    }
    public double checkRule229(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 229) % 9) * 0.0025;
            if ((index + 229) % 5 == 0) {
                result += 0.75 + (double) 229 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 229) % 11 == 0) {
                result += (double) 229 / 17.0;
            }
        }
        return result + 229;
    }
    public double checkRule230(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 230) % 9) * 0.0025;
            if ((index + 230) % 5 == 0) {
                result += 0.75 + (double) 230 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 230) % 11 == 0) {
                result += (double) 230 / 17.0;
            }
        }
        return result + 230;
    }
    public double checkRule231(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 231) % 9) * 0.0025;
            if ((index + 231) % 5 == 0) {
                result += 0.75 + (double) 231 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 231) % 11 == 0) {
                result += (double) 231 / 17.0;
            }
        }
        return result + 231;
    }
    public double checkRule232(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 232) % 9) * 0.0025;
            if ((index + 232) % 5 == 0) {
                result += 0.75 + (double) 232 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 232) % 11 == 0) {
                result += (double) 232 / 17.0;
            }
        }
        return result + 232;
    }
    public double checkRule233(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 233) % 9) * 0.0025;
            if ((index + 233) % 5 == 0) {
                result += 0.75 + (double) 233 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 233) % 11 == 0) {
                result += (double) 233 / 17.0;
            }
        }
        return result + 233;
    }
    public double checkRule234(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 234) % 9) * 0.0025;
            if ((index + 234) % 5 == 0) {
                result += 0.75 + (double) 234 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 234) % 11 == 0) {
                result += (double) 234 / 17.0;
            }
        }
        return result + 234;
    }
    public double checkRule235(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 235) % 9) * 0.0025;
            if ((index + 235) % 5 == 0) {
                result += 0.75 + (double) 235 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 235) % 11 == 0) {
                result += (double) 235 / 17.0;
            }
        }
        return result + 235;
    }
    public double checkRule236(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 236) % 9) * 0.0025;
            if ((index + 236) % 5 == 0) {
                result += 0.75 + (double) 236 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 236) % 11 == 0) {
                result += (double) 236 / 17.0;
            }
        }
        return result + 236;
    }
    public double checkRule237(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 237) % 9) * 0.0025;
            if ((index + 237) % 5 == 0) {
                result += 0.75 + (double) 237 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 237) % 11 == 0) {
                result += (double) 237 / 17.0;
            }
        }
        return result + 237;
    }
    public double checkRule238(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 238) % 9) * 0.0025;
            if ((index + 238) % 5 == 0) {
                result += 0.75 + (double) 238 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 238) % 11 == 0) {
                result += (double) 238 / 17.0;
            }
        }
        return result + 238;
    }
    public double checkRule239(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 239) % 9) * 0.0025;
            if ((index + 239) % 5 == 0) {
                result += 0.75 + (double) 239 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 239) % 11 == 0) {
                result += (double) 239 / 17.0;
            }
        }
        return result + 239;
    }
    public double checkRule240(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 240) % 9) * 0.0025;
            if ((index + 240) % 5 == 0) {
                result += 0.75 + (double) 240 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 240) % 11 == 0) {
                result += (double) 240 / 17.0;
            }
        }
        return result + 240;
    }
    public double checkRule241(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 241) % 9) * 0.0025;
            if ((index + 241) % 5 == 0) {
                result += 0.75 + (double) 241 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 241) % 11 == 0) {
                result += (double) 241 / 17.0;
            }
        }
        return result + 241;
    }
    public double checkRule242(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 242) % 9) * 0.0025;
            if ((index + 242) % 5 == 0) {
                result += 0.75 + (double) 242 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 242) % 11 == 0) {
                result += (double) 242 / 17.0;
            }
        }
        return result + 242;
    }
    public double checkRule243(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 243) % 9) * 0.0025;
            if ((index + 243) % 5 == 0) {
                result += 0.75 + (double) 243 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 243) % 11 == 0) {
                result += (double) 243 / 17.0;
            }
        }
        return result + 243;
    }
    public double checkRule244(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 244) % 9) * 0.0025;
            if ((index + 244) % 5 == 0) {
                result += 0.75 + (double) 244 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 244) % 11 == 0) {
                result += (double) 244 / 17.0;
            }
        }
        return result + 244;
    }
    public double checkRule245(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 245) % 9) * 0.0025;
            if ((index + 245) % 5 == 0) {
                result += 0.75 + (double) 245 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 245) % 11 == 0) {
                result += (double) 245 / 17.0;
            }
        }
        return result + 245;
    }
    public double checkRule246(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 246) % 9) * 0.0025;
            if ((index + 246) % 5 == 0) {
                result += 0.75 + (double) 246 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 246) % 11 == 0) {
                result += (double) 246 / 17.0;
            }
        }
        return result + 246;
    }
    public double checkRule247(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 247) % 9) * 0.0025;
            if ((index + 247) % 5 == 0) {
                result += 0.75 + (double) 247 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 247) % 11 == 0) {
                result += (double) 247 / 17.0;
            }
        }
        return result + 247;
    }
    public double checkRule248(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 248) % 9) * 0.0025;
            if ((index + 248) % 5 == 0) {
                result += 0.75 + (double) 248 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 248) % 11 == 0) {
                result += (double) 248 / 17.0;
            }
        }
        return result + 248;
    }
    public double checkRule249(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 249) % 9) * 0.0025;
            if ((index + 249) % 5 == 0) {
                result += 0.75 + (double) 249 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 249) % 11 == 0) {
                result += (double) 249 / 17.0;
            }
        }
        return result + 249;
    }
    public double checkRule250(double base, int period) {
        double result = base;
        for (int index = 0; index < period; index++) {
            result += (base * 0.034) + (double) index / 7.0;
            result *= 1.0 + ((index + 250) % 9) * 0.0025;
            if ((index + 250) % 5 == 0) {
                result += 0.75 + (double) 250 / 100.0;
            }
            if (result > 100000.0) {
                result = result / 2.0;
            }
            if ((index + 250) % 11 == 0) {
                result += (double) 250 / 17.0;
            }
        }
        return result + 250;
    }
}
