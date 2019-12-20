package com.vupadhi.heyhelp.ui.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.HomeSlideAdapter;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractFragment;
import com.vupadhi.heyhelp.models.ProfileCompanyModel;
import com.vupadhi.heyhelp.mvp.contract.fragment.ProfileCompanyFragmentContract;
import com.vupadhi.heyhelp.mvp.presenter.fragment.ProfileCompanyFragmentImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.utils.GalleryUriToPath;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import id.zelory.compressor.Compressor;


public class ProfileCompanyFragment extends BaseAbstractFragment<ProfileCompanyFragmentImpl> implements ProfileCompanyFragmentContract, APIResponseCallback {

    HomeSlideAdapter homeSlideAdapter;
    CustomTextViewBold customTextViewBold;
    EditText profiletype_et,companyname_et,contactperson_et,companytype_et,email_et,mobile_et,location_et,address1_et,address2_et,pincode_et;

    APIResponseCallback apiResponseCallback;
    RelativeLayout  profile_rl;
    ImageView profileimg;

    UserSession userSession;
    String nClientid;
    String companyname,contactperson, email, mobile, profiletype, location,companytype,address1,address2,pincode;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    private static final int MY_PERMISSION_CODE = 321;
    public int REQUEST_CAMERA = 001;
    public int SELECT_FILE = 002;
    String picturePath = "", imageurlforcover = "";

    private Uri photoURI;
    String imageFilePath;
    private ProgressDialog progessDialog;

    private Uri picUri;

    private String base64pic;

    String type;
    private static final int MY_PERMISSIONS_REQUEST_USE_CAMERA = 900;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

 //       View view = inflater.inflate(R.layout.profile_company_fragment, container, false);
        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.profile_company_fragment, null);
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        apiResponseCallback=this;
        userSession = new UserSession(getActivity());
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        customTextViewBold=view.findViewById(R.id.save_changes_lay_out);

        profiletype_et=view.findViewById(R.id.profiletype_et);
        companyname_et=view.findViewById(R.id.companyname_et);
        contactperson_et=view.findViewById(R.id.contactperson_et);
        companytype_et=view.findViewById(R.id.companytype_et);
        email_et=view.findViewById(R.id.email_et);
        mobile_et=view.findViewById(R.id.mobile_et);
        location_et=view.findViewById(R.id.location_et);
        address1_et=view.findViewById(R.id.address1_et);
        address2_et=view.findViewById(R.id.address2_et);
        pincode_et=view.findViewById(R.id.pincode_et);
        profile_rl=view.findViewById(R.id.profile_rl);
        profileimg=view.findViewById(R.id.profileimg);


        companyname_et.setFocusable(false);
        contactperson_et.setFocusable(false);
        email_et.setFocusable(false);
        mobile_et.setFocusable(false);
        profiletype_et.setFocusable(false);
        location_et.setFocusable(false);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Log.d(TAG,"Permission not available requesting permission");
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_USE_CAMERA);
        } else {
            //  Log.d(TAG,"Permission has already granted");
        }

        profile_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }
        });


        Map<String, String> requestBody = new HashMap<>();
        presenter.my_profile(getActivity(), apiResponseCallback, requestBody, nClientid);


        customTextViewBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (companytype_et.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(), "Please enter company type", Toast.LENGTH_SHORT).show();

                }
                else if (address1_et.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(), "Please enter address1", Toast.LENGTH_SHORT).show();
                }else if (address2_et.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(), "Please enter address2", Toast.LENGTH_SHORT).show();
                }else if (pincode_et.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(), "Please enter pincode", Toast.LENGTH_SHORT).show();
                }else if (pincode_et.getText().toString().length()< 6)
                {
                    Toast.makeText(getActivity(), "Please enter valid pincode", Toast.LENGTH_SHORT).show();
                }else {

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("nRegisteredID", nClientid);
                    requestBody.put("nUserType", profiletype);
                    requestBody.put("sAddress", address1_et.getText().toString());
                    requestBody.put("sAddress2", address2_et.getText().toString());
                    requestBody.put("sPinCode", pincode_et.getText().toString());
                    requestBody.put("sClientImage",base64pic);
                    requestBody.put("sCompanyType", companytype_et.getText().toString());
//                    presenter.update_profile(getActivity(), apiResponseCallback, requestBody);

                }

