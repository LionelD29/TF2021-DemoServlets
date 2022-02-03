package be.technifutur.demoServlets.utils;

public abstract class Util {
    public static String escapeSpecialCharacters(String string) {
        return string.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
