package za.co.digitalplatoon.invoiceservice.invoice;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController
{
  @Autowired
  private IInvoiceRepository repository;

  @Consumes(MediaType.APPLICATION_JSON)
  @RequestMapping(value = "/invoices", method = RequestMethod.POST)
  public void addInvoice(@RequestBody Invoice invoice)
  {
    System.out.println(invoice);
    repository.save(invoice);
  }

  @GetMapping("/invoices")
  @ResponseBody
  public List<Invoice> viewInvoices()
  {
    return repository.findAll();
  }

  @GetMapping("/invoice")
  @ResponseBody
  public Invoice viewInvoice(@RequestParam(name = "id") long id)
  {
    return repository.findInvoiceById(id);
  }
}
