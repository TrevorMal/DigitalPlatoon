package za.co.digitalplatoon.invoiceservice.invoice;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Invoice
{
  private long id;
  private String client;
  private BigDecimal vatRate;
  private Date invoiceDate;
  private Collection<LineItem> lineItems;

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

  @Column(name = "client")
  public String getClient()
  {
    return client;
  }

  public void setClient(String client)
  {
    this.client = client;
  }

  @Column(name = "vat_rate")
  public BigDecimal getVatRate()
  {
    return vatRate;
  }

  public void setVatRate(BigDecimal vatRate)
  {
    this.vatRate = vatRate;
  }

  @Column(name = "invoice_date")
  public Date getInvoiceDate()
  {
    return invoiceDate;
  }

  public void setInvoiceDate(Date invoiceDate)
  {
    this.invoiceDate = invoiceDate;
  }

  @OneToMany
  public Collection<LineItem> getLineItems()
  {
    return lineItems;
  }

  public void setLineItems(Collection<LineItem> lineItems)
  {
    this.lineItems = lineItems;
  }

  /**
   * Calculates the total for all the items excluding VAT
   * @return items subtotal
   */
  @Transient
  public BigDecimal getSubTotal()
  {
    BigDecimal subTotal = BigDecimal.ZERO;

    for (LineItem item : lineItems)
    {
      subTotal = subTotal.add(item.getLineItemTotal());
    }
    return subTotal;
  }

  /**
   * Calculates the vat on all items
   * @return total vat
   */
  @Transient
  public BigDecimal getVat()
  {
    return getSubTotal().multiply(vatRate);
  }

  /**
   * Calculate the total outstanding for the invoice
   * @return total invoice ammount
   */
  @Transient
  public BigDecimal getTotal()
  {
    return getSubTotal().add(getVat());
  }

}
