package tw.com.abc.myviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void clearMyView(View view){
        myView.clear();
    }
    public void undoMyView(View view){
        myView.undo();
    }
    public void redoMyView(View view){
        myView.redo();
    }
}
