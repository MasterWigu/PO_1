package mmt.app.service;

import mmt.core.TicketOffice;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;


/**
 * 3.2.1 Show all services.
 */
public class DoShowAllServices extends Command<TicketOffice> {

  /**
   * @param receiver
   */
  public DoShowAllServices(TicketOffice receiver) {
    super(Label.SHOW_ALL_SERVICES, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    /*
    _form.parse();

    _display.add("-------- SERVICES --------");

    for(Service s : _receiver.getService())
      _display.addLine(""+_receiver.showService());


    _display.display();
    */
  }

}
