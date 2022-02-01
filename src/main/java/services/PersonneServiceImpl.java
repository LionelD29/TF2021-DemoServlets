package services;

import models.Personne;
import services.PersonneService;

import java.util.ArrayList;
import java.util.Arrays;
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
