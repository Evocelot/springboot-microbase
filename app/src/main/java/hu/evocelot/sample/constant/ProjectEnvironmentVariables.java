package hu.evocelot.sample.constant;

/**
 * Class for storing the ENV keys for the project.
 * 
 * @author mark.danisovszky
 */
public class ProjectEnvironmentVariables {

    private ProjectEnvironmentVariables() {

    }

    /**
     * The url of the tracing tool (Jaeger).
     */
    public final static String TRACING_URL = "${TRACING_URL}";
}
