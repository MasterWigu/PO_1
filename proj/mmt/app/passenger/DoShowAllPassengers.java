
package mmt.app.passenger;

import mmt.core.TicketOffice;
import mmt.core.Passenger;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;

/**
 * §3.3.1. Show all passengers.
 */
public class DoShowAllPassengers extends Command<TicketOffice> {

  /**
   * @param receiver
   */
  public DoShowAllPassengers(TicketOffice receiver) {
    super(Label.SHOW_ALL_PASSENGERS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    
    _display.addLine(_receiver.getPassengers());
    
    _display.display();
  }

}
