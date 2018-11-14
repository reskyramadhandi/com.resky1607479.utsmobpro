package com.resky1607479.utsmobpro;

import java.util.List;

public interface OnGetCreditsCallback {
    void onSuccess(List<Credits> credits);

    void onError();
}
