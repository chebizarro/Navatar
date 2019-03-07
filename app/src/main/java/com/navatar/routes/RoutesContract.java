package com.navatar.routes;

import com.navatar.BasePresenter;
import com.navatar.BaseView;

/**
 * @author Chris Daley
 */
public interface RoutesContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}