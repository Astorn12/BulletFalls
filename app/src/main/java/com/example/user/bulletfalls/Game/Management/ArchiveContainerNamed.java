package com.example.user.bulletfalls.Game.Management;

import com.example.user.bulletfalls.Game.Elements.Helper.Named;

public class ArchiveContainerNamed extends ArchivContainer<Named> {
    public ArchiveContainerNamed()
    {
        super();
    }

    protected boolean compare(Named n1, Named n2)
    {
        if(n1.getName().equals(n2.getName()))
        {
            return true;
        }
        else return false;
    }
}
