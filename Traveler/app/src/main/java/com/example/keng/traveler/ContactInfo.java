package com.example.keng.traveler;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ContactInfo extends AppCompatActivity {

    ListView friendList;
    CursorAdapter contactCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        friendList = (ListView)findViewById(R.id.friendList);
        showContacts();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        if (requestCode == 100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                showContacts();
            }else{
                Toast.makeText(this,"Until you grant the permission,we cannot display the contact list",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    public void showContacts(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission((Manifest.permission.READ_CONTACTS)) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},100);
        }else{
            //After Permissions accepting write code here
            final ContentResolver cr = getContentResolver();
//            Email.data 
            Cursor contactCursor = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);// get all the information in Content Provider
            contactCursorAdapter = new SimpleCursorAdapter(this,R.layout.list_item,contactCursor,new String[]{ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts._ID},new int[]{R.id.listRow},0);
            friendList.setAdapter(contactCursorAdapter);
            friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                     Cursor cursor = (Cursor) contactCursorAdapter.getItem(i);
                     String contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                     Log.e("Debug", "onItemClick: "+contactID);
                     Cursor emailCursor= cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID+"="+contactID,null,null);
                     String emailAddressStr= "";
                     while (emailCursor.moveToNext()){
                         // prepare return the string to detailActivity
                         emailAddressStr = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                     }
                    Intent passIntent = new Intent();
                    passIntent.putExtra("emailAddress",emailAddressStr);
                    setResult(Activity.RESULT_OK,passIntent);
                    finish();
                }
            });

        }
    }
}
