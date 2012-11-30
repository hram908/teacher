package com.zubrilka;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 4/15/12
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TitleProvider {

    /**
     * Returns the title of the view at position
     * @param position
     * @return
     */
    public String getTitle(int position);

}
