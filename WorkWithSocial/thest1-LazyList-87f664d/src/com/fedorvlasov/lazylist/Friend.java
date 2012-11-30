package com.fedorvlasov.lazylist;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 30.05.12
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
public class Friend {

    private String image;
    private String name;
    private String id;

    public Friend(String image, String name, String id) {
        this.image = image;
        this.name = name;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
