package ${packageName};

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import butterknife.BindView;
import me.walten.fastgo.di.component.AppComponent;
import com.gyf.barlibrary.ImmersionBar;

public class ${activityClass} extends XMVPActivity<${presenterClass}> implements ${contractClass}.View {

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
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        // TODO: 请手动在 ActivityComponent 中添加inject()方法

    }

    @Override
    protected boolean enableImmersive(ImmersionBar immersionBar) {
        // TODO: 状态栏可以自己设置  return true 生效 反之不生效
        immersionBar.statusBarColor(R.color.colorPrimary);
        return true;
    }

}