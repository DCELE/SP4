package group14.lonesurvivor;


import group14.common.services.IPlugin;
import group14.common.services.IUpdate;
import java.io.IOException;

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

    // TODO: Put your own paths here...
    private static final String ADD_ENEMY_UPDATES_FILE = "/Users/Dilara/Documents/GitHub/Semesterprojekt%204/SP4/LoneSurvivor/application/src/test/resources/enemy/updates.xml";
    private static final String REM_ENEMY_UPDATES_FILE = "/Users/Dilara/Documents/GitHub/Semesterprojekt%204/SP4/LoneSurvivor/application/src/test/resources/remenemy/updates.xml";
    private static final String UPDATES_FILE = "/Users/Dilara/Documents/GitHub/Semesterprojekt%204/SP4/LoneSurvivor/netbeans_site/updates.xml";

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
        
//        System.out.println("hej" + ADD_ENEMY_UPDATES_FILE);
//        System.out.println("hej" + REM_ENEMY_UPDATES_FILE);
//        System.out.println("hej" + UPDATES_FILE);
//        // SETUP
        List<IUpdate> processors = new CopyOnWriteArrayList<>();
        List<IPlugin> plugins = new CopyOnWriteArrayList<>();

       
         waitForUpdate(processors, plugins, 1000);

        // PRE ASSERTS
        //Size should be 0 because no modules are installed.
       
        

        // PRE ASSERTS
        //Size should be 0 because no modules are installed.
      
        // TEST: Load Enemy via UC
        copy(get(ADD_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
           
        waitForUpdate(processors, plugins, 10000);

        
        
        // ASSERTS: Enemy loaded
        assertEquals("One plugins", 4, plugins.size());
        assertEquals("One processors", 6, processors.size());
   
        // TEST: Unload Enemy via UC
        copy(get(REM_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);

         waitForUpdate(processors, plugins, 10000);
        // ASSERTS: Enemy unloaded
        assertEquals("No plugins", 3, plugins.size());
        assertEquals("No processors", 4, processors.size());

        copy(get(ADD_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
    }

    private void waitForUpdate(List<IUpdate> processors ,List<IPlugin> plugins, long millis) throws InterruptedException {
        // Needs time for silentUpdater to install all modules
        Thread.sleep(millis);

        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(IUpdate.class));

        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(IPlugin.class));
    }

}