package com.example.kaspe.musicplayer.settings.mediaLibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaspe.musicplayer.R;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class AddFolderActivity extends Activity implements FolderAdapter.OnItemClickListener {

    private String[] rootFolders;
    private String path = "";
    private String pathShowed;
    private String[] subFolders;
    private int level = 0;
    RecyclerView recyclerView;
    TextView textViewFolderPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);
        initializeUI();
    }

    private void initializeUI() {

        recyclerView = findViewById(R.id.recyclerview_folder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        findRootFolder();
        FolderAdapter adapter = null;
        if(rootFolders.length == 2){
            adapter = new FolderAdapter(new String[]{"Internal Storage", "External Storage"});
        } else adapter = new FolderAdapter(new String[]{"Internal Storage"});
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        Button levelUpButton = findViewById(R.id.button_level_up);
        levelUpButton.setOnClickListener(levelUpListener);
    }

    @Override
    public void onClick(int position) {
        level++;
        if(level == 1){

            if(position == 0){
                pathShowed = "Internal Storage: ";
            } else  pathShowed = "External Storage: ";

        } else pathShowed += "/" + subFolders[position];
        path += "/" + subFolders[position];
        textViewFolderPath.setText(pathShowed);
        File file = new File(path);
        setNewAdapterUsingFile(file);
    }

    View.OnClickListener levelUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(level == 0) return;

            level--;
            if(level == 0){
                path = "";
                subFolders = rootFolders;
                setNewAdapterUsingSubFolder();
                textViewFolderPath.setText(R.string.root);
            } else {
                File file = new File(path);
                File parentFile = file.getParentFile();
                path = parentFile.getAbsolutePath();
                setNewAdapterUsingFile(parentFile);
                int index = pathShowed.lastIndexOf("/");
                pathShowed = pathShowed.substring(0, index);
                textViewFolderPath.setText(pathShowed);
            }
        }
    };

    private void setNewAdapterUsingFile(File file){
        String[] folders = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return new File(file, s).isDirectory();
            }
        });
        if (folders == null) {
            folders = new String[]{};
        }
        subFolders = folders;
        setNewAdapterUsingSubFolder();
    }

    private void setNewAdapterUsingSubFolder(){
        FolderAdapter adapter = null;
        if(level == 0){
            if(rootFolders.length == 2){
                adapter = new FolderAdapter(new String[]{"Internal Storage", "External Storage"});
            } else adapter = new FolderAdapter(new String[]{"Internal Storage"});
        } else {
            Arrays.sort(subFolders);
            adapter = new FolderAdapter(subFolders);
        }
        adapter.setOnItemClickListener(this);
        recyclerView.swapAdapter(adapter, true);
    }

    private void findRootFolder(){
        textViewFolderPath = findViewById(R.id.add_folder_path);

        textViewFolderPath.setText(R.string.root);

        String packageName = getApplicationInfo().packageName;
        String folder = "/Android/data/" + packageName + "/files";
        File[] files = getExternalFilesDirs(null);


        String[] folders = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            folders[i] = files[i].getAbsolutePath().replaceAll(folder, "");
        }
        rootFolders = folders;
        subFolders = folders;
    }
}
