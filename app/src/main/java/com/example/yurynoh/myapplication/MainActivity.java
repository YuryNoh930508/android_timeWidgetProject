package com.example.yurynoh.myapplication;

import android.icu.util.Calendar;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Chronometer bookTime;
    Button book, bookDone;
    RadioGroup dateTime;
    CalendarView calendarView;
    TimePicker timePicker;
    TextView bookMsg;
    int year, month, day, hour, mininute;
    Toast date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookTime = (Chronometer)findViewById(R.id.bookTime);
        book = (Button)findViewById(R.id.book);
        bookDone = (Button)findViewById(R.id.bookDone);
        dateTime = (RadioGroup)findViewById(R.id.dateTime);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        bookMsg = (TextView)findViewById(R.id.bookMsg);
        year = -1; month = -1; day = -1; hour = -1; mininute = -1;
        date = Toast.makeText(this, "날짜를 고르세요!", Toast.LENGTH_SHORT);
        time = Toast.makeText(this, "시간을 고르세요!", Toast.LENGTH_SHORT);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int y, int m, int d) {
                year = y;
                month = m;
                day = d;
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int h, int m) {
                hour = h;
                mininute = m;
            }
        });

        View.OnClickListener button =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.book) {
                    year = -1; month = -1; day = -1;
                    hour = -1; mininute = -1;
                    bookTime.setBase(SystemClock.elapsedRealtime());
                    bookTime.start();
                    bookMsg.setText("");
                } else if(v.getId() == R.id.bookDone) {
                    if(year == -1 || month == -1 || day == -1) {
                        date.show();
                    } else if(hour == -1 || mininute == -1) {
                        time.show();
                    } else {
                        bookTime.stop();
                        bookMsg.setText(year+"년"+month+"월"+day+"일 "+hour+"시"+mininute+"분 예약됨");
                    }
                }
            }
        };

        book.setOnClickListener(button);
        bookDone.setOnClickListener(button);

        dateTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.datePick) {
                    calendarView.setVisibility(View.VISIBLE);
                    timePicker.setVisibility(View.GONE);
                    calendarView.requestLayout();
                } else if(checkedId == R.id.timePick) {
                    timePicker.setVisibility(View.VISIBLE);

                    calendarView.setVisibility(View.GONE);
                    calendarView.requestLayout();
                }
            }
        });
    }
}
