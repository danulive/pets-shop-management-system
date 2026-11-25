package com.pawsshop.model; import java.math.BigDecimal;
/** Payment is deliberately abstract: subclasses supply different payment descriptions. No real gateway is connected. */
public abstract class Payment { public abstract String describe(BigDecimal amount); public static Payment of(String method){if("CASH".equals(method))return new CashPayment(); if("CARD".equals(method))return new CardPayment(); throw new IllegalArgumentException("Payment must be CASH or CARD");} }
