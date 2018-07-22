package calculator.mathmate;

/**
 * Created by deepesh on 2/25/17.
 */

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;

import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

import java.io.FileInputStream;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class FileOperations {
    public FileOperations() {
    }

    public Boolean write(String fname,String item,String timeStamp) {
        try {

            File mydir = new File(Environment.getExternalStorageDirectory() + "/MathMate/");
            if(!mydir.exists())
                mydir.mkdirs();
            else{
                Log.d("error", "dir. already exists");}

            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"MathMate"+File.separator+fname+timeStamp+".pdf");
            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // step 1
            Document document = new Document(PageSize.A4.rotate());

            // step 2
            PdfWriter.getInstance(document,new FileOutputStream(file.getAbsoluteFile()));
//            Rectangle x = new Rectangle(1500,5000);
//            document.setPageSize(x);
            // step 4
            document.open();

//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            Image myImg = Image.getInstance(stream.toByteArray());
            document.add(new Paragraph(item));
            // step 5
            document.close();

            Log.d("Suceess", "Sucess");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /** getResizedBitmap method is used to Resized the Image according to custom width and height
     * @param image
     * @param newHeight (new desired height)
     * @param newWidth (new desired Width)
     * @return image (new resized image)
     * */
//    public static Bitmap getResizedBitmap(Bitmap image, int newHeight, int newWidth) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        // create a matrix for the manipulation
//        Matrix matrix = new Matrix();
//        // resize the bit map
//        matrix.postScale(scaleWidth, scaleHeight);
//        // recreate the new Bitmap
//        Bitmap resizedBitmap = Bitmap.createBitmap(image, 0, 0, width, height,
//                matrix, false);
//        return resizedBitmap;
//    }
//
//    public String read(String fname) {
//        BufferedReader br = null;
//        String response = null;
//        try {
//            StringBuffer output = new StringBuffer();
//            String fpath = "/sdcard/" + fname + ".pdf";
//
//            PdfReader reader = new PdfReader(new FileInputStream(fpath));
//            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
//
//            StringWriter strW = new StringWriter();
//
//            TextExtractionStrategy strategy;
//            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
//                strategy = parser.processContent(i,
//                        new SimpleTextExtractionStrategy());
//
//                strW.write(strategy.getResultantText());
//
//            }
//
//            response = strW.toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return response;
//    }
}