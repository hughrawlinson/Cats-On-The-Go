//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.TextView;
//
//public class InstagramService extends Activity {
//    private static final String DEBUG_TAG = "Instagram Service";
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//    }
//
//    // When user clicks button, calls AsyncTask.
//    // Before attempting to fetch the URL, makes sure that there is a network connection.
//    public void myClickHandler(View view) {
//        // Gets the URL from the UI's text field.
//        String stringUrl = urlText.getText().toString();
//        ConnectivityManager connMgr = (ConnectivityManager)
//                getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            new DownloadWebpageTask().execute(stringUrl);
//        } else {
//            textView.setText("No network connection available.");
//        }
//    }
//
//    // Uses AsyncTask to create a task away from the main UI thread. This task takes a
//    // URL string and uses it to create an HttpUrlConnection. Once the connection
//    // has been established, the AsyncTask downloads the contents of the webpage as
//    // an InputStream. Finally, the InputStream is converted into a string, which is
//    // displayed in the UI by the AsyncTask's onPostExecute method.
//    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//
//            // params comes from the execute() call: params[0] is the url.
//            try {
//                return downloadUrl(urls[0]);
//            } catch (IOException e) {
//                return "Unable to retrieve web page. URL may be invalid.";
//            }
//        }
//        // onPostExecute displays the results of the AsyncTask.
//        @Override
//        protected void onPostExecute(String result) {
//            textView.setText(result);
//        }
//    }
//    ...
//}