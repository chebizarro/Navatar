package com.navatar.navigation;

import javax.inject.Inject;

import dagger.android.DaggerFragment;

public class NavigationFragment extends DaggerFragment implements NavigationContract.View {

    @Inject
    NavigationContract.Presenter mPresenter;

    @Inject
    public NavigationFragment() {}


}