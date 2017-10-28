package ws.nmathe.saber.core.language;

import org.json.JSONObject;
import ws.nmathe.saber.utils.Logging;
import java.io.*;
import java.util.*;
import org.apache.commons.io.IOUtils;

public class Language
{
    /** Mapping of all language files read-in at application start */
    private static Map<String, JSONObject> LANGUAGES = new HashMap<>();

    static
    {
        try
        {
            // find all json files in
            File dir = new File("./languages");
            File[] files = dir.listFiles(file -> file.getName().endsWith(".json") && file.canRead());
            if (files != null && files.length>0)
            {
                // for each json file, parse into a JSONObject and place into LANGUAGES
                for (File file: files)
                {
                    JSONObject json = new JSONObject(IOUtils.toString(new FileInputStream(file)));
                    String name = file.getName().replaceAll("\\.json$", "");
                    LANGUAGES.put(name, json);
                    Logging.info(Language.class, "Added \"" + name + "\" to available languages" );
                }
            }
            else
            {
                throw new IOException("No locale files found!");
            }
        }
        catch (IOException e)
        {
            Logging.exception(Language.class, e);
            System.exit(-1);
        }
    }

    /**
     * list of all valid LANGUAGES
     * @return list of locale names
     */
    public static Set<String> languages()
    {
        return LANGUAGES.keySet();
    }


    /**
     * retrieves a text for the specified locale
     * @param language the language file to use
     * @param key the key value to use when retrieving text
     * @param args arguments to insert into the retrieved string
     * @return locale specific text
     */
    public static String getString(String language, String key, String[] args)
    {
        JSONObject json = LANGUAGES.getOrDefault(language, new JSONObject());
        String value = json.getString(key);

        int count = 0;
        while (value.contains("$arg") && count < args.length)
        {
            value = value.replaceFirst("\\$arg", args[count++]);
        }
        return value;
    }


    /**
     * retrieves the java Locale object associated with the language file
     * @param language the language file to use
     * @return Locale for the language
     */
    public static Locale getLocale(String language)
    {
        JSONObject json = LANGUAGES.get(language);
        if (json == null) return Locale.getDefault();
        Locale locale = Locale.forLanguageTag(json.getString("Locale"));
        if (locale != null) return locale;
        else return Locale.getDefault();
    }
}
