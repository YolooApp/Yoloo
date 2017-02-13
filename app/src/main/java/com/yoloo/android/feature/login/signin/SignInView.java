package com.yoloo.android.feature.login.signin;

import com.yoloo.android.framework.MvpView;

public interface SignInView extends MvpView {

  void onSignedIn();

  void onError(Throwable t);

  void onShowLoading();

  void onHideLoading();
}
