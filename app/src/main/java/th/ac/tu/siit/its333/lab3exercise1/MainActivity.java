package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;
    String listCourse = "List of Courses\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();


        /**/



        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void calculate(){
        cr = 0;
        gp = 0.0;
        gpa = 0.0;
        listCourse = "List of Courses\n";
        int x;
        for(x=0;x<listCodes.size();x++){

            cr += listCredits.get(x);
            String grade_str = listGrades.get(x);
            switch(grade_str){
                case "A": gp += 4.0*listCredits.get(x);
                    break;
                case "B+": gp += 3.5*listCredits.get(x);
                    break;
                case "B": gp += 3.0*listCredits.get(x);
                    break;
                case "C+": gp += 2.5*listCredits.get(x);
                    break;
                case "C": gp += 2.0*listCredits.get(x);
                    break;
                case "D+": gp += 1.5*listCredits.get(x);
                    break;
                case "D": gp += 1.0*listCredits.get(x);
                    break;
                case "F": gp += 0.0*listCredits.get(x);
                    break;
                default: gp+=0.0*listCredits.get(x);
            }

            listCourse+=listCodes.get(x)+" ("+listCredits.get(x)+" credits) = "+grade_str+"\n";
        }

        gpa = gp/cr;

        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);
        TextView tvGP = (TextView)findViewById(R.id.tvGP);
        TextView tvCR = (TextView)findViewById(R.id.tvCR);
        tvGP.setText(Double.toString(gp));
        tvCR.setText(Integer.toString(cr));
        tvGPA.setText(Double.toString(gpa));



    }

    public void buttonClicked(View v) {

        int id = v.getId();
        if(id==R.id.button4){//Show Course List

            Intent i = new Intent(this, CourseListActivity.class);
            //int size = listCodes.size();
            //i.putExtra("size",size);
            i.putExtra("courses",listCourse);
            startActivity(i);

        }else if(id==R.id.button2){//Add Course

            Intent i = new Intent(this, CourseActivity.class);
            startActivityForResult(i, 200);

        }else if(id==R.id.button){//Reset

            listGrades.clear();
            listCodes.clear();
            listCredits.clear();
            TextView tvGPA = (TextView)findViewById(R.id.tvGPA);
            TextView tvGP = (TextView)findViewById(R.id.tvGP);
            TextView tvCR = (TextView)findViewById(R.id.tvCR);
            tvGP.setText("0.0");
            tvCR.setText("0");
            tvGPA.setText("0.0");
            listCourse = "List of Courses\n";

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                //String result = data.getStringExtra("retValue");

                String Codes = data.getStringExtra("course-name");
                cr = data.getIntExtra("course-credit",-1);
                String grades = data.getStringExtra("grades");
                listCodes.add(Codes);
                listCredits.add(cr);
                System.out.println("grades " + grades);
                listGrades.add(grades);
                System.out.println("Begin Calculate");
                calculate();
                Toast t = Toast.makeText(this.getApplicationContext(),
                        "Done Add Course: " + Codes, Toast.LENGTH_SHORT);
                t.show();
            }
            else if (resultCode == RESULT_CANCELED) {
                Toast t = Toast.makeText(this.getApplicationContext(),
                        "Error", Toast.LENGTH_SHORT);
                t.show();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
