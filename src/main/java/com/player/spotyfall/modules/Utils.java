package com.player.spotyfall.modules;

public class Utils {
    /** Check if a string is null, blank or empty
     * returns false if string have any of attributes above
     *
     * @param string
     * @return
     */
    public static boolean validateString(String string){
        if(string == null || string.isEmpty() || string.trim().isEmpty())
            return false;

        return true;
    }
}
