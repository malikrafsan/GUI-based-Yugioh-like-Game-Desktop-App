package com.aetherwars.controller;

import com.aetherwars.model.*;
import com.aetherwars.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardManager {
    private List<Card> cards;
    private static final String CHARACTER_PATH = "/com/aetherwars/card/data/character.csv";
    private static final String SPELL_PTN_PATH = "/com/aetherwars/card/data/spell_ptn.csv";
    private static final String SPELL_SWAP_PATH = "/com/aetherwars/card/data/spell_swap.csv";
    private static final String SPELL_MORPH_PATH = "/com/aetherwars/card/data/spell_morph.csv";
    private static final String SPELL_LVL_PATH = "/com/aetherwars/card/data/spell_lvl.csv";
    private int ctC;
    private int ctSP;
    private int ctSS;
    private int ctSM;
    private int ctSL;

    /**
     * Constructor, loads the cards from the CSV files
     */
    public CardManager() {
        try {
            this.loadCards();
        } catch (Exception e) {
            System.out.println("Error loading cards: " + e);
        }
    }

    /**
     * Loads the cards from the CSV files
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    public void loadCards() throws IOException, URISyntaxException {
        File characterFile = new File(getClass().getResource(CHARACTER_PATH).toURI());
        File spellPtnFile = new File(getClass().getResource(SPELL_PTN_PATH).toURI());
        File spellSwapFile = new File(getClass().getResource(SPELL_SWAP_PATH).toURI());
        File spellMorphFile = new File(getClass().getResource(SPELL_MORPH_PATH).toURI());
        File spellLvlFile = new File(getClass().getResource(SPELL_LVL_PATH).toURI());

        CSVReader characterReader = new CSVReader(characterFile, "\t");
        CSVReader spellPtnReader = new CSVReader(spellPtnFile, "\t");
        CSVReader spellSwapReader = new CSVReader(spellSwapFile, "\t");
        CSVReader spellMorphReader = new CSVReader(spellMorphFile, "\t");
        CSVReader spellLvlReader = new CSVReader(spellLvlFile, ",");

        characterReader.setSkipHeader(true);
        spellPtnReader.setSkipHeader(true);
        spellSwapReader.setSkipHeader(true);
        spellMorphReader.setSkipHeader(true);
        spellLvlReader.setSkipHeader(true);

        List<String[]> characterRows = characterReader.read();
        List<String[]> spellPtnRows = spellPtnReader.read();
        List<String[]> spellSwapRows = spellSwapReader.read();
        List<String[]> spellMorphRows = spellMorphReader.read();
        List<String[]> spellLvlRows = spellLvlReader.read();

        this.cards = new ArrayList<Card>();
        for(String[] row : characterRows) {
            this.cards.add(new CharacterCard(Integer.parseInt(row[0]), row[1], CharType.valueOf(row[2]), row[3], row[4], Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), Integer.parseInt(row[8]), Integer.parseInt(row[9])));
        }
        for(String[] row : spellPtnRows) {
            this.cards.add(new SpellPotionCard(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]), Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }
        for(String[] row : spellSwapRows) {
            this.cards.add(new SpellSwapCard(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]), Integer.parseInt(row[5])));
        }
        for(String[] row : spellMorphRows) {
            this.cards.add(new SpellMorphCard(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]), Integer.parseInt(row[5])));
        }
        for(String[] row : spellLvlRows) {
            this.cards.add(new SpellLevelCard(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]), Integer.parseInt(row[5])));
        }

        ctC = characterRows.size();
        ctSP = spellPtnRows.size();
        ctSS = spellSwapRows.size();
        ctSM = spellMorphRows.size();
        ctSL = spellLvlRows.size();
    }

    /**
     * Returns character cards with id id
     * @param id
     * @return
     */
    public CharacterCard getCharacterCard(int id) {
        return (CharacterCard) this.cards.get(id-1);
    }

    /**
     * setup Deck 40-60 cards
     * @param deck
     */
    public void setupDeck(Deck deck) {
        // 16:10:8:4:2
        List<Card> res = new ArrayList<Card>();
        Random rand = new Random(System.currentTimeMillis());
        int nc = 16+rand.nextInt(8+1);
        int nsp = 10+rand.nextInt(5+1);
        int nss = 8+rand.nextInt(4+1);
        int nsm = 4+rand.nextInt(2+1);
        int nsl = 2+rand.nextInt(1+1);

        try {
            for(int i=0; i<nc; i++) {
                res.add(this.cards.get(rand.nextInt(ctC)));
            }
            for(int i=0; i<nsp; i++) {
                res.add(this.cards.get(ctC+rand.nextInt(ctSP)));
            }
            for(int i=0; i<nss; i++) {
                res.add(this.cards.get(ctC+ctSP+rand.nextInt(ctSS)));
            }
            for(int i=0; i<nsm; i++) {
                res.add(this.cards.get(ctC+ctSP+ctSS+rand.nextInt(ctSM)));
            }
            for(int i=0; i<nsl; i++) {
                res.add(this.cards.get(ctC+ctSP+ctSS+ctSM+rand.nextInt(ctSL)));
            }
        } catch(Exception e) {
            System.out.println("In setupDeck: " + e );
        }

        Collections.shuffle(res);
        for (int i = 0; i < res.size(); i++) {
            deck.addCard(res.get(i));
        }
    }
}
