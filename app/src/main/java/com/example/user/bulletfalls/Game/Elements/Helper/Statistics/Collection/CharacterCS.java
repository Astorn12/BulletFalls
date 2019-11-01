package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection;

import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;

import java.util.List;

public class CharacterCS {
    String indyvidualHeroMarker;
    List<FamilyName> familyNames;
    List<Kind> kind;

    public CharacterCS(String indyvidualHeroMarker, List<FamilyName> familyNames, List<Kind> kind) {
        this.indyvidualHeroMarker = indyvidualHeroMarker;
        this.familyNames = familyNames;
        this.kind = kind;
    }

    public String getIndyvidualHeroMarker() {
        return indyvidualHeroMarker;
    }

    public void setIndyvidualHeroMarker(String indyvidualHeroMarker) {
        this.indyvidualHeroMarker = indyvidualHeroMarker;
    }

    public List<FamilyName> getFamilyNames() {
        return familyNames;
    }

    public void setFamilyNames(List<FamilyName> familyNames) {
        this.familyNames = familyNames;
    }

    public List<Kind> getKind() {
        return kind;
    }

    public void setKind(List<Kind> kind) {
        this.kind = kind;
    }
}
