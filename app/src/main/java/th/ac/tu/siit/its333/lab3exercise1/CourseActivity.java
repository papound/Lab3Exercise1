package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;


public class CourseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void add_course(View v){

        Intent i = new Intent(this, MainActivity.class);
        String course_name = ((EditText) findViewById(R.id.etCode)).getText().toString();
        int credit = Integer.parseInt(((EditText) findViewById(R.id.etCR)).getText().toString());
        i.putExtra("course-name",course_name);
        i.putExtra("course-credit", credit);

        RadioGroup rg = (RadioGroup) findViewById(R.id.rgroup);
        String grade="";

        switch (rg.getCheckedRadioButtonId()){

            case R.id.rbA: grade = "A";
                break;
            case R.id.rbBP:  grade = "B+";
                break;
            case R.id.rbB:  grade = "B";
                break;
            case R.id.rbCP:  grade = "C+";
                break;
            case R.id.rbC:  grade = "C";
                break;
            case R.id.rbDP:  grade = "D+";
                break;
            case R.id.rbD:  grade = "D";
                break;
            case R.id.rbF:  grade = "F";
                break;
            default: grade = "F";
                break;

        }
        i.putExtra("grades", grade);
        setResult(RESULT_OK, i);
        finish();

    }
}
