package com.app.dermcop.ui.skin_cancer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.dermcop.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mClassification;
    private String mPercentage;

    private String predication;

    public ResultFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String mClassification, String mPercentage) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mClassification);
        args.putString(ARG_PARAM2, mPercentage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mClassification = getArguments().getString(ARG_PARAM1);
            mPercentage = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(
                R.layout.skin_cancer_fragment_result,
                container,
                false
        );

        final TextView textResult = root.findViewById(R.id.text_result);
        final TextView textResultInfo = root.findViewById(R.id.text_result_info);

        // set text depending on classification results
        if (mClassification.equals("Benign")) {
            String cancerDiagnosis = "Non-skin cancer";
            predication = cancerDiagnosis+"\n"+mPercentage + "% probability";
            textResult.setText(predication);
            textResultInfo.setText("RECOMMENDATIONS:\n" +
                    "1. The test was not malignant but it is still advisable to see a doctor near you for second opinion" +
                    "\n2. Search a local hospital or help center in your area throgh the get hospital near you feature");
        }
        else{
            String cancerDiagnosis = "Skin cancer";
            predication = cancerDiagnosis+"\n"+mPercentage + "% probability";
            textResult.setText(predication);
            textResultInfo.setText("RECOMMENDATIONS:" +
                    "1.Seek medical help as soon as possible because the result shows malignancy in the lesion " +
                    "\n2. Search a local hospital or help center in your area through the get hospital near you feature");
        }

        // get bitmap and display
        File mPhotoFile = ((CameraActivity) requireActivity()).getPhotoFile();
        ImageView lesionImage = root.findViewById(R.id.resultImage);
        Bitmap lesionBitmap = BitmapFactory.decodeFile(mPhotoFile.getPath());
        lesionImage.setImageBitmap(lesionBitmap);

        // save image
        try {
            storeImage(lesionBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button backButton = root.findViewById(R.id.backButtonResults);
        backButton.setOnClickListener(v -> getActivity().onBackPressed());

        return root;
    }

    // store image along with classification
    private void storeImage(Bitmap image) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.UK);
        Date now = new Date();
        String fileName = formatter.format(now);
        File pictureFile = new File(getContext().getFilesDir(), fileName + ".png");

        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(getContext().openFileOutput(fileName + ".txt", Context.MODE_PRIVATE));

        outputStreamWriter.write(predication);
        outputStreamWriter.close();

        FileOutputStream fos = new FileOutputStream(pictureFile);
        image.compress(Bitmap.CompressFormat.PNG, 90, fos);
        fos.close();

        File[] files = getContext().getFilesDir().listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
        }
    }
}
