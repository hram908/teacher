package com.blacksheep.teacher.game;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/26/12
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigManager {
    
    private static ConfigManager instance = new ConfigManager();
    public static ConfigManager getInstance()
    {
        return  instance;
    }
    
    public int getImageWidth()
    {
        return 640;
    }
    
    public int getIageHeight()
    {
        return 480;
    }
}
