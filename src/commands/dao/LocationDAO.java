package commands.dao;

import models.Item;
import models.Location;
import models.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationDAO {

    private Map<String, Location> locations;

    public LocationDAO() {
        this.locations = new HashMap<>();
    }

    private void initializations(){
        String description =
                "Temný, stísněný prostor mezi vysokými domy. " +
                        "Jemný déšť tiše bubnuje o beton a neonové " +
                        "odlesky se lámou v kalužích. " +
                        "Ležíš na hromadě odpadkových pytlů, promočených a páchnoucích chemií."  +
                        "Studený asfalt tlačí do zad a město kolem tebe dýchá cizí, lhostejnou energií.";
        List<Item> locationItems = new ArrayList<>();
        List<NPC> npcs = new ArrayList<>();
        npcs.add(new NPCDAO().getNPCByName("Bezdomovec"));

        HashMap <String, Location> exits = new HashMap<>();
        exits.put("sever", new LocationDAO().getLocationByName("Trh v Malé Čině"));
        Location startingLocation = new Location("Postranní úlička",
                description, locationItems, npcs, );
    }

    public void getLocationByName(String name){

    }

}
