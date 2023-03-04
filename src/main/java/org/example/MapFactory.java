package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory {

    public static <T, K> Map<T, K> getInstance(MapTypes selectedMap, T keyType, K valyType){
        if(selectedMap.equals(null))
            throw new RuntimeException("No map type selected.");
        switch (selectedMap){
            case HASHMAP: return new HashMap<T, K>();
            case LINKED_HASHMAP: return new LinkedHashMap<T, K>();
            case TREEMAP: return new TreeMap<T, K>();
        }
        return null;
    }
}
