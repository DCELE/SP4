
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dilara
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {

        LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();
        cfg.title = "LoneSurvivor";
        cfg.width = 840;
        cfg.height = 840;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(new Game(), cfg);

    }

}

