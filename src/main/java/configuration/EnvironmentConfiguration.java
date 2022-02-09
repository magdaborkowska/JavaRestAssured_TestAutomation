package configuration;

public class EnvironmentConfiguration {

    public static EnvironmentConfiguration instance;
    public static String envName;
    private static final String envNameDefault = "dev";
    public static String swUrl;

    public EnvironmentConfiguration() {

        envName = System.getProperty("env");
        if (envName == null)
            envName = envNameDefault;

        swUrl = String.format("https://swapi.%s/api/", envName);
    }

    public static synchronized EnvironmentConfiguration getInstance() {
        if (instance == null)
            instance = new EnvironmentConfiguration();
        return instance;
    }
}
