package ${packageName};

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.walten.fastgo.base.fragment.MVPFragment;
import me.walten.fastgo.di.component.AppComponent;


public class ${fragmentClass} extends MVPFragment<${presenterClass}> implements ${contractClass}.View
{


    public ${fragmentClass}() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutResId() {
        return R.layout.${layoutName};
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setupComponent(@NonNull AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .fragmentModule(new FragmentModule(this))
                .build()
                .inject(this);

        // TODO: 请手动在 FragmentComponent 中添加inject()方法
    }
}

