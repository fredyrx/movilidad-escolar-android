package com.ramos.fredy.goschool.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.bus.LocationSelectedEvent;
import com.ramos.fredy.goschool.dialog.DateDialog;
import com.ramos.fredy.goschool.models.AddDependent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDependentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.toolbar)                     Toolbar toolbar;
    @BindView(R.id.til_dependent_name)          TextInputLayout mTilName;
    @BindView(R.id.tie_dependent_name)          TextInputEditText mTieName;
    @BindView(R.id.til_dependent_last_name)     TextInputLayout mTilLastName;
    @BindView(R.id.tie_dependet_last_name)      TextInputEditText mTieLastName;
    @BindView(R.id.til_dependent_photo)         TextInputLayout mTilPhoto;
    @BindView(R.id.tie_dependent_photo)         TextInputEditText mTiePhoto;
    @BindView(R.id.img_dependent_photo)         ImageView mImgPhoto;
    @BindView(R.id.til_dependent_birthday)      TextInputLayout mTilBirthday;
    @BindView(R.id.tie_dependent_birthday)      TextInputEditText mTieBirthday;
    @BindView(R.id.til_dependent_home_address)  TextInputLayout mTilHomeAddress;
    @BindView(R.id.tie_dependent_home_address)  TextInputEditText mTieHomeAddress;
    @BindView(R.id.til_dependent_location)      TextInputLayout mTilLocation;
    @BindView(R.id.tie_dependent_location)      TextInputEditText mTieLocation;
    @BindView(R.id.btn_dependent_location)      Button mBtnSave;

    static final int REQUEST_TAKE_PHOTO = 1;

    private String mCurrentPhotoPath;
    private LatLng mLatLngSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dependent);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_dependent_photo)
    public void takePhoto() {
        dispatchTakePictureIntent();
    }

    @OnClick(R.id.btn_dependent_location)
    public void takeLocation() {
        startActivity(new Intent(this, DependentLocationActivity.class));
    }

    @OnClick(R.id.tie_dependent_birthday)
    public void showDatepicker() {
        DateDialog dialog = new DateDialog();
        dialog.show(getSupportFragmentManager(), "DATEPICKER");
    }

    @OnClick(R.id.fab_dependent_save)
    public void saveDependent() {
        AddDependent addDependent = new AddDependent();
        addDependent.setName(mTieName.toString().trim());
        addDependent.setLastName(mTieLastName.toString().trim());
        addDependent.setPhotoUri(mTiePhoto.toString().trim());
        addDependent.setBirthday(mTieBirthday.toString().trim());
        addDependent.setHomeAddress(mTieHomeAddress.toString().trim());

        if (mLatLngSelected != null) {
            addDependent.setLatitude(mLatLngSelected.latitude);
            addDependent.setLongitude(mLatLngSelected.longitude);
        } else {
            Toast.makeText(this, "Debe ingresar ubicación", Toast.LENGTH_SHORT).show();
            return;
        }

        //Enviar al servicio
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSelectedLocationEvent(LocationSelectedEvent event) {
        event = EventBus.getDefault().removeStickyEvent(LocationSelectedEvent.class);
        mLatLngSelected = event.getLatLng();
        mTieLocation.setText(event.getLocationToString());
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.ramos.fredy.goschool.fileprovider",
                        photoFile);

                //COMENTAR ESTA LÍNEA SI SE USA EN EL EMULADOR
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            galleryAddPic();
            mTiePhoto.setText(mCurrentPhotoPath);

            File file = new File(mCurrentPhotoPath);
            Uri contentUri = Uri.fromFile(file);

            Glide.with(AddDependentActivity.this)
                    .load(contentUri)
                    .into(mImgPhoto);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String fecha = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month, year);
        mTieBirthday.setText(fecha);
    }
}
