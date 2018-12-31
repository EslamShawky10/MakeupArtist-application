package com.eslamshawky.hp.makeupartist.Register;

import com.eslamshawky.hp.makeupartist.RegisterFirebase.Register;

public interface RegisterInterfaces {
    interface RegisterView{
        void Succes(String message);
        void error(String message);
    }
    interface RegisterPresenter{
       void Register(String username,String password);
       void selectImage();
       void encoding();

    }
}
