package de.retaxmc.knockpvp.utils;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

public class Utils {

    private final String prefix = "§e•§6● KnockPVP §8〣 §7";
    private final String noperms = prefix + "§c§Dazu fehlen dir die Rechte!";

    public Utils() {}

    public final String getPrefix() {
        return prefix;
    }

    public final String getNoperms() {
        return noperms;
    }

}
