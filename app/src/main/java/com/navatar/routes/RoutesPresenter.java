package com.navatar.routes;

import android.support.annotation.Nullable;

/**
 * @author Chris Daley
 */
public class RoutesPresenter implements RoutesContract.Presenter {

    @Nullable
    private RoutesContract.View mRoutesView;

    @Override
    public void takeView(RoutesContract.View view) {
        mRoutesView = view;
    }

    @Override
    public void dropView() {
        mRoutesView = null;
    }
}