package me.walten.fastgo.demo.model.local;

import javax.inject.Inject;

import me.walten.fastgo.base.mvp.BaseModel;
import me.walten.fastgo.base.mvp.IModel;

public class LocalModel extends BaseModel implements IModel {

    @Inject
    public LocalModel() {
        super(null);
    }


}