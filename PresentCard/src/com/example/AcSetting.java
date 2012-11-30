package com.example;

import android.app.Activity;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 4/18/12
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcSetting extends Activity {
    EditText editTextName;
    public static String SETTINGS_DESK="settingsDesc";
    public static int SETTINGS_DESK_YELLOW=0;
    public static int SETTINGS_DESK_GREEN=1;
    public static int SETTINGS_DESK_RED=2;
    public static int SETTINGS_DESK_BLUE=3;
    public static int SETTINGS_DESK_VIOLET=4;
    /*ProgressDialog mProgressDialog;
    @Override
    protected void onStop() {

        String nameHint = editTextName.getText().toString().toLowerCase();
        String nameText = editTextName.getHint().toString().toLowerCase();

        if (!nameHint.equals(nameText) && !nameHint.equals("")) {
            SharedPreferences.Editor stats_editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            LinkedList<DataNames> dataNames = createNameList();
            int numberName = checkName(dataNames, nameHint);
            if (numberName >= 0) {
                String nameStandart = dataNames.get(numberName).getDataName(DataNames.keyNameStandart);
                String pathStandart = dataNames.get(numberName).getDataName(DataNames.keyPathStandart);
                String pathStrogoe = dataNames.get(numberName).getDataName(DataNames.keyPathStrogoe);
                String pathLascatelnoe = dataNames.get(numberName).getDataName(DataNames.keyPathLascatelnoe);
                stats_editor.putString("nameStandart", nameStandart);
                stats_editor.putString("pathStandart", pathStandart);
                stats_editor.putString("pathStrogoe", pathStrogoe);
                stats_editor.putString("pathLascatelnoe", pathLascatelnoe);
            } else {

                String nameStandart = nameHint;
                stats_editor.putString("nameStandart", nameStandart);
                stats_editor.putString("pathStandart", "-");
                stats_editor.putString("pathStrogoe", "-");
                stats_editor.putString("pathLascatelnoe", "-");
            }

            stats_editor.commit();
        }

         Downloader[0] = false;

        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
    }

    FacebookWorker facebookWorker;
    VkontakteWorker vkontakteWorker;

    public boolean [] Downloader =new boolean[1];

    private void buildProgress()
    {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("A message");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_setting);
        facebookWorker = new FacebookWorker();
        facebookWorker.logout(this);
        vkontakteWorker = new VkontakteWorker();
        setEditTextName();
        setRadioButtonsDesc();
        buildProgress();
        Downloader[0]=true;

    }
    private void setEditTextName()
    {
        editTextName = (EditText) findViewById(R.id.AcSettingsName);
        editTextName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        TextView desk=(TextView) findViewById(R.id.AcSettingsColorDesk);
        TextView nameUser=(TextView) findViewById(R.id.AcSettingsChooseYourName);
        TextView social=(TextView) findViewById(R.id.AcSettingsEnterIn);
        Button forParents=(Button) findViewById(R.id.AcSettingsForParents);
        Button resetGame=(Button) findViewById(R.id.AcSettingsResetGame);
        desk.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        nameUser.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        social.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        forParents.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        resetGame.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String nameFromPrefs = prefs.getString("nameStandart", "-");
        if (nameFromPrefs.equals("-")) {
            editTextName.setHint("Введи свое имя:");
        } else editTextName.setHint(nameFromPrefs);


    }
    private void setRadioButtonsDesc()
    {
        RadioButton rb1=(RadioButton)findViewById(R.id.ac_settings_desk1);
        RadioButton rb2=(RadioButton)findViewById(R.id.ac_settings_desk2);
        RadioButton rb3=(RadioButton)findViewById(R.id.ac_settings_desk3);
        RadioButton rb4=(RadioButton)findViewById(R.id.ac_settings_desk4);
        RadioButton rb5=(RadioButton)findViewById(R.id.ac_settings_desk5);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        switch (prefs.getInt(SETTINGS_DESK,SETTINGS_DESK_GREEN))
        {
            case 0:{
                rb1.setChecked(true);
                break;
            }
            case 1:{
                rb2.setChecked(true);
                break;
            }
            case 2:{
                rb3.setChecked(true);
                break;
            }
            case 3:{
                rb4.setChecked(true);
                break;
            }
            case 4:{
                rb5.setChecked(true);
                break;
            }
            default:break;

        }
        rb1.setOnCheckedChangeListener(new RadioListener(SETTINGS_DESK_YELLOW));
        rb2.setOnCheckedChangeListener(new RadioListener(SETTINGS_DESK_GREEN));
        rb3.setOnCheckedChangeListener(new RadioListener(SETTINGS_DESK_RED));
        rb4.setOnCheckedChangeListener(new RadioListener(SETTINGS_DESK_BLUE));
        rb5.setOnCheckedChangeListener(new RadioListener(SETTINGS_DESK_VIOLET));
    }
    private class RadioListener implements CompoundButton.OnCheckedChangeListener
    {
        int numberDesc;

        private RadioListener(int numberDesc) {
            this.numberDesc = numberDesc;
        }

        SharedPreferences.Editor stats_editor = PreferenceManager.getDefaultSharedPreferences(AcSetting.this).edit();
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b)
            {
                 stats_editor.putInt(SETTINGS_DESK,numberDesc);
                 stats_editor.commit();
            }
        }
    }
    private int checkName(LinkedList<DataNames> dataNames, String name) {
        int count = dataNames.size();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 5; j++) {
                if (dataNames.get(i).getDataName(j).toLowerCase().equals(name.toLowerCase())) return i;
            }
        }
        return -1;
    }

    private LinkedList<DataNames> createNameList() {
        BufferedReader in = null;
        LinkedList<DataNames> names = new LinkedList<DataNames>();
        try {
            in = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.names), "utf8"));
            while (in.ready()) {
                for (int i = 0; i < 121; i++) {
                    String a = in.readLine();
                    String[] aa = a.split("\t");
                    names.add(new DataNames(i, aa[1], aa[2], aa[3], aa[4], aa[5], aa[6], aa[7], aa[8]));

                }

            }
            in.close();
        } catch (Exception ex) {
        }
        return names;

    }
    public void clickEnterFb(View v) {
        facebookWorker.authorizeI(this);

    }


    public void clickEnterVk(View v) {
        //facebookWorker.postI(String.valueOf(System.currentTimeMillis()),this);
        vkontakteWorker.authorizeI(this);

    }

    public void clickTestTEst(View v) {

        try {
            vkontakteWorker.postWallI("test test", this);
        } catch (VkontakteError vkontakteError) {
            vkontakteError.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public void clickGoInParentSetting(View view) {
        startActivity(new Intent(this, AcSettingParent.class));
    }

    DownloadFile downloadFile;
    public void clickLoadRes(View v)
    {
        // DBManager.getInstance().getImagesAnimationsByAnimationId(1);
//        DBManager.getInstance().getAnimationById(1);
//        DBManager.getInstance().getAnimations();

//        SuperLoader superLoader = new SuperLoader();
//
//        try {
//            superLoader.loadContentToLectureForDB();
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        try {
//            superLoader.loadAnimationInfoForDb();
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        try {
//            DBManager.getInstance().getContentForLecture(1);
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        try {
//            superLoader.loadAudioForLectureForDb();
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//
//        try {
//            superLoader.setOnLoadListener(new SuperLoader.onLoadListener() {
//                @Override
//                public void load(int i, boolean firs) {
//
//                }
//            });
//            superLoader.loadImages();
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }


        downloadFile = new DownloadFile();
        downloadFile.execute("download");


    }

    public void clickBack(View v)
    {
        finish();
    }
    private class DownloadFile extends AsyncTask<String, Integer, String> {

        private NotificationHelper mNotificationHelper;

        int count;

        public DownloadFile()
        {
            mNotificationHelper = new NotificationHelper(AcSetting.this);
        }

        @Override
        protected String doInBackground(String... sUrl) {
            SuperLoader superLoader = new SuperLoader();

            try {
                superLoader.loadContentToLectureForDB();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            publishProgress(1);
            try {
                superLoader.loadAnimationInfoForDb();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            publishProgress(2);
            try {
                superLoader.loadImagesForDb();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            publishProgress(3);
            try {
                superLoader.loadAudioForLectureForDb();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            publishProgress(4);

            try {
                superLoader.setOnLoadListener(new SuperLoader.onLoadListener() {
                    @Override
                    public void load(int i, boolean firs,String txt) {
                        if(firs)
                            count=i;
                        else
                        {
                            currentImage = txt;
                            publishProgress((int)((float)i/count*100));
                        }
                    }
                });
                superLoader.loadImages(Downloader);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return null;
        }

        String currentImage="";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mProgressDialog.show();
            mNotificationHelper.createNotification();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mProgressDialog.dismiss();
          //  mNotificationHelper.completed();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            if(progress[0]!=0)
                //mProgressDialog.setProgress(progress[0]);
            mNotificationHelper.progressUpdate(progress[0],currentImage);
        }
    }
*/
}
