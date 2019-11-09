package com.example.androidthreadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private TextView text;
public static final int UPDATE_TEXT = 1;

    private Handler handler = new Handler( ){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case UPDATE_TEXT:
                    text.setText( "计科1705班张艳红"  );
                    break;
                default:
                    break;
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        text = (TextView)findViewById( R.id.text );
        Button changeText = (Button) findViewById( R.id.change_text );
        changeText.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.change_text:
                new Thread(new Runnable(){
                public void run() {
                   Message message = new Message();
                   message.what = UPDATE_TEXT;
                   handler.sendMessage( message );
            }
            }).start();
                break;
        default:
            break;
        }
    }
}
