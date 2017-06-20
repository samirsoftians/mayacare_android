package com.felixtechlabs.mayacare.features.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.felixtechlabs.mayacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base Activity, all other activities should extend from this.
 * Created by rohan on 20/6/17.
 */

public abstract class MCBaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(getActivity());
        configureToolbar();
    }


    protected abstract Activity getActivity();

    protected abstract
    @LayoutRes
    int getLayout();

    public abstract boolean isToolbarPresent();

    private void configureToolbar() {
        if (isToolbarPresent() && mToolbar != null)
            setSupportActionBar(mToolbar);
    }

    protected void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    protected void enableHome() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void start(Class clazz) {
        getActivity().startActivity(new Intent(getActivity(), clazz));
    }

    protected Intent getNewIntent(Class clazz) {
        return new Intent(getActivity(), clazz);
    }

    protected void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
