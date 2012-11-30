package com.testsocial.social.vk;

/**
 * Created with IntelliJ IDEA.
 * FriendFFFF: default
 * Date: 4/25/12
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class VkontakteError extends Throwable {

    private static final long serialVersionUID = 1L;

    private int mErrorCode = 0;
    private String mErrorType;

    public VkontakteError(String message) {
        super(message);
    }

    public VkontakteError(String message, String type, int code) {
        super(message);
        mErrorType = type;
        mErrorCode = code;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorType() {
        return mErrorType;
    }
}
