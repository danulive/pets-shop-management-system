package com.pawsshop.service;
import com.pawsshop.model.*; import com.pawsshop.repo.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*;
@Service public class SaleService {
 private final ProductRepository products; private final SaleRepository sales;
 public SaleService(ProductRepository p,SaleRepository s){products=p;sales=s;}
 public static class Line { public Long productId; public int quantity; }
 public static class Request { public String customerName; public String paymentMethod; public List<Line> items; }
 @Transactional public Sale create(Request request){
  if(request==null || request.items==null || request.items.isEmpty()) throw new IllegalArgumentException("Add at least one item");
  Payment payment=Payment.of(request.paymentMethod); Sale sale=new Sale(); sale.setCustomerName(request.customerName==null||request.customerName.trim().isEmpty()?"Walk-in customer":request.customerName.trim()); sale.setPaymentMethod(request.paymentMethod); sale.setCreatedAt(LocalDateTime.now()); BigDecimal total=BigDecimal.ZERO;
  // Lock products in a consistent order to prevent concurrent sales overselling stock.
  List<Line> lines=new ArrayList<>(request.items); if(lines.stream().anyMatch(l->l==null||l.productId==null||l.quantity<=0))throw new IllegalArgumentException("Each item needs a product and a positive quantity"); lines.sort(Comparator.comparing(l->l.productId));
  for(Line line:lines){ Product p=products.lockById(line.productId).orElseThrow(()->new IllegalArgumentException("Product not found")); p.removeStock(line.quantity); SaleItem item=new SaleItem(); item.setProductId(p.getId()); item.setProductName(p.getName()); item.setQuantity(line.quantity); item.setUnitPrice(p.getPrice()); item.setSubtotal(p.getPrice().multiply(BigDecimal.valueOf(line.quantity))); total=total.add(item.getSubtotal()); sale.getItems().add(item); }
  payment.describe(total); sale.setTotal(total); return sales.save(sale);
 }
}
