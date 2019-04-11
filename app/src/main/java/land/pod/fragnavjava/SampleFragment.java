package land.pod.fragnavjava;


import android.os.Bundle;
import android.view.View;

/**
 * Created by f.zivdar on 3/26/16.
 */
public class SampleFragment extends BaseFragment {

    public static SampleFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentNavigation != null) {
                    mFragmentNavigation.pushFragment(SampleFragment.newInstance(mInt + 1));
                }
            }
        });
        mButton.setText(getClass().getSimpleName() + " " + mInt);
    }
}