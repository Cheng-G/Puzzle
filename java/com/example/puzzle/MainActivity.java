package com.example.puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private PuzzleLayoutView puzzleLayoutView;
    private TextView mLevel, mTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mLevel = this.findViewById(R.id.id_level);
        mTime = this.findViewById(R.id.id_time);
        puzzleLayoutView = this.findViewById(R.id.puzzle_layout_view);
        puzzleLayoutView.setTimeEnabled(true);
        //监听事件
        puzzleLayoutView.setOnGamePintuListner(new PuzzleLayoutView.GamePintuListner() {
            public void timechanged(int currentTime) {
            //此处为int 注意加""
            mTime.setText(currentTime + "秒");
            }

            public void nextLevel(final int nextLevel) {
            //弹出提示框
                new AlertDialog.Builder(MainActivity.this).setTitle("游戏信息").setMessage("游戏升级").setPositiveButton("进入下一关", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //游戏结束后,调用下一关
                         puzzleLayoutView.nextLevel();
                        mLevel.setText("第" + +nextLevel + "关");
                    }
              }).show();
            }

             public void gameover() {
                //弹出提示框
                new AlertDialog.Builder(MainActivity.this).setTitle("游戏信息").setMessage("游戏失败").setPositiveButton("继续该关卡", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        puzzleLayoutView.restart();//重新启动
                    }
                }).setNegativeButton("放弃该游戏", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                     finish();
                    }
                }).show();
            }
        });
    }

}