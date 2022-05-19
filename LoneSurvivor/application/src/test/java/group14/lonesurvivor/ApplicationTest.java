package group14.lonesurvivor;

import static com.badlogic.gdx.utils.TimeUtils.millis;
import group14.common.services.IUpdate;
import java.io.IOException;
import group14.common.services.IPlugin;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.time.Clock.system;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Lookup;

public class ApplicationTest extends NbTestCase {
    
    
    private static final String ADD_ENEMY_UPDATES_FILE = "/Users/muhamadsdu/Desktop/SP4/LoneSurvivor/application/src/test/resources/enemy/updates.xml";
    private static final String REM_ENEMY_UPDATES_FILE = "/Users/muhamadsdu/Desktop/SP4/LoneSurvivor/application/src/test/resources/remenemy/updates.xml";
    private static final String UPDATES_FILE = "/Users/muhamadsdu/Desktop/SP4/LoneSurvivor/netbeans_site/updates.xml";

    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false). 
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

public void testApplication() throws InterruptedException, IOException {

        // SETUP
        List<IUpdate> update = new CopyOnWriteArrayList<>();
        List<IPlugin> plugin = new CopyOnWriteArrayList<>();

        waitForUpdate(update, plugin);
        
        // PRE ASSERTS
        //Size should be 0 because no modules are installed.
        assertEquals("No plugins", 0, update.size());
        assertEquals("No processors", 0, plugin.size());

        // TEST: Load Enemy via UC
        copy(get(ADD_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);

        waitForUpdate(update, plugin);

        // ASSERTS: Enemy loaded
        assertEquals("One plugins", 4, plugin.size());
        assertEquals("One processors", 4, update.size());

        // TEST: Unload Enemy via UC
        copy(get(REM_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);

        waitForUpdate(update, plugin);

        // ASSERTS: Enemy unloaded
        assertEquals("No plugins", 4, plugin.size());
        assertEquals("No processors", 4, update.size());

        copy(get(ADD_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
    }

    private void waitForUpdate(List<IUpdate> update, List<IPlugin> plugin) throws InterruptedException {
        // Needs time for silentUpdater to install all modules
        Thread.sleep(10000);

        update.clear();
        update.addAll(Lookup.getDefault().lookupAll(IUpdate.class));

        plugin.clear();
        plugin.addAll(Lookup.getDefault().lookupAll(IPlugin.class));
    }

}
