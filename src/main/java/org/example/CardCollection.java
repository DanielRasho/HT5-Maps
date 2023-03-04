package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Representation of a User Card's collection.*/
public class CardCollection {

    private final Map<String, String> cardDB;
    private final List<String> availableTypes;
    private final Map<String, Object[]> collection;

    public CardCollection (Map<String, String> cardDB, MapTypes mapImplementation, List<String> availableTypes){
        this.cardDB = cardDB;
        this.availableTypes = availableTypes;
        this.collection = MapFactory.getInstance(mapImplementation, "", new Object[]{});
    }

    public void  add(String avatar) throws Exception {
        if (!cardDB.containsKey(avatar))
            throw new Exception("Avatar does not exist in current DB.");
        if (!collection.containsKey(avatar))
            collection.put(avatar, new Object[]{cardDB.get(avatar),1});
        else{
            int cardCount = (Integer) collection.get(avatar)[1] + 1;
            collection.put(avatar, new Object[]{cardDB.get(avatar), cardCount});
        }
    }

    public void show(boolean sortedByType){
        if(sortedByType)
            this.collection.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.comparing(e -> e[0].toString())))
                    .forEach(
                    (e) -> {
                        String cardName = e.getKey();
                        String cardType = (String) e.getValue()[0];
                        String cardCount = e.getValue()[1].toString();
                        System.out.println(">> " + cardName + "\n\tType:" + cardType + "\n\tCount: " + cardCount);
                    });
        else
            this.collection.entrySet().forEach(
                    (e) -> {
                        String cardName = e.getKey();
                        String cardType = (String) e.getValue()[0];
                        String cardCount = e.getValue()[1].toString();
                        System.out.println(">> " + cardName + "\n\tType:" + cardType + "\n\tCount: " + cardCount);
                    });
    }

    public void showCardDB(boolean sortedByType){
        if(sortedByType)    // Ordenando primero.
            this.cardDB.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(System.out::println);
        else                // Sin ordenar
            this.cardDB.entrySet().forEach(System.out::println);
    }
}
