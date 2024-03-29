package mmt.app;

import static pt.tecnico.po.ui.Dialog.IO;

import mmt.core.TicketOffice;
import mmt.core.exceptions.ImportFileException;

import mmt.app.main.MainMenu;
import pt.tecnico.po.ui.Menu;

import java.util.Locale;
/**
 * Main driver for the travel management application.
 */
public class App {
  /**
   * @param args
   */
  public static void main(String[] args) {
    Locale.setDefault(new Locale("en", "US"));
    TicketOffice office = new TicketOffice();

    String datafile = System.getProperty("import"); //$NON-NLS-1$
    if (datafile != null) {
      try {
        office.importFile(datafile);
      } catch (ImportFileException e) {
        // no behavior described: just present the problem
        e.printStackTrace();
      }
    }

    Menu menu = new MainMenu(office);
    menu.open();

    IO.close();
  }

}
