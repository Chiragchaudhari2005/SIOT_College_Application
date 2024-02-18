package com.example.siot;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    String branch,sem,mainFolder,subFolder;

    public void update(String fname, String url) {
        items.add(fname);
        urls.add(url);
        notifyDataSetChanged();
    }

    public NotesAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls,String branch,String sem) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls = urls;
        this.branch = branch;
        this.sem = sem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pdf_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameOfFile.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameOfFile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfFile = itemView.findViewById(R.id.notesText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerView.getChildLayoutPosition(view);
                    String filename = items.get(position);
                    Toast.makeText(context, "filename="+filename, Toast.LENGTH_SHORT).show();
                    // Get the proper Firebase Storage download URL for the file
                    setFolders();
                    getFirebaseStorageDownloadUrl(filename);
                }
            });
        }

        void setFolders(){
            if("CO".equals(branch) && "SEM1".equals(sem)){
                mainFolder = "COMP_ENGG";
                subFolder = "sem1";
            }
            else if("CO".equals(branch) && "SEM6".equals(sem)){
                mainFolder = "COMP_ENGG";
                subFolder = "sem6";
            }
        }

        private void getFirebaseStorageDownloadUrl(String filename) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

            // Replace "COMP_ENGG/sem6/" with the actual path to your files in Firebase Storage
            String filePath = mainFolder+"/"+subFolder+"/"+filename+ ".pdf";
            Log.d(filePath, "getFirebaseStorageDownloadUrl: "+filePath);
            Toast.makeText(context, "filepath="+filePath, Toast.LENGTH_SHORT).show();
            //String filePath = "COMP_ENGG/sem1/1708238256292.pdf";
            //gs://siot-3878e.appspot.com/COMP_ENGG/sem1/1708238256292.pdf
            StorageReference fileRef = storageRef.child(filePath);

            // Retrieve the download URL
            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // The download URL is available here
                    String downloadUrl = uri.toString();
                    Log.d("FirebaseStorage", "Download URL: " + downloadUrl);

                    // Now you can use 'downloadUrl' to open the PDF
                    openPdfWithIntent(downloadUrl);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors that occurred while retrieving the download URL
                    Log.e("FirebaseStorage", "Error getting download URL: " + exception.getMessage());
                    Toast.makeText(context, "Error getting download URL", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void openPdfWithIntent(String downloadUrl) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri pdfUri = Uri.parse(downloadUrl);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Error opening PDF", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