//                Intent intent=new Intent(getActivity(), HomeScreenActivity.class);
//                startActivity(intent);
            }
        });

    }

    @Override
    public void setPresenter() {

        presenter = new ProfileCompanyFragmentImpl(this, getActivity());

    }

    private void selectImage() {
        final CharSequence[] items = {"Take photo", "from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("!Add Photos");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take photo")) {
//                    userChoosenTask="Take Photo";

                    checkPermission();
                    // checkPermission();
                } else if (items[item].equals("from Gallery")) {
//                    userChoosenTask="Choose from Library";


                    galleryPermission();

                    //  galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    protected void checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            // Do something, when permissions not granted
       /* if (ActivityCompat.shouldShowRequestPermissionRationale(
                getActivity(), Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(
                getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(
                getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // If we should give explanation of requested permissions

            // Show an alert dialog here with request explanation
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
            builder.setMessage("Camera, Read and Write External" +
                    " Storage permissions are required to do the task.");
            builder.setTitle("Please grant those permissions");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            },
                            MY_PERMISSIONS_REQUEST_CODE
                    );
                }
            });
            builder.setNeutralButton("Cancel", null);
            android.support.v7.app.AlertDialog dialog = builder.create();
            dialog.show();
        } else {*/
            // Directly request for required permissions, without explanation
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    MY_PERMISSIONS_REQUEST_CODE
            );

        } else {

            openCameraIntent();


            // Do something, when permissions are already granted
            // Toast.makeText(getApplicationContext(), "Permissions already granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void openCameraIntent() {

        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
        );
        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File file = null;
            File photoThumbnailFile = null;
            try {
                file = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            if (file != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    pictureIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 0);
                }else {
                    pictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 0);
                }
                photoURI = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", file);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                long fileSizeInBytes = file.length();
                float fileSizeInKB = fileSizeInBytes / 1024;
                System.out.println("/-/-/-/-/-/-  file size is fdg" + fileSizeInKB);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, REQUEST_CAMERA);
            }
        }
    }


    protected void galleryPermission() {
        if (ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Do something, when permissions not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // If we should give explanation of requested permissions

                // Show an alert dialog here with request explanation
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Read and Write External" +
                        " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                getActivity(),
                                new String[]{

                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },
                                MY_PERMISSION_CODE
                        );
                    }
                });
                builder.setNeutralButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSION_CODE
                );
            }
        } else {
            galleryIntent();


            // Do something, when permissions are already granted
            //Toast.makeText(getApplicationContext(), "Permissions already granted123", Toast.LENGTH_SHORT).show();
        }
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }


    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        } else if (requestCode == SELECT_FILE) {
            onSelectFromGalleryResult(data);
        } else if (requestCode == REQUEST_CAMERA) {
            if (!imageFilePath.isEmpty()) {
                try {
                    System.out.println("imageFilePath" + imageFilePath);
                    File file = new File(imageFilePath);
/*
                        Bitmap bitmap = MediaStore.Images.Media
                                .getBitmap(CraeteOffer.this.getContentResolver(), Uri.fromFile(file));
*/
                    //  Bitmap thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imageFilePath), 1024, 1024);
                    File sd = Environment.getExternalStorageDirectory();
                    File image = new File(imageFilePath);
                    //  BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    //   Bitmap thumbImage = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);

                    //  thumbImage = Bitmap.createScaledBitmap(thumbImage,parent.getWidth(),parent.getHeight(),true);
                    // imageView.setImageBitmap(bitmap);

                    Bitmap thumbImage = BitmapFactory.decodeFile(imageFilePath);
                    if (thumbImage != null) {
                        photoURI = Uri.fromFile(new File(imageFilePath));
                        //   addImageToGallery(imageFilePath, getActivity());
                        //   Bitmap bitmap1 = ImageUtil.setImage(imageFilePath, thumbImage);
                        // Bitmap afterconvert = scaleBitmapAndKeepRation(bitmap1, 400, 300);

                        // Bitmap bm=((BitmapDrawable)iv.getDrawable()).getBitmap();


                        // profilebit = (Bitmap) data.getExtras().get("data");
                        // ByteArrayOutputStream stream = new ByteArrayOutputStream();

                        File finalfile = new Compressor(getActivity()).compressToFile(file);


                        base64pic=encodeFileToBase64Binary(finalfile);
                        profileimg.setImageBitmap(thumbImage);


                        // imageurlfromcam = encodeFileToBase64Binary(finalfile);

                        //  new Async_BitmapWorkerTaskForProfile().execute();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";

        File storageDir =
                getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContext().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = GalleryUriToPath.getPath(getContext(), selectedImage);
                File pickedfile = new File(picturePath);
                //   File finalfile = ImageUtil.saveBitmapToFile(pickedfile);
                File finalfile = new Compressor(getActivity()).compressToFile(pickedfile);
                long fileSizeInBytes = pickedfile.length();
                float fileSizeInKB = fileSizeInBytes / 1024;

                long fileSizeInBytes12 = finalfile.length();
                float fileSizeInKB12 = fileSizeInBytes12 / 1024;
                System.out.println("/-/-/-/-/-/-  before compression" + fileSizeInKB);
                System.out.println("/-/-/-/-/-/-  after compression" + fileSizeInKB12);

                base64pic=encodeFileToBase64Binary(finalfile);
                profileimg.setImageBitmap(bm);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //  image12.setImageBitmap(bm);
        // new AccountActivity.uploadProfilePic().execute();
    }

    private static String encodeFileToBase64Binary(File file) throws Exception {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStreamReader.read(bytes);
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(bytes), "UTF-8");
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.MY_PROFILE == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    ProfileCompanyModel profileCompanyModel = new Gson().fromJson(responseString, ProfileCompanyModel.class);

                    companyname = profileCompanyModel.getData().getCompanyProfile().getSCompanyName();
                    contactperson = profileCompanyModel.getData().getCompanyProfile().getSContactPerson();
                    email = profileCompanyModel.getData().getCompanyProfile().getSEmail();
                    mobile = profileCompanyModel.getData().getCompanyProfile().getSMobileNumber();
                    profiletype = profileCompanyModel.getData().getCompanyProfile().getNUserType();
                    location = profileCompanyModel.getData().getCompanyProfile().getSLocationName();
                    address1 = profileCompanyModel.getData().getCompanyProfile().getSAddress();
                    address2 = profileCompanyModel.getData().getCompanyProfile().getSAddress2();
                    pincode = String.valueOf(profileCompanyModel.getData().getCompanyProfile().getSPinCode());
                    companytype = profileCompanyModel.getData().getCompanyProfile().getSCompanyType();


                   companyname_et.setText(companyname);
                   contactperson_et.setText(contactperson);
                    email_et.setText(email);
                    mobile_et.setText(mobile);

                    if (profiletype.equalsIgnoreCase("101")) {
                        profiletype_et.setText("Company");
                    }
                    location_et.setText(location);

                    if (address1!=null)
                    {
                        address1_et.setText(address1);
                    }else {

                        address1_et.setText("");

                    }
                    if (address2!=null)
                    {
                        address2_et.setText(address2);
                    }else {

                        address2_et.setText("");

                    }
                    if (pincode!=null)
                    {
                        pincode_et.setText(pincode);
                    }else {

                        pincode_et.setText("");

                    }
                    if (companytype != null) {
                        companytype_et.setText(companytype);
                    } else {

                        companytype_et.setText("");

                    }

                } else {

                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                }
            }else if (NetworkConstants.RequestCode.UPDATE_PROFILE == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    Toast.makeText(getActivity(), "Profile update successfully", Toast.LENGTH_SHORT).show();

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN,new Bundle());

                } else {

                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

}
