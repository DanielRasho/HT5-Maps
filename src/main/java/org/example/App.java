package org.example;

import org.example.App_utils.printer.AnsiColors;
import org.example.App_utils.printer.Menu;
import org.example.App_utils.printer.MenuEntry;
import org.example.App_utils.printer.Printer;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // FETCHING CARDS FROM FILE
        File cardDBFile;
        try {
            cardDBFile = getFile();
        } catch (NoSuchFileException e) {
            Printer.print("No such File of directory. Try Again.", AnsiColors.BLACK, AnsiColors.RED_BACKGROUND);
            return;
        }

        // SELECTING MAP IMPLEMENTATION.
        MapTypes mapType = selectMapImplementation();

        // STORING CARDS ENTRIES ON MAP.
        Map<String, String> cardDB = MapFactory.getInstance(mapType, "", "");
        FileReader.fillMapFromFile( cardDBFile, "\\|", "\\n|\\r",cardDB);

        // CREATING USER CARD'S COLLECTION
        List<String> cardTypes = Arrays.asList("Monstruo", "Trampa", "Hechizo");
        CardCollection UserCards = new CardCollection(cardDB, mapType, cardTypes);

        // EDITING USER CARDS' COLLECTION
        boolean canContinue = true;
        while (canContinue){
            Menu menu = new Menu()
                    .header("What you want to do?")
                    .headerColors(AnsiColors.BLACK, AnsiColors.GREEN_BACKGROUND)
                    .indexColors(AnsiColors.BLACK, AnsiColors.YELLOW_BACKGROUND)
                    .data( Arrays.asList(
                            new MenuEntry<String>("Add card to collection"),
                            new MenuEntry<String>("Show your collection"),
                            new MenuEntry<String>("Show card DB"),
                            new MenuEntry<String>("Exit")))
                    .show();
            switch (menu.getSelectedIndex()){
                case 0 -> {
                    String key = Printer.input("Enter a valid card name: ");
                    try {
                        UserCards.add(key);
                    } catch (Exception e) {
                        Printer.printConfirmation("Card not found...", "Try a valid name");
                    }
                }
                case 1 -> {
                    boolean canSort = Printer.printSimpleMenu("Sort cards by type? ", "Yes", "No");
                    UserCards.show(canSort);
                }
                case 2 -> {
                    boolean canSort = Printer.printSimpleMenu("Sort cards by type? ", "Yes", "No");
                    long timeStart = System.nanoTime();
                    UserCards.showCardDB(canSort);
                    long execTime = System.nanoTime() - timeStart;
                    System.out.println(execTime/1000000);
                }
                case 3 -> canContinue = false;
            }
        }
    }

    private static File getFile() throws NoSuchFileException {
        String filePath = Printer.input("Enter the FILEPATH, were your cards DB exist: ",
                AnsiColors.BLACK ,AnsiColors.GREEN_BACKGROUND);
        File file = new File(filePath);
        if (!file.exists())
            throw new NoSuchFileException(filePath);
        else
            return new File(filePath);
    }

    private static MapTypes selectMapImplementation(){
        Menu menu = new Menu()
                .header("Choose your Map implementation")
                .headerColors(AnsiColors.BLACK, AnsiColors.GREEN_BACKGROUND)
                .indexColors(AnsiColors.BLACK, AnsiColors.YELLOW_BACKGROUND)
                .data(Arrays.asList(MapTypes.values()), MapTypes::toString)
                .show();
        return (MapTypes) menu.getEntryValue();
    }
}
