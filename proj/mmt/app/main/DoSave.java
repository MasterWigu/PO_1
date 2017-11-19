package mmt.app.main;

import java.io.IOException;
import java.io.FileNotFoundException;

import mmt.core.TicketOffice;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

//FIXME import other classes if necessary

/**
 * §3.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<TicketOffice> {
  
  private Input<String> _filename;
  private Message _message;

  /**
   * @param receiver
   */
  public DoSave(TicketOffice receiver) {
    super(Label.SAVE, receiver);
    _filename = _form.addStringInput(_message.saveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    String name = _receiver.getSaveFile();


    while (name.length() == 0) {
      _display.popup(Message.newSaveAs());
      _form.parse(); 
      name = _filename.value();
      _receiver.setSaveFile(name);
    }

    try {
      _receiver.save(name);
    } catch (FileNotFoundException fnfe) {
      _display.popup(Message.fileNotFound());
    } catch (IOException e) {
      // shouldn't happen in a controlled test setup
      e.printStackTrace();
    }
    //FIXME implement command
  }
}
