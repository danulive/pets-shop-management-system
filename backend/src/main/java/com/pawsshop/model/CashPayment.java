package com.pawsshop.model; import java.math.BigDecimal; public class CashPayment extends Payment { @Override public String describe(BigDecimal amount){return "Cash payment: " + amount;} }
