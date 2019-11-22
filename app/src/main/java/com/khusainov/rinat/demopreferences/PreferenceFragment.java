package com.khusainov.rinat.demopreferences;

import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class PreferenceFragment extends PreferenceFragmentCompat {

    public static final String ARG_ROOT = "root_screen";

    public static PreferenceFragment newInstance(String root) {

        Bundle args = new Bundle();
        args.putString(ARG_ROOT, root);

        PreferenceFragment fragment = new PreferenceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, getArguments().getString(ARG_ROOT));

        Preference toastPreference = findPreference(getString(R.string.pref_key_toast));
        if (toastPreference != null) {
            toastPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(requireContext(), "onPreferenceClickListener", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        Preference listPreference = findPreference(getString((R.string.pref_key_list)));
        if (listPreference != null) {
            listPreference.setSummaryProvider(new Preference.SummaryProvider() {
                @Override
                public CharSequence provideSummary(Preference preference) {
                    String value = ((ListPreference) preference).getValue();

                    if (value != null) {
                        return getResources().getStringArray(R.array.demo_list_help)[Integer.valueOf(value)];
                    }
                    return null;
                }

            });
        }
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (getString(R.string.pref_key_toast).equals(preference.getKey())) {
            Toast.makeText(requireContext(), "onPreferenceTeeClick", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }
}
