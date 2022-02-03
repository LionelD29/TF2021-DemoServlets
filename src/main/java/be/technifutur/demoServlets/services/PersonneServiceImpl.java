package be.technifutur.demoServlets.services;

import be.technifutur.demoServlets.models.Personne;

import java.util.ArrayList;
import java.util.List;

public class PersonneServiceImpl implements PersonneService {

    // region SINGLETON
    private static PersonneServiceImpl _instance;

    private PersonneServiceImpl() {
        personnes = new ArrayList<>();
    }

    public static PersonneServiceImpl getInstance() {
        return _instance == null ? _instance = new PersonneServiceImpl() : _instance;
    }
    // endregion

    private final List<Personne> personnes;

    @Override
    public List<Personne> getAll() {
        return personnes;
    }

    public void addPersonne(Personne personne) {
        personnes.add(personne);
    }
}
