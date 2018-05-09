package com.liam.fragment_lifecycle;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements
    BlankFragment.OnFragmentInteractionListener {

  private static final String BTN_TXT = "main:btn_txt";
  private static final String TAG = MainActivity.class.getSimpleName();
  private BlankFragment fragment;
  private Button btn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btn = findViewById(R.id.button);
    btn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment != null) {
          ft.remove(fragment).commit();
          fragment = null;
          btn.setText("添加Fragment");
        } else {
          fragment = BlankFragment.newInstance("", "");
          ft.add(R.id.container, fragment).commit();
          btn.setText("移除Fragment");
        }
      }
    });
    Log.d(TAG, "onCreate: ");
  }

  @Override
  public void onAttachFragment(Fragment fragment) {
    super.onAttachFragment(fragment);
    if (fragment instanceof BlankFragment) {
      this.fragment = (BlankFragment) fragment;
    }
    Log.d(TAG, "onAttachFragment: ");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(BTN_TXT, btn.getText().toString());
    Log.d(TAG, "onSaveInstanceState: ");
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    btn.setText(savedInstanceState.getString(BTN_TXT));
    Log.d(TAG, "onRestoreInstanceState: ");
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }
}
