package calculator.mathmate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;



public class eHistory  extends Activity {

    private ListView lv;
    private DBHelper dbHelper;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private String calcName="";
    private String []EmptyList={"There is  no result yet"};
    String timeStamp ;
            Bitmap mbitmap;
    Button captureScreenShot;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_history);

        dbHelper=new DBHelper(this);
        save=(Button) findViewById(R.id.bt_save_PDF);
//        captureScreenShot = (Button) findViewById(R.id.save);

        lv=(ListView)findViewById(R.id.listViewSS);

        calcName=getIntent().getStringExtra("calcName");

        //Create time stamp
        Date date = new Date() ;
       timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);


        if (shouldAskPermissions()) {
            askPermissions();
        }

        list=dbHelper.showHistory(calcName);
        if(!list.isEmpty())
            adapter=new ArrayAdapter<String>(this,R.layout.list,list);
        else
            adapter=new ArrayAdapter<String>(this,R.layout.list,EmptyList);
        lv.setAdapter(adapter);

        Button buttonSetPortrait = (Button)findViewById(R.id.setPortrait);
        Button buttonSetLandscape = (Button)findViewById(R.id.setLandscape);

        buttonSetPortrait.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

        });

        buttonSetLandscape.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

        });

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//                mbitmap = getBitmapOFRootView(captureScreenShot);
                String filename = "";
                FileOperations fop = new FileOperations();

                String item = (String) lv.getItemAtPosition(0);

                fop.write(calcName, item,timeStamp);
                if (fop.write(calcName, item,timeStamp)) {
                    Toast.makeText(getApplicationContext(),
                            calcName + timeStamp+".pdf created", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "I/O error",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(19)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    public void onClick(View view) {
        mbitmap = getBitmapOFRootView(captureScreenShot);

//        if(calcName=="threeeqn"){
//            savename="3var";
//        }
//        else if(calcName=="eqn"){
//            savename="Qudratic";
//        }
//        else if(calcName=="expofunc"){
//            savename="Exponential";
//        }
//        else if(calcName=="bisection"){
            createImagebisection(mbitmap);
//        }

        Toast.makeText(getApplicationContext(), "Saved :)", Toast.LENGTH_SHORT).show();
    }


//    private void createPdf() throws FileNotFoundException, DocumentException {
//
//        File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_DOCUMENTS), "pdfdemo");
//        if (!pdfFolder.exists()) {
//            pdfFolder.mkdir();
//            Log.i(LOG_, "Pdf Directory created");
//        }
//
//        //Create time stamp
//        Date date = new Date() ;
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
//
//        File myFile = new File(pdfFolder + timeStamp + ".pdf");
//
//        OutputStream output = new FileOutputStream(myFile);
//
//        //Step 1
//        DocumentsContract.Document document = new DocumentsContract.Document();
//
//        //Step 2
//        PdfWriter.getInstance(document, output);
//
//        //Step 3
//        document.open();
//
//        //Step 4 Add content
//        document.add(new Paragraph(mSubjectEditText.getText().toString()));
//        document.add(new Paragraph(mBodyEditText.getText().toString()));
//
//        //Step 5: Close the document
//        document.close();
//
//    }


    public Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        return bitmap1;
    }

    public void createImagebisection(Bitmap bmp) {
        int min = 0;
        int max = 1000000;



        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Pictures"+ File.separator + "Bisection" + i1 + ".jpg");

        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        dbHelper.deleteRecords(calcName);
        finish();
    }

}
