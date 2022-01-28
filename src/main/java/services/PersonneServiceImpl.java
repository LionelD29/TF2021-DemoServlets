package services;

import models.Personne;
import services.PersonneService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonneServiceImpl implements PersonneService {
    private List<Personne> personnes = new ArrayList<>(Arrays.asList(
            new Personne("Lionel", "Delsupexhe"),
            new Personne("Christophe", "Delbeke")
    ));

    @Override
    public List<Personne> getAll() {
        return personnes;
    }

    public void addPersonne(Personne personne) {
        personnes.add(personne);
    }
}