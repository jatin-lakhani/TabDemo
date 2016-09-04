package com.tabdemo.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tabdemo.R;
import com.tabdemo.adapter.ContactsRecyclerAdapter;
import com.tabdemo.models.ContactInfo;
import com.tabdemo.models.ContactSResp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class ContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView recyclerView;
    ContactsRecyclerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        getActivity().setTitle("Contacts");

        getContacts();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new ContactsRecyclerAdapter(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return view;
    }

    ProgressDialog pd;

    private void getContacts() {

        try {

            pd = ProgressDialog.show(getContext(), "", "Loading...");
            String URL = "https://api.togglewave.com/rcci.svc/getcontacts";
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(60 * 2000);
            JSONObject jsonParams = new JSONObject();

            try {
                jsonParams.put("mynumber", "2145414112");
                jsonParams.put("apikey", "adasdsadsadsadsadsadsadd");
                StringEntity entity = new StringEntity(jsonParams.toString());
                client.post(getActivity(), URL, entity, "application/json", new ContactsRespHandler());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    class ContactsRespHandler extends AsyncHttpResponseHandler {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            try {
                String content = new String(responseBody, "UTF-8");
                Log.e("onsuccess", content + "--");
                ContactSResp contactSResp = new Gson().fromJson(content, ContactSResp.class);
                Collections.sort(contactSResp.getcontactsResult.listContactInfo, new CustomComparator());

                Log.e("onsuccess", new Gson().toJson(contactSResp.getcontactsResult.listContactInfo) + "--");
                mAdapter.addAll(contactSResp.getcontactsResult.listContactInfo);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


            error.printStackTrace();
            try {
                Log.e("onFailure", new String(responseBody, "UTF-8") + "--");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFinish() {
            super.onFinish();
            pd.dismiss();
        }
    }

    public class CustomComparator implements Comparator<ContactInfo> {
        @Override
        public int compare(ContactInfo o1, ContactInfo o2) {
            return o1.getCaption().compareTo(o2.getCaption());
        }
    }
}
