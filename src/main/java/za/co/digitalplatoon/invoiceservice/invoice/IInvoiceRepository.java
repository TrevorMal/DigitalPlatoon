package za.co.digitalplatoon.invoiceservice.invoice;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 *
 * <p>Title: InvoiceRepository</p>
 *
 * <p>Description: InvoiceRepository</p>
 *
 */
public interface IInvoiceRepository extends CrudRepository<Invoice,Long>
{
  Invoice findInvoiceById(long id);
  @Override
  List<Invoice> findAll();
  @Override
  Invoice save(Invoice invoice);
}
