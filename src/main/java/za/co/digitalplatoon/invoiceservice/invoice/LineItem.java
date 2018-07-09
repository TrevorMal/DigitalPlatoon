package za.co.digitalplatoon.invoiceservice.invoice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"invoice"})
public class LineItem
{

  private long id;
  private long invoiceId;
  private long quantity;
  private String description;
  private BigDecimal unitPrice;
  private Invoice invoice;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  @Column(name = "invoice_id")
  public long getInvoiceId()
  {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId)
  {
    this.invoiceId = invoiceId;
  }

  @Column(name = "quantity")
  public long getQuantity()
  {
    return quantity;
  }

  public void setQuantity(long quantity)
  {
    this.quantity = quantity;
  }

  @Column(name = "description")
  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  @Column(name = "unit_price")
  public BigDecimal getUnitPrice()
  {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice)
  {
    this.unitPrice = unitPrice;
  }

  @ManyToOne
  @JoinColumn(name = "invoice_id", insertable = false, updatable = false)
  public Invoice getInvoice()
  {
    return invoice;
  }

  public void setInvoice(Invoice invoice)
  {
    this.invoice = invoice;
  }

  @Transient
  public BigDecimal getLineItemTotal()
  {
    return this.unitPrice.multiply(BigDecimal.valueOf(quantity));
  }
}
