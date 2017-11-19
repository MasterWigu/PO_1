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
//FIXME import other classes if necessary

/**
 * 3.2.3 Show services departing from station.
 */
public class DoShowServicesDepartingFromStation extends Command<TicketOffice> {
  private Input<String> _stationName;
  private Message _message;


  /**
   * @param receiver
   */
  public DoShowServicesDepartingFromStation(TicketOffice receiver) {
    super(Label.SHOW_SERVICES_DEPARTING_FROM_STATION, receiver);
    _stationName = _form.addStringInput(_message.requestStationName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    List<Service> servList;
    try {
      servList =  _receiver.getServicesDeparting(_stationName.value());
    } catch (NoSuchStationNameException nssn) {
      throw new NoSuchStationException(nssn.getName());
    }

    for (Service s : servList) {
      _display.addLine(""+s.showService());
    }

    _display.display();
  }

}
