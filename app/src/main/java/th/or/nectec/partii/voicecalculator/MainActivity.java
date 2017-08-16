package th.or.nectec.partii.voicecalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import th.or.nectec.partii.embedded.android.EmbeddedUtils.ModelUtil;
import th.or.nectec.partii.embedded.android.RecognitionListener;
import th.or.nectec.partii.embedded.android.SpeechRecognizer;


public class MainActivity extends AppCompatActivity implements RecognitionListener, ModelUtil.OnReceiveStatusListener {

    private boolean isSetupRecognizer = false;
    private SpeechRecognizer recognizer;
    private ModelUtil mUtil;

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_0;
    private Button btn_add;
    private Button btn_sub;
    private Button btn_mul;
    private Button btn_div;
    private Button btn_exec;
    private Button btn_c;
    private TextView txt_result;
    private LinearLayout grp_voice;
    private Button btn_voice;
    private Button btn_stop;
    private Button btn_download;
    private Switch sw_voice;
    private TextView txt_apikey;
    private TextView txt_asr;
    private LinearLayout lyt_control = null;
    private LinearLayout lyt_apikey = null;

    private String num1;
    private String num2;
    private String oper;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        mUtil = new ModelUtil();
        mUtil.setOnReceiveDialogStatus(MainActivity.this);


        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_exec = (Button) findViewById(R.id.btn_exec);
        btn_c = (Button) findViewById(R.id.btn_c);
        txt_result = (TextView) findViewById(R.id.txt_result);
        grp_voice = (LinearLayout) findViewById(R.id.grp_voice);
        sw_voice = (Switch) findViewById(R.id.sw_voice);
        btn_voice = (Button) findViewById(R.id.btn_voice);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_download = (Button) findViewById(R.id.btn_download);
        txt_apikey = (TextView) findViewById(R.id.txt_apikey);
        txt_asr = (TextView) findViewById(R.id.txt_asr);
        lyt_control = (LinearLayout) findViewById(R.id.lyt_control);
        lyt_apikey = (LinearLayout) findViewById(R.id.lyt_apikey);
        num1 = "";
        num2 = "";
        oper = "";

        setupActions();

