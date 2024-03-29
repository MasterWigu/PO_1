package mmt.app.service;

import mmt.core.TicketOffice;
import mmt.core.Service;
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

    _display.addLine(_receiver.getAllServices());


    _display.display();
  }

}
