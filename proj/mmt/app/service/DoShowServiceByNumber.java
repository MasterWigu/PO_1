package mmt.app.service;

import mmt.core.TicketOffice;
import mmt.core.exceptions.NoSuchServiceIdException;
import mmt.app.exceptions.NoSuchServiceException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;


//FIXME import other classes if necessary

/**
 * 3.2.2 Show service by number.
 */
public class DoShowServiceByNumber extends Command<TicketOffice> {

  private Input<Integer> _id;
  private Message _message;

  /**
   * @param receiver
   */
  public DoShowServiceByNumber(TicketOffice receiver) {
    super(Label.SHOW_SERVICE_BY_NUMBER, receiver);
    _id = _form.addIntegerInput(_message.requestServiceId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {

    _form.parse();

    _display.addLine(""+getServiceById(_id).showService());

    _display.display();
  }
}
