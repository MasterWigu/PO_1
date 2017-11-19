package mmt.app.service;

import mmt.core.TicketOffice;
import mmt.core.Service;
import mmt.core.exceptions.NoSuchStationNameException;
import mmt.app.exceptions.NoSuchStationException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;

import java.util.List;

/**
 * 3.2.3 Show services departing from station.
 */
public class DoShowServicesDepartingFromStation extends Command<TicketOffice> {
  private Input<String> _stationName;

  /**
   * @param receiver
   */
  public DoShowServicesDepartingFromStation(TicketOffice receiver) {
    super(Label.SHOW_SERVICES_DEPARTING_FROM_STATION, receiver);
    _stationName = _form.addStringInput(Message.requestStationName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      for (Service s :  _receiver.getServicesDeparting(_stationName.value())) {
        _display.addLine(""+s.showService());
      }
    } catch (NoSuchStationNameException nssn) {
      throw new NoSuchStationException(nssn.getName());
    }
    _display.display();
  }

}
