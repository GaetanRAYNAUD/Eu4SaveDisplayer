package com.graynaud.eu4savedisplayerbo.model.save.general;

import java.util.List;
import java.util.TreeSet;

public class Empire {
    private String emperor;

    private Double imperialInfluence;

    private Integer reformLevel;

    private TreeSet<OldEmperor> oldEmperors;

    private List<String> electors;
}
