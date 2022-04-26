package group14.common.services;

import group14.common.game.GameData;
import group14.common.game.World;

public interface IEntityProcessingService {

   public void process(GameData gameData, World world);
}