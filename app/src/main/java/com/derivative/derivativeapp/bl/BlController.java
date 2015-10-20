package com.derivative.derivativeapp.bl;

/**
 * Created by FD on 2015/10/20.
 */
public class BlController implements BlService {
    private static  BlService  blController = new BlController();
    private BlController()
    {

    }

    public static BlService instance()
    {
        return  blController;
    }

    @Override
    public String[] getUserInfo() {
        return null;
    }
}
