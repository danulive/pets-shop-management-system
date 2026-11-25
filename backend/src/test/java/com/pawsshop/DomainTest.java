package com.pawsshop;
import com.pawsshop.model.*; import com.pawsshop.repo.*; import com.pawsshop.service.SaleService;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*; import static org.mockito.Mockito.*;
import java.math.BigDecimal; import java.util.*;
class DomainTest {
 @Test void petPolymorphism(){Pet dog=new Dog(); dog.setBreed("Beagle"); Pet cat=new Cat();cat.setBreed("Tabby");assertEquals("Dog · Beagle",dog.getDisplayInfo());assertEquals("Cat · Tabby",cat.getDisplayInfo());}
 @Test void stockCannotBecomeNegative(){Product p=new Food();p.setName("Food");p.setStock(3);p.removeStock(2);assertEquals(1,p.getStock());assertThrows(IllegalArgumentException.class,()->p.removeStock(2));assertEquals(1,p.getStock());}
 @Test void saleUsesProductPriceAndUpdatesStock(){ProductRepository products=mock(ProductRepository.class);SaleRepository sales=mock(SaleRepository.class);Product p=new Food();p.setName("Food");p.setPrice(new BigDecimal("12.50"));p.setStock(5);when(products.lockById(1L)).thenReturn(Optional.of(p));when(sales.save(any(Sale.class))).thenAnswer(i->i.getArgument(0));SaleService service=new SaleService(products,sales);SaleService.Line line=new SaleService.Line();line.productId=1L;line.quantity=2;SaleService.Request r=new SaleService.Request();r.paymentMethod="CASH";r.items=List.of(line);Sale sale=service.create(r);assertEquals(new BigDecimal("25.00"),sale.getTotal());assertEquals(3,p.getStock());assertEquals("Food",sale.getItems().get(0).getProductName());}
 @Test void rejectsInvalidPayment(){assertThrows(IllegalArgumentException.class,()->Payment.of("CHEQUE"));assertTrue(Payment.of("CARD").describe(BigDecimal.TEN).contains("Card"));}
}