        if(mUtil.isPermissionGranted(context)) {
            if(mUtil.isSyncDir(getExternalFilesDir("")) && !isSetupRecognizer) {
                setUpRecognizer();
                lyt_control.setVisibility(View.VISIBLE);
                lyt_apikey.setVisibility(View.GONE);
            }
        }
        else {
            mUtil.requestPermission(context);
            lyt_control.setVisibility(View.GONE);
            lyt_apikey.setVisibility(View.VISIBLE);
        }
    }

    private void setupActions() {
        sw_voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                grp_voice.setEnabled(isChecked);
                disableEnableControls(isChecked, (ViewGroup)grp_voice);
            }
        });

        View.OnClickListener on_btn_0 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "0";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "0";
                    txt_result.setText(num2);
                }
                System.err.println("btn_0 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_0.setOnClickListener(on_btn_0);

        View.OnClickListener on_btn_1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "1";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "1";
                    txt_result.setText(num2);
                }
                System.err.println("btn_1 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_1.setOnClickListener(on_btn_1);

        View.OnClickListener on_btn_2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "2";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "2";
                    txt_result.setText(num2);
                }
                System.err.println("btn_2 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_2.setOnClickListener(on_btn_2);

        View.OnClickListener on_btn_3 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "3";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "3";
                    txt_result.setText(num2);
                }
                System.err.println("btn_3 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_3.setOnClickListener(on_btn_3);

        View.OnClickListener on_btn_4 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "4";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "4";
                    txt_result.setText(num2);
                }
                System.err.println("btn_4 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_4.setOnClickListener(on_btn_4);

        View.OnClickListener on_btn_5 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "5";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "5";
                    txt_result.setText(num2);
                };
                System.err.println("btn_5 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_5.setOnClickListener(on_btn_5);

        View.OnClickListener on_btn_6 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "6";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "6";
                    txt_result.setText(num2);
                }
                System.err.println("btn_6 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_6.setOnClickListener(on_btn_6);

        View.OnClickListener on_btn_7 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "7";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "7";
                    txt_result.setText(num2);
                }
                System.err.println("btn_7 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_7.setOnClickListener(on_btn_7);

        View.OnClickListener on_btn_8 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "8";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "8";
                    txt_result.setText(num2);
                }
                System.err.println("btn_8 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_8.setOnClickListener(on_btn_8);

        View.OnClickListener on_btn_9 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oper == "") {
                    num1 = num1 + "9";
                    txt_result.setText(num1);
                }
                else {
                    num2 = num2 + "9";
                    txt_result.setText(num2);
                }
                System.err.println("btn_9 --> num1 = " + num1 + ", num2 = " + num2);
            }
        };
        btn_9.setOnClickListener(on_btn_9);

        View.OnClickListener on_btn_c = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = "";
                num2 = "";
                oper = "";
                txt_result.setText("0");
                System.err.println("btn_c --> num1 = " + num1 + ", num2 = " + num2 + ", oper = " + oper);
            }
        };
        btn_c.setOnClickListener(on_btn_c);

        View.OnClickListener on_btn_add = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oper = "+";
                System.err.println("btn_add --> num1 = " + num1 + ", num2 = " + num2 + ", oper = " + oper);
            }
        };
        btn_add.setOnClickListener(on_btn_add);

        View.OnClickListener on_btn_sub = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oper = "-";
                System.err.println("btn_sub --> num1 = " + num1 + ", num2 = " + num2 + ", oper = " + oper);
            }
        };
        btn_sub.setOnClickListener(on_btn_sub);

        View.OnClickListener on_btn_mul = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oper = "*";
                System.err.println("btn_mul --> num1 = " + num1 + ", num2 = " + num2 + ", oper = " + oper);
            }
        };
        btn_mul.setOnClickListener(on_btn_mul);

        View.OnClickListener on_btn_div = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oper = "/";
                System.err.println("btn_div --> num1 = " + num1 + ", num2 = " + num2 + ", oper = " + oper);
            }
        };
        btn_div.setOnClickListener(on_btn_div);

        View.OnClickListener on_btn_exec = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.err.println("btn_exec --> num1 = " + num1 + ", num2 = " + num2 + ", oper = " + oper);
                String result;
                if (oper == "+") {
                    result = String.valueOf(Integer.parseInt(num1) + Integer.parseInt(num2));
                }
                else if (oper == "-") {
                    result = String.valueOf(Integer.parseInt(num1) - Integer.parseInt(num2));
                }
                else if (oper == "*") {
                    result = String.valueOf(Integer.parseInt(num1) * Integer.parseInt(num2));
                }
                else {
                    result = String.valueOf(Integer.parseInt(num1) / Integer.parseInt(num2));
                }
                num1 = "";
                num2 = "";
                oper = "";
                txt_result.setText(result);
            }
        };
        btn_exec.setOnClickListener(on_btn_exec);



        btn_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recognizer.startListening();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recognizer.stop();
            }
        });

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apikey = txt_apikey.getText().toString();

                if (apikey.trim() != "") {
                    mUtil.startDownload(context, MainActivity.this, getExternalFilesDir(""), apikey);
                }
            }
        });
    }

    private void Text2Num(String text) {
        int number = 0;

        switch (text) {
            case "เท่ากับ":
                btn_exec.callOnClick();
                break;
            case "ล้าง":
            case "เริ่มใกม่":
                btn_c.callOnClick();
                break;
            case "บวก":
                btn_add.callOnClick();
                break;
            case "ลบ":
                btn_sub.callOnClick();
                break;
            case "คูณ":
                btn_mul.callOnClick();
                break;
            case "หาร":
                btn_div.callOnClick();
                break;
            case "หนึ่ง" :
                btn_1.callOnClick();
                break;
            case "สอง" :
                btn_2.callOnClick();
                break;
            case "สาม" :
                btn_3.callOnClick();
                break;
            case "สี่" :
                btn_4.callOnClick();
                break;
            case "ห้า" :
                btn_5.callOnClick();
                break;
            case "หก" :
                btn_6.callOnClick();
                break;
            case "เจ็ด" :
                btn_7.callOnClick();
                break;
            case "แปด" :
                btn_8.callOnClick();
                break;
            case "เก้า" :
                btn_9.callOnClick();
                break;
            case "ศูนย์" :
                btn_0.callOnClick();
                break;
        }
    }

    private void disableEnableControls(boolean enable, ViewGroup vg){
        for (int i = 0; i < vg.getChildCount(); i++){
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup){
                disableEnableControls(enable, (ViewGroup)child);
            }
        }
    }

    private void setUpRecognizer(){
        Log.d("Recognizer", "Setting recognizer");

        try {
            recognizer = mUtil.getRecognizer(context);
            if (recognizer.getDecoder() == null) {
                finish();
            }
            recognizer.addListener(this);
            isSetupRecognizer = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onProgress(int i) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onPartialResult(String s) {

    }

    @Override
    public void onResult(String s) {
        if (s != null) {
            if (!s.equals(SpeechRecognizer.NO_HYP) &&
                    !s.equals(SpeechRecognizer.REQUEST_NEXT)) {
                System.err.println("s = " + s);
                txt_asr.setText(s);
                Text2Num(s);
            }
        }

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onReceiveDownloadComplete() {
        Log.d("Recognizer", "DownloadComplete");

        lyt_control.setVisibility(View.VISIBLE);
        lyt_apikey.setVisibility(View.GONE);
        setUpRecognizer();

    }

    @Override
    public void onReceiveDownloadFailed() {

    }
}
