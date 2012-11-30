package com.blacksheep.teacher.model.dataEntity;

/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 4/27/12
 * Time: 5:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataAnimationToAudio {

    private String audio_file_name;
    private String file_url;
    private int animation_id;
    private int id;

    public DataAnimationToAudio(int id, String audio_file_name, int animation_id) {
        this.id = id;
        this.audio_file_name = audio_file_name;
        this.animation_id = animation_id;
    }
    public DataAnimationToAudio(int id, String audio_file_name, String file_url, int animation_id) {
        this.id = id;
        this.audio_file_name = audio_file_name;
        this.file_url = file_url;
        this.animation_id = animation_id;
    }

    public String getAudio_file_name() {
        return audio_file_name;
    }

    public int getAnimation_id() {
        return animation_id;
    }

    public int getId() {
        return id;
    }

    public String getFile_url() {
        return file_url;
    }
}
