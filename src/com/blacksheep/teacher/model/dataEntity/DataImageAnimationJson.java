package com.blacksheep.teacher.model.dataEntity;

/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 4/27/12
 * Time: 1:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataImageAnimationJson {

    private int id;
    private int animation_id;
    private String file_url;
    private String image_file_name;
    private int image_file_size;
    private boolean isSync;


    public DataImageAnimationJson(int id, int animation_id, String file_name, int file_size, String file_url, int isSync) {
        this.id = id;
        this.animation_id = animation_id;
        this.file_url = file_url;
        this.image_file_name = file_name;
        this.image_file_size = file_size;
        this.isSync = isSync==0?false:true;
    }

    public int getId() {
        return id;
    }

    public int getAnimation_id() {
        return animation_id;
    }

    public String getFile_url() {
        return file_url;
    }

    public String getImage_file_name() {
        return image_file_name;
    }

    public int getImage_file_size() {
        return image_file_size;
    }

    public boolean isSync() {
        return isSync;
    }
}
