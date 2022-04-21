package com.example.shudong10.second;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shudong10.R;
import com.example.shudong10.second.chatdemo.DemoFragment.DemoDialogs;
import com.example.shudong10.second.chatdemo.data.fixtures.DialogsFixtures;
import com.example.shudong10.second.chatdemo.data.model.Dialog;
import com.example.shudong10.second.chatdemo.data.model.Message;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogsFragment extends DemoDialogs {


    private DialogsList dialogsList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogsFragment newInstance(String param1, String param2) {
        DialogsFragment fragment = new DialogsFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.GONE);
        return inflater.inflate(R.layout.fragment_dialogs, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialogsList =getView().findViewById(R.id.dialogsList);
        initAdapter();

    }
    @Override
    public void onDialogClick(Dialog dialog) {
        //DefaultMessagesActivity.open(requireActivity());
        Gson gson=new Gson();
        String dialogjson=gson.toJson(dialog);
        Bundle bundle=new Bundle();
        bundle.putString("dialog",dialogjson);
        NavController navController= Navigation.findNavController(getView());
        navController.navigate(R.id.action_dialogsFragment_to_messageListFragment,bundle);
    }
    private void initAdapter() {
        super.dialogsAdapter = new DialogsListAdapter<Dialog>(super.imageLoader);
        super.dialogsAdapter.setItems(DialogsFixtures.getDialogs());

        super.dialogsAdapter.setOnDialogClickListener(this);
        super.dialogsAdapter.setOnDialogLongClickListener(this);

        dialogsList.setAdapter(super.dialogsAdapter);
    }

    //for example
    private void onNewMessage(String dialogId, Message message) {
        boolean isUpdated = dialogsAdapter.updateDialogWithMessage(dialogId, message);
        if (!isUpdated) {
            //Dialog with this ID doesn't exist, so you can create new Dialog or update all dialogs list
        }
    }

    //for example
    private void onNewDialog(Dialog dialog) {
        dialogsAdapter.addItem(dialog);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}