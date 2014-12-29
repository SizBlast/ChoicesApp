package hogent.choicesapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChoiceActivity extends Activity {

    private static final String[] ACTIVITY_CHOICES = new String[]{"Open web site","Open contacts","Open dialer","Search google","Start voice command"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        ListView choices = (ListView) findViewById(R.id.list);
        int layoutID = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> myAdapterInstance = new ArrayAdapter<String>(this,layoutID,ACTIVITY_CHOICES);
        choices.setAdapter(myAdapterInstance);

        setOnItemClickListener(choices);
    }

    private void setOnItemClickListener(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            final Intent intent = new Intent();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.google.com"));
                        break;
                    case 1:
                        intent.setAction(Intent.ACTION_PICK);
                        intent.setData(ContactsContract.Contacts.CONTENT_URI);
                        break;
                    case 2:
                        intent.setAction(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:0123456789"));
                        break;
                    case 3:
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.google.com/#q=olaf%20frozen"));
                        break;
                    case 4:
                        Log.e("lala", "lala");
                        intent.setAction(Intent.ACTION_VOICE_COMMAND);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice, menu);
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
