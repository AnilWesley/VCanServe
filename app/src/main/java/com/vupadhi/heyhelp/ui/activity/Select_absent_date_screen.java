package com.vupadhi.heyhelp.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.SelectAbsentDateScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.SelectAbsentDateScreenImpl;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class Select_absent_date_screen extends BaseAbstractActivity<SelectAbsentDateScreenImpl> implements SelectAbsentDateScreenContract.IView, APIResponseCallback, SlyCalendarDialog.Callback {
    LinearLayout linearLayout;
    ImageView imageView;
    CalendarView calendarView;
  //  ArrayList<String> dateslist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_select_absent_date_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new SelectAbsentDateScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        Mark_absent.markAbsentWorkersModel.getData().getWorkerList().size();

        linearLayout = findViewById(R.id.make_absent_lay_out);
        imageView = findViewById(R.id.select_absent_back_but);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        calendarView = (CalendarView) findViewById(R.id.calendarView);
        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -5);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 0);

        calendarView.setMaximumDate(max);
        calendarView.setMinimumDate(min);
        calendarView.showCurrentMonthPage();
        
        
        
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                Calendar min = Calendar.getInstance();
                min.add(Calendar.DATE, -2);

                Calendar max = Calendar.getInstance();
                max.add(Calendar.DATE, 0);

                calendarView.setMaximumDate(max);
                calendarView.setMinimumDate(min);


               // if ()


                System.out.println("calendarView.size"+calendarView.getSelectedDates().size());
                if(calendarView.getSelectedDates().size()>1){
                  calendarView.setClickable(false);

                    Calendar min1 = Calendar.getInstance();
                    min.add(Calendar.DATE, -2);

                    Calendar max1 = Calendar.getInstance();
                    max.add(Calendar.DATE, 0);

                    calendarView.setMaximumDate(max1);
                    calendarView.setMinimumDate(min1);





                    Toast.makeText(Select_absent_date_screen.this, "dont select more than 2 dates", Toast.LENGTH_SHORT).show();
                };
            }
        });
    //  calendarView.

        calendarView.setOnForwardPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Forward", Toast.LENGTH_SHORT).show());

        calendarView.setOnPreviousPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show());

        // calendarView.setSelectedDates(getSelectedDays());

        List<EventDay> events = new ArrayList<>(2);


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        events.add(new EventDay(cal, R.drawable.ic_arrow_right));

        calendarView.setEvents(events);

/*
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("fgvndfjkgdf");
            }
        });
*/

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String finaldate=null;
                int index=0;
                for (Calendar calendar : calendarView.getSelectedDates()) {
                    System.out.println(calendar.getTime().toString());
                    String dateStr = calendar.getTime().toString();
                    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = null;
                    try {
                        date = (Date)formatter.parse(dateStr);
                      String   tempfinaldate=dateFormat.format(date);
                        System.out.println("fkjsdfvjsdklfj"+tempfinaldate);

                        if (index==0){
                            finaldate=tempfinaldate;
                        }else if(index==1){
                            finaldate=finaldate+","+tempfinaldate;
                        }

                            index++;

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(date);
                    Toast.makeText(getApplicationContext(),
                            calendar.getTime().toString(),
                            Toast.LENGTH_SHORT).show();
                }

                Mark_absent.dateslist.set(Mark_absent.position1,finaldate);

                System.out.println("datelist"+ Mark_absent.dateslist.toString());

                Intent returnIntent = new Intent();
//                returnIntent.putExtra("datelist", Mark_absent.dateslist.toString());
                 setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });


       /* calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                System.out.println("clickedDayCalendar"+clickedDayCalendar);
            }
        });*/

       /* calendarView.setOnForwardPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Forward", Toast.LENGTH_SHORT).show());

        calendarView.setOnPreviousPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show());
*/
//        calendarView.setSelectedDates(getSelectedDays());

      /*  DatePickerBuilder builder = new DatePickerBuilder(this, listener)
                .pickerType(CalendarView.ONE_DAY_PICKER);

        DatePicker datePicker = builder.build();
        datePicker.show();*/


/*
        new DatePickerBuilder(this, listener)
                .date(Calendar.getInstance()) // Initial date as Calendar object
                .minimumDate(Calendar.getInstance()) // Minimum available date
                .maximumDate(Calendar.getInstance()); // Maximum available date
*/
            /*    .disabledDays(List<Calendar>) /// List of disabled days
                .headerColor(R.color.color) // Color of the dialog header
                .headerLabelColor(R.color.color) // Color of the header label
                .previousButtonSrc(R.drawable.drawable) // Custom drawable of the previous arrow
                .forwardButtonSrc(R.drawable.drawable) // Custom drawable of the forward arrow
                .previousPageChangeListener(new OnCalendarPageChangeListener(){}) // Listener called when scroll to the previous page
                .forwardPageChangeListener(new OnCalendarPageChangeListener(){}) // Listener called when scroll to the next page
                .abbreviationsBarColor(R.color.color) // Color of bar with day symbols
                .abbreviationsLabelsColor(R.color.color) // Color of symbol labels
                .pagesColor(R.color.sampleLighter) // Color of the calendar background
                .selectionColor(R.color.color) // Color of the selection circle
                .selectionLabelColor(R.color.color) // Color of the label in the circle
                .daysLabelsColor(R.color.color) // Color of days numbers
                .anotherMonthsDaysLabelsColor(R.color.color) // Color of visible days numbers from previous and next month page
                .disabledDaysLabelsColor(R.color.color) // Color of disabled days numbers
                .todayLabelColor(R.color.color) // Color of the today number
                .dialogButtonsColor(R.color.color); // Color of "Cancel" and "OK" buttons
*/

        //List<Calendar> selectedDates = calendarView.getSelectedDates();

    }

    private List<Calendar> getSelectedDays() {
        List<Calendar> calendars = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Calendar calendar = DateUtils.getCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, i);
            calendars.add(calendar);
        }

        return calendars;
    }

    private OnSelectDateListener listener = new OnSelectDateListener() {
        @Override
        public void onSelect(List<Calendar> calendars) {

            System.out.println("dates...." + calendars.size());

        }
    };

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void onCancelled() {

    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {

        if (firstDate != null) {
            if (secondDate == null) {
                firstDate.set(Calendar.HOUR_OF_DAY, hours);
                firstDate.set(Calendar.MINUTE, minutes);
                Toast.makeText(
                        this,
                        new SimpleDateFormat("timeformat", Locale.getDefault()).format(firstDate.getTime()),
                        Toast.LENGTH_LONG

                ).show();
            } else {
                Toast.makeText(
                        this,
                        getString(
                                R.string.pleasewait,
                                new SimpleDateFormat("dateformat", Locale.getDefault()).format(firstDate.getTime()),
                                new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(secondDate.getTime())
                        ),
                        Toast.LENGTH_LONG

                ).show();
            }
        }
    }
}
