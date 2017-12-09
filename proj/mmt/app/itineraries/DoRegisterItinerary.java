package mmt.app.itineraries;

import mmt.core.TicketOffice;
import mmt.app.exceptions.BadDateException;
import mmt.app.exceptions.BadTimeException;
import mmt.app.exceptions.NoSuchItineraryException;
import mmt.app.exceptions.NoSuchPassengerException;
import mmt.app.exceptions.NoSuchStationException;
import mmt.core.exceptions.BadDateSpecificationException;
import mmt.core.exceptions.BadTimeSpecificationException;
import mmt.core.exceptions.NoSuchPassengerIdException;
import mmt.core.exceptions.NoSuchStationNameException;
import mmt.core.exceptions.NoSuchItineraryChoiceException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;

//FIXME import other classes if necessary

/**
 * §3.4.3. Add new itinerary.
 */
public class DoRegisterItinerary extends Command<TicketOffice> {
  private Input<Integer> _id;
  private Input<String> _depStation;
  private Input<String> _arrStation;
  private Input<String> _depDate;
  private Input<String> _depTime;
  private Input<Integer> _choice;

  /**
   * @param receiver
   */
  public DoRegisterItinerary(TicketOffice receiver) {
    super(Label.REGISTER_ITINERARY, receiver);
    _id = _form.addIntegerInput(Message.requestPassengerId());
    _depStation = _form.addStringInput(Message.requestDepartureStationName());
    _arrStation = _form.addStringInput(Message.requestArrivalStationName());
    _depDate = _form.addStringInput(Message.requestDepartureDate());
    _depTime = _form.addStringInput(Message.requestDepartureTime());
    _choice = _form.addIntegerInput(Message.requestItineraryChoice());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    String out = new String();
    try {
      out = _receiver.searchItineraries(_id.value(), _depStation.value(), _arrStation.value(), _depDate.value(), _depTime.value());

      if (out != " "){
        _display.addLine(out);
        _display.display();
        _form.parse();
      }

      
        _receiver.commitItinerary(_id.value(), _choice.value());
      

    } catch (NoSuchPassengerIdException e) {
      throw new NoSuchPassengerException(e.getId());
    } catch (NoSuchStationNameException e) {
      throw new NoSuchStationException(e.getName());
    } catch (NoSuchItineraryChoiceException e) {
      throw new NoSuchItineraryException(e.getPassengerId(), e.getItineraryId());
    } catch (BadDateSpecificationException e) {
      throw new BadDateException(e.getDate());
    } catch (BadTimeSpecificationException e) {
      throw new BadTimeException(e.getTime());
    }
  }
}
